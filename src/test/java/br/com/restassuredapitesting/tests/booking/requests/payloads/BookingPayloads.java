package br.com.restassuredapitesting.tests.booking.requests.payloads;

import com.github.javafaker.Faker;
import org.json.JSONObject;

public class BookingPayloads {

    Faker randomStuff = new Faker();

    public JSONObject getBookingPayload() {
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");
        payload.put("firstname", randomStuff.name().firstName());
        payload.put("lastname", randomStuff.name().lastName());
        payload.put("totalprice", 111);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "breakfast");
        return payload;
    }

    public JSONObject getPayloadMissingParams() {
        JSONObject payload = new JSONObject();
        payload.put("firstname", randomStuff.name().firstName());
        payload.put("lastname", randomStuff.name().lastName());
        payload.put("totalprice", false);
        payload.put("depositpaid", true);
        payload.put("additionalneeds", "breakfast");
        return payload;
    }
}
