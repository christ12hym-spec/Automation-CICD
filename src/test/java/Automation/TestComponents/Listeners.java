package Automation.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Automation.Resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports Extent = ExtentReporterNG.getReportObject();
	ThreadLocal <ExtentTest> extenTest = new ThreadLocal <ExtentTest> (); // tread safe if we execute test parallely result may interchange and update in report 

	@Override
	public void onTestStart(ITestResult result) { // this method execute before all test
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		test = Extent.createTest(result.getMethod().getMethodName()); // to get test name to write the testcase result
																		// in report
		extenTest.set(test); //capture own thread id of the test and whileexecute the test
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		extenTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		extenTest.get().log(Status.FAIL, "Test Failed");// extenTest.get()extract the thread mappedagainst the Test
		//test.fail(result.getThrowable()); // instead of test used extenTest.get().fail(result.getThrowable());  for thread safe
		extenTest.get().fail(result.getThrowable()); // to display the Error msg
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extenTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
		Extent.flush();
	}

}
