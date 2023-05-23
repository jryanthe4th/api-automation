package com.herokuapp.restfulbooker.Restfulbooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class GetBookingIdsTests {

	@Test
	public void getBookingIdsWithoutFilterTest() {

		// Get response with booking Ids
		Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking");
		response.print();

		// Verify response is 200
		assertEquals(response.getStatusCode(), 200, "Status code should be 200, but it is " + response.getStatusCode());

		// Verify at least 1 booking Id in response
		List<Integer> bookingIds = response.jsonPath().getList("bookingId");
		assertFalse(bookingIds.isEmpty(), "List of bookingIds is empty, but it shouldn't be");
	}
}
