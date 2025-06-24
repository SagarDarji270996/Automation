package projects.trakzee.web.pages.common.header;

import java.util.*;

public class FilterMapper {
	// HashMap storing <filterField, <pageName, fieldValue>>
	private static final Map<String, Map<String, String>> FILTER_MAPPINGS = new HashMap<>();

	// Static block to initialize mappings
	static {
		initializeMappings();
	}

	// Method to define mappings
	private static void initializeMappings() {
		// Company mappings
		addMapping("company", "todayactivity", "short_name");
		addBulkMapping("company", new String[] { "trip", "stoppage", "inactive" }, "company_name");
		addBulkMapping("company", new String[] { "idle", "travel", "objectbatterystatus" }, "company");


		// Branch mappings
		addMapping("branch", "todayactivity", "location_name");
		addBulkMapping("branch", new String[] { "trip", "stoppage", "inactive" }, "location_name");
		addBulkMapping("branch", new String[] { "travel", "idle", "objectbatterystatus" }, "branch");

		// Vehicle Group mappings
		addBulkMapping("vehicleGroup",
				new String[] { "todayactivity", "travel", "travelhistory", "trip", "stoppage", "idle", "inactive" },
				null);

		// Vehicle Type mappings
		addBulkMapping("vehicleGroup",
				new String[] { "todayactivity", "travel", "travelhistory", "trip", "stoppage", "idle", "inactive" },
				null);

		// Vehicle Brand mappings
		addBulkMapping("vehicleBrand",
				new String[] { "todayactivity", "travel", "travelhistory", "trip", "stoppage", "idle", "inactive",
						"objectbatterystatus" },
				"object_brand");
		addBulkMapping("vehicleBrand", new String[] { "travelhistory" }, null);

		// Vehicle Model mappings
		addBulkMapping("vehicleModel",
				new String[] { "todayactivity", "travel", "trip", "stoppage", "idle", "inactive",
						"objectbatterystatus" },
				"object_model");
		addBulkMapping("vehicleModel", new String[] { "travelhistory" }, null);

		// Distance mappings
		addBulkMapping("distance", new String[] { "todayactivity", "trip" }, "total_running_km");
		addBulkMapping("distance", new String[] { "travel" }, "running_km");
		addBulkMapping("distance", new String[] { "travelhistory" }, null);

		//stopDuration
		addBulkMapping("stopDuration", new String[] { "todayactivity", "travelhistory", "travel" }, null);

		//idle duration
		addBulkMapping("idleDuration", new String[] { "todayactivity", "travelhistory", "travel" }, null);

		//duration format
		addBulkMapping("durationFormat", new String[] { "todayactivity", "travelhistory", "travel" }, null);

		//Trip calculation for
		addBulkMapping("tripCalculationFor", new String[] { "todayactivity", "travelhistory", "travel" }, null);

		//Trip calculation for
		addBulkMapping("tripCalculationFor", new String[] { "todayactivity", "travelhistory", "travel" }, null);

		//Idle
		addBulkMapping("idle", new String[] { "stoppage" }, "total_idle_duration");
		addBulkMapping("idle", new String[] { "travel" }, "idle_duration");
		addBulkMapping("idle", new String[] { "todayactivity", "travelhistory", "trip" }, null);

		//Stop
		addBulkMapping("stop", new String[] { "stoppage" }, "total_stop_duration");
		addBulkMapping("stop", new String[] { "travel" }, "stop_duration");
		addBulkMapping("stop", new String[] { "todayactivity", "travelhistory", "trip" }, null);

		//Inactive
		addBulkMapping("inactive", new String[] { "travel" }, "inactive_duration");
		addBulkMapping("inactive", new String[] { "todayactivity", "travelhistory", "trip", "stoppage" }, null);

		//Running
		addBulkMapping("running", new String[] { "stoppage" }, "total_running_duration");
		addBulkMapping("running", new String[] { "travel" }, "running_duration");
		addBulkMapping("running", new String[] { "todayactivity", "travelhistory", "trip" }, null);

		//Duration
		addBulkMapping("duration", new String[] { "travel" }, "total_duration");
		addBulkMapping("duration", new String[] { "todayactivity", "travelhistory", "trip", "stoppage" }, null);

		//Work Duration
		addBulkMapping("workDuration", new String[] { "travel" }, "work_duration");
		addBulkMapping("workDuration", new String[] { "todayactivity", "travelhistory", "trip", "stoppage" }, null);

		//Max stop duration
		addBulkMapping("maxStoppage", new String[] { "travel", "stoppage" }, "max_stop_duration");
		addBulkMapping("maxStoppage", new String[] { "todayactivity", "travelhistory", "trip" }, null);

		//Vehicle category
		addBulkMapping("vehicleCategory", new String[] { "objectefficiency" }, "vehicle_category");

		// Status mappings
		addBulkMapping("status", new String[] { "todayactivity" }, "vehicle_status");
		addBulkMapping("status", new String[] { "travelhistory", "trip" }, null);

		// Object Selection mappings
		addMapping("objectSelection", "travel", "vehicle_information");
		addBulkMapping("objectSelection", new String[] { "travel", "inactive" }, "vehicle_information");
		addBulkMapping("objectSelection", new String[] { "trip", "stoppage", "todayactivity" }, "vehicle_info");

		// Default NA mappings
		addNAFields(new String[] { "dateSelection", "timeRange", "durationFormat" });
	}

	// Helper method for single mapping
	private static void addMapping(String filterField, String pageName, String value) {
		FILTER_MAPPINGS.computeIfAbsent(filterField, k -> new HashMap<>()).put(pageName, value);
	}

	// Helper method for bulk mapping
	private static void addBulkMapping(String filterField, String[] pages, String value) {
		Map<String, String> pageMap = FILTER_MAPPINGS.computeIfAbsent(filterField, k -> new HashMap<>());
		for (String page : pages) {
			pageMap.put(page, value);
		}
	}

	// Helper method for default "NA" mappings
	private static void addNAFields(String[] fields) {
		for (String field : fields) {
			FILTER_MAPPINGS.put(field, Collections.singletonMap("default", "na"));
		}
	}

	// Method to retrieve values
	public static String getFilterField(String filterField, String pageName) {
		return FILTER_MAPPINGS.getOrDefault(filterField, Collections.emptyMap()).getOrDefault(pageName, null);
	}

	// Main method to test the lookup
	public static void main(String[] args) {
		System.out.println(getFilterField("company", "trip")); // Output: company_name
		System.out.println(getFilterField("company", "stoppage")); // Output: company_name
		System.out.println(getFilterField("company", "idle")); // Output: company
		System.out.println(getFilterField("company", "unknown_page")); // Output: null
		System.out.println(getFilterField("dateSelection", "anyPage")); // Output: na
	}
}
