package com.bridgelabz.selenium.hashmapusage;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author Vijaykumar Bhavanur purpose: To demonstrate reading PDF in selenium
 *
 */
public class ReadPDF {

	// Defining driver instance reference
	WebDriver driver;

	/**
	 * Method used to create and initialize driver instance
	 */

	@BeforeMethod
	public void setup() {
		// To set the path of the driver for the respective browser
		System.setProperty("webdriver.chrome.driver", "/home/admin1/Documents/chromedriver");
		// Creating browser instance based on driver
		driver = new ChromeDriver();
	}

	@Test
	public void readPDF() throws IOException {

		// To Open URL of pdf to be read
		driver.get("file:///home/admin1/Desktop/sample.pdf");

		// Creating URL Object with current url name as parameter
		URL url = new URL(driver.getCurrentUrl());

		// Opening input stream to read data
		InputStream is = url.openStream();

		// Creating buffer object for temporary data storage
		BufferedInputStream fileToParse = new BufferedInputStream(is);

		// Defining PDF Document reference
		PDDocument document = null;
		try {
			// Loading pdf contains from buffer
			document = PDDocument.load(fileToParse);

			// Extracting data in simple string
			String output = new PDFTextStripper().getText(document);

			// Printing PDF contains
			System.out.println("File content ::::::\n\n" + output);

			// Checking whether PDF contains string "This is a small demonstration .pdf
			// file"
			Assert.assertEquals(true, output.contains(" This is a small demonstration .pdf file"));

		} finally {
			if (document != null) {

				// closing document reference
				document.close();
			}
			// closing buffer
			fileToParse.close();

			// closing inputstream
			is.close();
		}

	}

	/**
	 * Method to close resource such as browser after executing test methods
	 */
	@AfterMethod
	public void releaseResource() {
		driver.close();
	}

}
