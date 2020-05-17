package com.employeeapi.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC003_Post_New_Employee_Record extends TestBase {

	String empName = RestUtils.empName();
	String empSal = RestUtils.empSal();
	String empAge = RestUtils.empAge();

	@BeforeClass
	void postNewEmployeerecord() throws InterruptedException {
		logger.info("********Started TC003_Post_New_Employee_Record*******");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httprequest = RestAssured.given();

		// create a Json Object
		JSONObject requestparams = new JSONObject();

		// specify request params
		requestparams.put("name", empName);
		requestparams.put("salary", empSal);
		requestparams.put("age", empAge);

		// Specify content type is Json
		httprequest.header("Content-Type", "application/json");

		// Attach the body to the request
		httprequest.body(requestparams.toJSONString());
		// Response object/ pass the method

		response = httprequest.request(Method.POST, "/create");

		Thread.sleep(5000);
	}

	@Test
	void getStatusCode() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	void getResponseBody() {
		String responseBody = response.getBody().asString();
		logger.info("Response Body is==>" + responseBody);
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empSal), true);
		Assert.assertTrue(responseBody.contains(empAge));
	}

	@Test
	void getStatusLine() {
		String statusLine = response.getStatusLine();
		logger.info("Status Line is==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@AfterClass
	void tearDown() {
		logger.info("********Finished TC003_Post_New_Employee_Record*******");
	}
}
