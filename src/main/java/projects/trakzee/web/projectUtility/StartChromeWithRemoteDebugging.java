package projects.trakzee.web.projectUtility;

import java.io.IOException;
import java.net.Socket;

import org.testng.asserts.SoftAssert;

import projects.trakzee.configurations.base.TestBase;

public class StartChromeWithRemoteDebugging {
	private SoftAssert softAssert = TestBase.getSoftAssert();
	public static void startChromeIfNotRunning(int port, String userBrowserStoreDataDirectory, String userProfile) {
		if (isPortInUse(port)) {
			System.out.println("Chrome is already running on port " + port);
		} else {
			startChromeWithRemoteDebugging(port, userBrowserStoreDataDirectory, userProfile);
		}
	}

	private static boolean isPortInUse(int port) {
		try (Socket socket = new Socket("localhost", port)) {
			return true; // Port is in use
		} catch (IOException e) {
			return false; // Port is not in use
		}
	}

//	private static boolean isPortInUse(int port) {
//		try {
//			URL url = new URL("http://localhost:" + port + "/json");
//			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//			connection.setRequestMethod("GET");
//			connection.setConnectTimeout(1000);
//			connection.connect();
//			return connection.getResponseCode() == 200; // Chrome is running in debugger mode
//		} catch (Exception e) {
//			return false;
//		}
//	}


	private static void startChromeWithRemoteDebugging(int port, String userBrowserStoreDataDirectory,
			String userProfile) {
		try {
			String chromePath = "google-chrome"; 
			if ((System.getProperty("os.name").toLowerCase().contains("win"))) {
				chromePath = System.getenv("CHROME_HOME");
				if (chromePath != null) {
					chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
				}
				System.out.println("Window enviroment chrome home path or google chrome path in window: " + chromePath);

			} else if ((System.getProperty("os.name").toLowerCase().contains("nix"))
					|| System.getProperty("os.name").toLowerCase().contains("nux")) {
				chromePath = "/usr/bin/google-chrome";
				System.out.println("Linux chrome home path: " + chromePath);
			}
			String portArg = "--remote-debugging-port=" + port;
			String userDataDirArg = "--user-data-dir=" + userBrowserStoreDataDirectory;
			String profileDirArg = "--profile-directory=" + userProfile; // Add profile-directory

			//Used to stop auto page refresh on loading.
			String additionalArgs = "--disable-extensions --disable-background-networking --disable-features=PreloadPages";

			// Include both the user-data-dir and profile-directory arguments
			ProcessBuilder processBuilder = new ProcessBuilder(chromePath, portArg, userDataDirArg, profileDirArg,
					additionalArgs);
			Process process = processBuilder.start();

			System.out.println("Chrome started on remote debugging on port " + port);
		} catch (IOException e) {
			System.out.println("Error occurred while starting Chrome: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		StartChromeWithRemoteDebugging starter = new StartChromeWithRemoteDebugging();
		int port = 9222;
		String userDataDir = "C:\\Users\\koiri\\MyChromeData"; // Update the user data directory path as needed
		String userProfile = "Profile 3";
		starter.startChromeIfNotRunning(port, userDataDir, userProfile);
	}
}
