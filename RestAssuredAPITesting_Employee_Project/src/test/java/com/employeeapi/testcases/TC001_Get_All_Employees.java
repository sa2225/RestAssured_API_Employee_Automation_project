package com.employeeapi.testcases;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_Get_All_Employees extends TestBase {

	@BeforeClass
	void getAllEmployees() throws InterruptedException {

		logger.info("********* Started TC001_Get_All_Employees  ********");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httprequest = RestAssured.given();
		response = httprequest.request(Method.GET, "/employees");
		Thread.sleep(3);

	}

	@Test
	void checkResponseBodyNotNull() {
		String responseBody = response.getBody().asString();
		logger.info("Response Body==>" + responseBody);
		Assert.assertTrue(responseBody != null);

	}

	@Test
	void checkResponseTime() {
		long responseTime = response.getTime(); // Get response time
		logger.info("Response Time is==>" + responseTime);

		if (responseTime > 2000) {
			logger.warn("Response time is greater than 2000");
		}
		Assert.assertTrue(responseTime < 2000);

	}

	@Test
	void checkStatusLine() {
		String statusLine = response.getStatusLine();
		logger.info("Status Line is==>" + statusLine);
		Assert.assertEquals("HTTP/1.1 200 OK", statusLine);
	}

	@Test
	void checkContentType() {
		String contentType = response.header("Content-Type");
		logger.info("Content Type is==>" + contentType);
		Assert.assertEquals("application/json;charset=utf-8", contentType);
	}

	@Test
	void checkServerType() {
		String serverType = response.header("Server");
		logger.info("Server type is==>" + serverType);
		Assert.assertEquals("nginx/1.16.0", serverType);
	}

	@Test
	void checkCookieGenerated() {
		String cookie = response.getCookie("ezoadgid_133674");
		logger.info("Cookie is==>" + cookie);

	}

	@AfterClass
	void tearDown() {
		logger.info("********* Finished TC001_Get_All_Employees  ********");
	}
}
