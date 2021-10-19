package br.com.restassuredapitesting.tests.booking.requests.payloads;

import org.json.JSONException;
import org.json.JSONObject;

public class BookingPayloads {


    public JSONObject getBookingPayload() {
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();
        try {
            bookingDates.put("checkin", "2018-01-01");
            bookingDates.put("checkout", "2019-01-01");
            payload.put("firstname", "Cristiano");
            payload.put("lastname", "Ronaldo");
            payload.put("totalprice", 111);
            payload.put("depositpaid", true);
            payload.put("bookingdates", bookingDates);
            payload.put("additionalneeds", "breakfast");
        } catch (JSONException e) {
            System.out.println("Informação inválida para BookingPayloads");
            e.printStackTrace();
        }
        return payload;
    }
}
