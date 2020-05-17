package com.employeeapi.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {

	public ExtentSparkReporter sparkreporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext testContext) {
		sparkreporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/extentreport.html");
		sparkreporter.config().setDocumentTitle("Automation Report");
		sparkreporter.config().setReportName("API Testing Employee Project");
		sparkreporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkreporter);
		extent.setSystemInfo("Host mame", "local host");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Saniya");

	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());// create new entry in the report
		test.log(Status.FAIL, "Test Case Failed is" + result.getName());// to add name in extent report
		test.log(Status.FAIL, "Test Case Failed is" + result.getThrowable());

	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());// create new entry in the report
		test.log(Status.PASS, "Test Case Passed is" + result.getName());// to add name in extent report
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test case skipped is" + result.getName());
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}
