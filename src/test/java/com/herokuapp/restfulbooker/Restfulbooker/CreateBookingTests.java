package com.herokuapp.restfulbooker.Restfulbooker;

import com.herokuapp.restfulbooker.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

public class CreateBookingTests extends TestBase {

	@Test
	public void createBookingTest() {

		Response response = createBooking();
		response.print();

		// Validate response is 200
		assertEquals(response.getStatusCode(), 200, "Status code should be 200, but it is " + response.getStatusCode());

		// Validate all fields
		SoftAssert softAssert = new SoftAssert();

		String actualFirstName = response.jsonPath().getString("booking.firstname");
		softAssert.assertEquals(actualFirstName, "Joseph", "firstname in response does not match expected value: " + actualFirstName);

		String actualLastName = response.jsonPath().getString("booking.lastname");
		softAssert.assertEquals(actualLastName, "Ryan", "lastname in response does not match expected value: " + actualLastName);

		int totalprice = response.jsonPath().getInt("booking.totalprice");
		softAssert.assertEquals(totalprice, 650, "totalprice in response does not match expected value: " + totalprice);

		boolean isDepositPaid = response.jsonPath().getBoolean("booking.depositpaid");
		softAssert.assertTrue(isDepositPaid, "depositpaid should be true, but it was: " + isDepositPaid);

		String actualCheckin = response.jsonPath().getString("booking.bookingdates.checkin");
		softAssert.assertEquals(actualCheckin, "2023-08-20", "checkin in response does not match expected value: " + actualCheckin);

		String actualCheckout = response.jsonPath().getString("booking.bookingdates.checkout");
		softAssert.assertEquals(actualCheckout, "2023-08-28", "checkout in response does not match expected value: " + actualCheckout);

		String additionalNeeds = response.jsonPath().getString("booking.additionalneeds");
		softAssert.assertEquals(additionalNeeds, "Ocean view", "additionalNeeds in response does not match expected value: " + additionalNeeds);

		softAssert.assertAll();


		/**
		 * curl -X POST \
		 *   https://restful-booker.herokuapp.com/booking \
		 *   -H 'Content-Type: application/json' \
		 *   -d '{
		 *     "firstname" : "Jim",
		 *     "lastname" : "Brown",
		 *     "totalprice" : 111,
		 *     "depositpaid" : true,
		 *     "bookingdates" : {
		 *         "checkin" : "2018-01-01",
		 *         "checkout" : "2019-01-01"
		 *     },
		 *     "additionalneeds" : "Breakfast"
		 * }'
		 */
	}
}
