package resources;

import java.io.BufferedInputStream;
import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.testng.Assert;
//import org.junit.Assert;

public class PDFReader {

	//public static String readPDfContent(String pathOfFile) {
	public static String readPDfContent(BufferedInputStream pathOfFile,String s) {
		String pdfFileInText = "";
		try {
			PDDocument document = PDDocument.load(pathOfFile);
			document.getClass();
			if (!document.isEncrypted()) {
				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition(true);
				PDFTextStripper tStripper = new PDFTextStripper();
				pdfFileInText = tStripper.getText(document);
				System.out.println(pdfFileInText);
				Assert.assertTrue(pdfFileInText.contains(s));

			}
			return pdfFileInText;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;

		}
	}
}
