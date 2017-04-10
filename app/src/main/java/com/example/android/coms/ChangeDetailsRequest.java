package com.example.android.coms;

/**
 * Created by Knayak on 06/04/2017.
 */
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by knayak on 09-02-2017.
 */

public class ChangeDetailsRequest extends StringRequest {
    private static final String CHANGE_DETAILS_REQUEST_URL = "https://knayak.000webhostapp.com/change_details.php";
    private Map<String, String> params;

    public ChangeDetailsRequest(long user_id, String name, String username, String password, long mob_num, String email, String address, Response.Listener<String> listener) {
        super(Method.POST, CHANGE_DETAILS_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("user_id", user_id + "");
        params.put("name", name);
        params.put("username", username);
        params.put("password", password);
        params.put("mob_num", mob_num + "");
        params.put("email", email);
        params.put("address", address);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
