package projects.trakzee.web.projectUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZIPFileHandler {

	private static final String ZIP_FILE_PATH = "C:/path/to/your/zipfile.zip";
	private static final String EXTRACT_DIR = "C:/path/to/temp/extracted/";

	public static void main(String[] args) throws IOException {
            // Extract files from ZIP
            extractZipFiles(ZIP_FILE_PATH, EXTRACT_DIR);

		}

	private static void extractZipFiles(String zipFilePath, String extractDir) throws IOException {
		File dir = new File(extractDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
			ZipEntry entry;
			byte[] buffer = new byte[1024];

			while ((entry = zipIn.getNextEntry()) != null) {
				if (!entry.isDirectory() && (entry.getName().endsWith(".xlsx"))) {
					File newFile = new File(extractDir + entry.getName());
					try (FileOutputStream fos = new FileOutputStream(newFile)) {
						int len;
						while ((len = zipIn.read(buffer)) > 0) {
							fos.write(buffer, 0, len);
						}
					}
				}
				zipIn.closeEntry();
			}
		}
	}

}
