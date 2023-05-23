package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

public class TestBase {

	public static Response createBooking() {
		// Create json body
		JSONObject body = new JSONObject();
		body.put("firstname", "Joseph");
		body.put("lastname", "Ryan");
		body.put("totalprice", 650);
		body.put("depositpaid", true);

		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2023-08-20");
		bookingdates.put("checkout", "2023-08-28");
		body.put("bookingdates", bookingdates);
		body.put("additionalneeds", "Ocean view");

		// Get response
		Response response = RestAssured.given().contentType(ContentType.JSON).body(body.toString()).post("https://restful-booker.herokuapp.com/booking");
		return response;
	}
}
