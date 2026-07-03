package Automation.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject()
	{
		String path = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path); // create report html file with some config
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");

		ExtentReports Extent = new ExtentReports(); // is the main class & attach the above created report here
		/* This is the main report manager. It collects all test cases and writes them
		 * into the HTML report. */
		Extent.attachReporter(reporter);
		Extent.setSystemInfo("Tester", "Christ Raja"); // These details appear in the report.
		return Extent;
	}
}
