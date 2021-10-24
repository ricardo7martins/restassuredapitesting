package br.com.restassuredapitesting.utils;

import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class Utils {
    public static String getSchemaBasePath(String pack, String schemaName) {
        return System.getProperty("user.dir")
                + "/src/test/java/br/com/restassuredapitesting/tests/"
                + pack
                + "/schema/"
                + schemaName
                + ".json";
    }

    public static String getValueFromBooking(String key) {
        GetBookingRequest getBookingRequest = new GetBookingRequest();
        ExtractableResponse<Response> firstBooking = getBookingRequest.getFirstBooking().then().extract();
        if (key.equals("checkin")) {
            key = "bookingdates.get(\"checkin\")";
        } else if (key.equals("checkout")) {
            key = "bookingdates.get(\"checkout\")";
        }
        return firstBooking.path(key);
    }

    public static String getValueFromBooking(String key, int id) {
        GetBookingRequest getBookingRequest = new GetBookingRequest();
        ExtractableResponse<Response> firstBooking = getBookingRequest.getFirstBooking().then().extract();
        if (key.equals("checkin")) {
            key = "bookingdates.get(\"checkin\")";
        } else if (key.equals("checkout")) {
            key = "bookingdates.get(\"checkout\")";
        }
        return firstBooking.path(key);
    }


}
