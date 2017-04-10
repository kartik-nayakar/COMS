package com.example.android.coms;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Knayak on 10/04/2017.
 */

public class TransferRequest extends StringRequest {
    private static final String TRANSFER_REQUEST_URL = "https://knayak.000webhostapp.com/transfer.php";
    private Map<String, String> params;

    public TransferRequest(String newAddress, String reason, Response.Listener<String> listener) {
        super(Method.POST, TRANSFER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("newAddress", newAddress);
        params.put("reason", reason);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
