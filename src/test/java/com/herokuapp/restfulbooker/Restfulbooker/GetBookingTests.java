package com.herokuapp.restfulbooker.Restfulbooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class GetBookingTests {

	@Test
	public void getBookingIdsWithoutFilterTest() {

		// Get response with booking Ids
		Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/5");
		response.print();

		// Validate response is 200
		assertEquals(response.getStatusCode(), 200, "Status code should be 200, but it is " + response.getStatusCode());

		// Validate all fields
		SoftAssert softAssert = new SoftAssert();

		String actualFirstName = response.jsonPath().getString("firstname");
		softAssert.assertEquals(actualFirstName, "Eric", "firstname in response does not match expected value: " + actualFirstName);

		String actualLastName = response.jsonPath().getString("lastname");
		softAssert.assertEquals(actualLastName, "Smith", "lastname in response does not match expected value: " + actualLastName);

		int totalprice = response.jsonPath().getInt("totalprice");
		softAssert.assertEquals(totalprice, 573, "totalprice in response does not match expected value: " + totalprice);

		boolean isDepositPaid = response.jsonPath().getBoolean("depositpaid");
		softAssert.assertTrue(isDepositPaid, "depositpaid should be true, but it was: " + isDepositPaid);

		String actualCheckin = response.jsonPath().getString("bookingdates.checkin");
		softAssert.assertEquals(actualCheckin, "2019-09-22", "checkin in response does not match expected value: " + actualCheckin);

		String actualCheckout = response.jsonPath().getString("bookingdates.checkout");
		softAssert.assertEquals(actualCheckout, "2022-11-03", "checkout in response does not match expected value: " + actualCheckout);

		softAssert.assertAll();

		/**
		 * {
		 *     "firstname": "Sally",
		 *     "lastname": "Brown",
		 *     "totalprice": 111,
		 *     "depositpaid": true,
		 *     "bookingdates": {
		 *         "checkin": "2013-02-23",
		 *         "checkout": "2014-10-23"
		 *     },
		 *     "additionalneeds": "Breakfast"
		 * }
		 */
	}
}
