package com.employeeapi.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC005_Delete_Employee_Record extends TestBase {

	@BeforeClass
	void deleteEmployeeRecord() throws InterruptedException {
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httprequest = RestAssured.given();

		// Get all the employees
		response = httprequest.request(Method.GET, "/employees");

		/*
		 * we need to do a get to grab the id from 0th position and then perform a
		 * delete on it
		 */

		JsonPath jsonpathevaluator = response.jsonPath();
		String empID = jsonpathevaluator.get("[0].id");

		httprequest.request(Method.DELETE, "/delete/" + empID); // pass the empID to the request
		Thread.sleep(5000);

	}

	@Test
	void checkResponseBody() {
		String responseBody = response.getBody().asString();
		logger.info("Response Body is==>" + responseBody);
		Assert.assertEquals(responseBody.contains("successfully! deleted Records"), true);

	}

}
