package com.employeeapi.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC004_Put_Employee_Record extends TestBase {

	String empName = RestUtils.empName();
	String empSal = RestUtils.empSal();
	String empAge = RestUtils.empAge();

	@BeforeClass
	void putEmployeeRecord() throws InterruptedException {
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httprequest = RestAssured.given();

		// Define Json Object
		JSONObject requestparams = new JSONObject();

		// Specify request params
		requestparams.put("name", empName);
		requestparams.put("salary", empSal);
		requestparams.put("age", empAge);

		// Specify content type is json
		httprequest.headers("Content-Type", "application/json");

		// Attach body to the request
		httprequest.body(requestparams.toJSONString());

		// Reponse object/Pass the method
		response = httprequest.request(Method.PUT, "/update/" + empID);

		Thread.sleep(5000);

	}

	@Test
	void checkResponseBody() {
		String responseBody = response.getBody().asString();
		logger.info("Response Body is==>" + responseBody);
		Assert.assertTrue(responseBody.contains(empName));
		Assert.assertTrue(responseBody.contains(empSal));
		Assert.assertTrue(responseBody.contains(empAge));

	}

	@Test
	void checkStatusCode() {
		int statusCode = response.getStatusCode();
		logger.info("Status Code is==>" + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

}
