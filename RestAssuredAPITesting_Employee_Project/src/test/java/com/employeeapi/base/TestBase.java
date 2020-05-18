package com.employeeapi.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	/*
	 * I am declaring it static because I don't want to create an object for these
	 * again. Static variable belong to a class and are initialized only once
	 */

	public static RequestSpecification httprequest;
	public static Response response;
	public static String empID = "21";

	public Logger logger; // global variable because we are going to be using it in all the classes

	@BeforeClass // This setup method will execute first
	public void setup() {
		logger = Logger.getLogger("EmployeeRestAPI");// give any name
		PropertyConfigurator.configure("log4j.properties"); // give path of log4j properties file
		logger.setLevel(Level.DEBUG);
	}

}
