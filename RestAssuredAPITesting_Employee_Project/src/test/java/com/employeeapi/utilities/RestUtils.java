package com.employeeapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

	public static String empName() {

		String generatedstring = RandomStringUtils.randomAlphabetic(1); // generates random single char
		return ("Saniya" + generatedstring);

	}

	public static String empSal() {
		String generatedstring = RandomStringUtils.randomNumeric(5); // generate random number of 5 digits
		return (generatedstring);
	}

	public static String empAge() {
		String generatedstring = RandomStringUtils.randomNumeric(2);// Generates random number of 2 digits
		return (generatedstring);
	}

}
