package projects.trakzee.web.projectUtility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import org.testng.asserts.SoftAssert;

import projects.trakzee.configurations.base.TestBase;

public class FileHandling {
	private SoftAssert softAssert = TestBase.getSoftAssert();
	public static void main(String[] args) {
		try {
			// Example Usage
			String chromeDownloadPath = "/home/uffizio/Downloads"; // Replace with actual Chrome download path
			String targetDirectoryPath = System.getProperty("user.dir")
					+ "/src/test/resources/trakzee/pdf/report/Activity"; // Replace with desired target directory

			moveMostRecentDownloadedFileToNewLocation(chromeDownloadPath, targetDirectoryPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String moveMostRecentDownloadedFileToNewLocation(String fileDirectoryAddress,
			String targetDirectoryPath) throws IOException {
		File downloadDirectory = new File(fileDirectoryAddress);

		// Validate download directory
		if (!downloadDirectory.exists() || !downloadDirectory.isDirectory()) {
			throw new IllegalArgumentException("Invalid Chrome download directory: " + fileDirectoryAddress);
		}

		// Get the most recent file
		File mostRecentFile = getMostRecentDownloadedFile(downloadDirectory);
		System.out.println("mostRecentFile: " + mostRecentFile);

		if (mostRecentFile == null) {
			System.out.println("No files found in the download directory.");
			return null;
		}

		System.out.println("Most recent file: " + mostRecentFile.getName());

		// Create target directory if it doesn't exist
		File targetDirectory = new File(targetDirectoryPath);
		if (!targetDirectory.exists()) {
			targetDirectory.mkdirs();
		}

		// Move the file
		File targetFile = new File(targetDirectory, mostRecentFile.getName());
		Files.move(mostRecentFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

		System.out.println("File moved to: " + targetFile.getAbsolutePath());
		return mostRecentFile.getName();
	}

	public static File getMostRecentDownloadedFile(File directory) {
		File[] files = directory.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File mostRecentFile = null;
		long lastModifiedTime = Long.MIN_VALUE;

		for (File file : files) {
			if (file.isFile() && file.lastModified() > lastModifiedTime) {
				lastModifiedTime = file.lastModified();
				mostRecentFile = file;
			}
		}

		return mostRecentFile;
	}

	public static File getMostRecentDownloadedFile(String fileDirectory) {
		File directory = new File(fileDirectory);
		File[] files = directory.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File mostRecentFile = null;
		long lastModifiedTime = Long.MIN_VALUE;

		for (File file : files) {
			if (file.isFile() && file.lastModified() > lastModifiedTime) {
				lastModifiedTime = file.lastModified();
				mostRecentFile = file;
			}
		}
		return mostRecentFile;
	}

	public static String updateFileName(File file, Map<String, String> keyValuesForUpdate) {
		// Create a File object

		// Extract the file name and parent directory
		String fileName = file.getName();
		String parentDirectory = file.getParent();

		// Process the file name (example: remove digits, dashes, and modify "_PM")
		String updatedFileName = updateString(fileName, keyValuesForUpdate);

		// Reconstruct the full file path with the updated file name
		String updatedFilePath = parentDirectory + File.separator + updatedFileName;
		System.out.println("Updted file name and path is: " + updatedFilePath);
		return updatedFilePath;
	}

	private static String updateString(String baseString, Map<String, String> stringMap) {
		if (baseString == null) {
			return null;
		}

		for (Map.Entry<String, String> entry : stringMap.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			// Apply replacements correctly
			baseString = baseString.replaceAll(key, value);
			baseString = baseString.replace(key, value);
		}

		System.out.println("Updated file: " + baseString);
		return baseString;
	}

	public static void renameFile(File file, String newName) {
		// Extract file extension (if any)
		String fileName = file.getName();
		String parentDirectory = file.getParent();

		// Get file extension
		String extension = "";
		int lastDotIndex = fileName.lastIndexOf(".");
		if (lastDotIndex != -1) {
			extension = fileName.substring(lastDotIndex); // Include the dot (.)
		}

		// New file name
		String newFileName = newName + extension;
		File newFile = new File(parentDirectory, newFileName);

		// Rename the file
		if (file.renameTo(newFile)) {
			System.out.println("File renamed successfully to: " + newFile.getAbsolutePath());
		} else {
			System.out.println("Failed to rename the file.");
		}
	}


}
