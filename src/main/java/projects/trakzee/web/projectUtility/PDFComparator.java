package projects.trakzee.web.projectUtility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.testng.asserts.SoftAssert;

import projects.trakzee.configurations.base.TestBase;

public class PDFComparator {
	private static SoftAssert softAssert = TestBase.getSoftAssert();

	public static void main(String[] args) {
		String fileAddress = System.getProperty("user.dir")
				+ "/src/test/resources/trakzee/pdf/report/Activity/HasDifferences.txt";
		comparetTwoPDFFileAndFindTheDifferences(
				"/home/uffizio/Automation/MP/taskeye_watm/src/test/resources/trakzee/pdf/report/Fill-Drain_1.pdf",
				"/home/uffizio/Automation/MP/taskeye_watm/src/test/resources/trakzee/pdf/report/Fill-Drain_2.pdf",
				fileAddress);
	}

	public static void comparetTwoPDFFile(String addressPDFFileOne, String addressPDFFileTwo) {

		try {

			// Load the two PDF documents
			PDDocument pdf1 = PDDocument.load(new File(addressPDFFileOne));
			PDDocument pdf2 = PDDocument.load(new File(addressPDFFileTwo));

			// Extract text from both PDFs
			String text1 = new PDFTextStripper().getText(pdf1);
			String text2 = new PDFTextStripper().getText(pdf2);

			// Close the PDF documents
			pdf1.close();
			pdf2.close();

			softAssert.assertTrue(text1.equals(text2), "Both PDF are not have same data");
			// Compare the text content
			if (text1.equals(text2)) {
				System.out.println("The PDF files are identical.");
			} else {
				System.out.println("The PDF files are different.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void comparetTwoPDFFileAndFindTheDifferences(String addressPDFFileOne, String addressPDFFileTwo,
			String fileAddress) {

		try {

			// Load the two PDF documents
			PDDocument pdf1 = PDDocument.load(new File(addressPDFFileOne));
			PDDocument pdf2 = PDDocument.load(new File(addressPDFFileTwo));

			// Extract text from both PDFs
			String text1 = new PDFTextStripper().getText(pdf1);
			String text2 = new PDFTextStripper().getText(pdf2);

			// Close the PDF documents
			pdf1.close();
			pdf2.close();

			// Compare and list differences
			listDifferences(text1, text2, true, fileAddress);

		} catch (IOException e) {
			softAssert.assertTrue(false, "Get expeception: " + e.getMessage());

			e.printStackTrace();
		}
	}

	private static void listDifferences(String text1, String text2, boolean wantToWriteTheDifferencesInFile,
			String outputFilePath) {

		List<String> pdf1Lines = new ArrayList<>();
		List<String> pdf2Lines = new ArrayList<>();


		// Split the texts into lines for comparison
		String[] lines1 = text1.split("\\r?\\n");
		String[] lines2 = text2.split("\\r?\\n");

		System.out.println("Comparing PDFs...");
		boolean hasDifferences = false;

		int maxLines = Math.max(lines1.length, lines2.length);
		for (int i = 0; i < maxLines; i++) {
			String line1 = i < lines1.length ? lines1[i] : "<Missing Line in PDF 1>";
			String line2 = i < lines2.length ? lines2[i] : "<Missing Line in PDF 2>";

			if (!line1.equals(line2)) {
				hasDifferences = true;
				pdf1Lines.add(line1);
				pdf2Lines.add(line2);
			}
		}
		if (wantToWriteTheDifferencesInFile) {
			writeDifferences(pdf1Lines, pdf2Lines, outputFilePath);
		}

		softAssert.assertTrue(!hasDifferences, "Both PDF are not have same data");
		if (!hasDifferences) {
			System.out.println("The PDF files are identical.");
		}

	}

	private static void writeDifferences(List<String> pdf1Lines, List<String> pdf2Lines, String outputFilePath) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
			boolean hasDifferences = false;

			if (pdf2Lines != null && pdf1Lines != null) {
				for (int i = 0; i < Math.max(pdf1Lines.size(), pdf2Lines.size()); i++) {
					String line1 = i < pdf1Lines.size() ? pdf1Lines.get(i) : "MISSING LINE";
					String line2 = i < pdf2Lines.size() ? pdf2Lines.get(i) : "MISSING LINE";

					if (!line1.equals(line2)) {
						hasDifferences = true;
						writer.write("Difference at line " + (i + 1) + ":\n");
						writer.write("PDF 1: " + line1 + "\n");
						writer.write("PDF 2: " + line2 + "\n");
						writer.write("--------------------------------------\n");
					}
				}
			}

			if (!hasDifferences) {
				writer.write("No differences found between the two PDFs.\n");
			}

			System.out.println("Comparison completed. Differences saved to: " + outputFilePath);
		} catch (IOException e) {
			System.err.println("Error writing differences to file: " + e.getMessage());
		}
	}

}
