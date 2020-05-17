package com.employeeapi.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC002_Get_Single_Employee_record extends TestBase {

	@BeforeClass
	void getSingleEmployeeRecord() throws InterruptedException {
		logger.info("*********** Started TC002_Get_Single_Employee_record******");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httprequest = RestAssured.given();
		response = httprequest.request(Method.GET, "/employee/" + empID);
		Thread.sleep(3000);

	}

	@Test
	void checkEmpID() {

		String responseBody = response.getBody().asString();
		logger.info("Response body is==>" + responseBody);
		Assert.assertEquals(responseBody.contains(empID), true);

	}

	@Test
	void checkStatusCode() {
		int statusCode = response.getStatusCode();
		logger.info("Status Code is==>" + statusCode);
		Assert.assertEquals(statusCode, 200);

	}

	@Test
	void checkResponseTime() {
		long responseTime = response.getTime();
		logger.info("Response Time is==>" + responseTime);
		if (responseTime > 1000) {
			logger.warn("response time is greater than 200");
			Assert.assertTrue(responseTime < 1000);
		}
	}

	@Test
	void checkContentLength() {
		String contentlength = response.header("Content-Length");
		logger.info("The content length is==>" + contentlength);
		Assert.assertEquals(Integer.parseInt(contentlength),
				134); /* we need to convert content length into a number value and then compare */

	}

	@Test
	void checkemployeeName() {
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains("Tiger"), true);
	}

	@AfterClass
	void tearDown() {
		logger.info("******Finished TC002_Get_Single_Employee_record******");
	}

}
