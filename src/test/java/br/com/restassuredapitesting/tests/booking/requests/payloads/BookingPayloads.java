package br.com.restassuredapitesting.tests.booking.requests.payloads;

import com.github.javafaker.Faker;
import org.json.JSONObject;

import java.sql.Date;

public class BookingPayloads {

    Faker randomStuff = new Faker();

    public JSONObject getBookingPayload() {
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", randomStuff.date()
                .between(Date.valueOf("2018-01-01"), Date.valueOf("2018-06-30")));
        bookingDates.put("checkout", randomStuff.date()
                .between(Date.valueOf("2018-07-01"), Date.valueOf("2018-12-31")));
        payload.put("firstname", randomStuff.name().firstName());
        payload.put("lastname", randomStuff.name().lastName());
        payload.put("totalprice", randomStuff.random().nextInt(60, 200));
        payload.put("depositpaid", randomStuff.random().nextBoolean());
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", randomStuff.food().dish());
        return payload;
    }

    public JSONObject getPayloadWithMissingParams() {
        JSONObject payload = new JSONObject();
        payload.put("firstname", randomStuff.name().firstName());
        payload.put("lastname", randomStuff.name().lastName());
        payload.put("totalprice", false);
        payload.put("depositpaid", true);
        payload.put("additionalneeds", "breakfast");
        return payload;
    }
}
