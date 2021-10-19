package br.com.restassuredapitesting.tests.auth.requests.payloads;

import org.json.JSONException;
import org.json.JSONObject;

public class AuthPayloads {

    public JSONObject jsonAuthLogin() {
        JSONObject payloadLogin = new JSONObject();
        try {
            payloadLogin.put("username", "admin");
            payloadLogin.put("password", "password123");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return payloadLogin;
    }
}
