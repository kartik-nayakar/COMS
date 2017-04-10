package com.example.android.coms;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.android.coms.R.id.bRequest;
import static com.example.android.coms.R.id.bTransfer;
import static com.example.android.coms.R.id.edit_query;
import static com.example.android.coms.R.id.etReason;

/**
 * Created by Knayak on 28/02/2017.
 */

public class Transfer extends Fragment implements View.OnClickListener{
    ProgressBar cprogress;
    EditText etNewAddress,etReason;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transfer,container,false);
        etNewAddress = (EditText) view.findViewById(R.id.etNewAddress);
        etReason = (EditText) view.findViewById(R.id.etReason);
        Button bRequest = (Button) view.findViewById(R.id.bRequest);
        bRequest.setOnClickListener(this);
        cprogress = (ProgressBar) view.findViewById(R.id.progressBar4);
        cprogress.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void onClick(View v) {
        String newAddress = etNewAddress.getText().toString();
        String reason = etReason.getText().toString();
        if(TextUtils.isEmpty(newAddress) || TextUtils.isEmpty(reason) || etNewAddress.length()<15 || etReason.length()<10) {
            if (TextUtils.isEmpty(newAddress)) {
                etNewAddress.setError("This can't be empty!");
            } else if (etNewAddress.length() < 15)
                etNewAddress.setError("Address too small");
            if (TextUtils.isEmpty(reason)) {
                etReason.setError("This field can't be empty!");
            } else if (etReason.length() < 10) {
                etReason.setError("Length too small");
            }
        }
        else {
            cprogress.setVisibility(View.VISIBLE);

            Response.Listener<String> responseListener = new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    try {

                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");

                        if (success) {
                            Intent myIntent = new Intent(getActivity(), HomeActivity.class);
                            getActivity().startActivity(myIntent);
                            getActivity().finish();
                            cprogress.setVisibility(View.GONE);
                            Context context = getContext();
                            CharSequence text = "Request Sent!";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        } else {
                            Context context = getContext();
                            cprogress.setVisibility(View.GONE);
                            CharSequence text = "Request Failed!";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            TransferRequest transferRequest = new TransferRequest(newAddress, reason, responseListener);
            RequestQueue queue = Volley.newRequestQueue(getContext());
            queue.add(transferRequest);
        }
    }
}
