package com.example.android.coms;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
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
/**
 * Created by Knayak on 04/04/2017.
 */

public class ChangeDetails extends Fragment implements View.OnClickListener {
    private ProgressBar cprogress;
    EditText etChangeName, etChangeUsername, etChangePassword, etChangeMobileNumber, etChangeEmail, etChangeAddress;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.change_details, container, false);
        // Inflate the layout for this fragment
        Button bChangeSave = (Button) view.findViewById(R.id.bChangeSave);
        Button bChangeBack = (Button) view.findViewById(R.id.bChangeBack);

        UserDetails ud= new UserDetails();
        String name= ud.getName();
        String username= ud.getUsername();
        String password= ud.getPassword();
        Long mob_num= ud.getMob_num();
        String email= ud.getEmail();
        String address= ud.getAddress();
        cprogress = (ProgressBar) view.findViewById(R.id.progressBar3);
        cprogress.setVisibility(View.GONE);

        //*************  Displaying existing details in database
        etChangeName = (EditText) view.findViewById(R.id.etChangeName);
        etChangeName.setText(name);
        etChangeUsername = (EditText) view.findViewById(R.id.etChangeUsername);
        etChangeUsername.setText(username);
        etChangePassword = (EditText) view.findViewById(R.id.etChangePassword);
        etChangePassword.setText(password);
        etChangeMobileNumber = (EditText) view.findViewById(R.id.etChangeMobileNumber);
        etChangeMobileNumber.setText(mob_num + "");
        etChangeEmail = (EditText) view.findViewById(R.id.etChangeEmail);
        etChangeEmail.setText(email);
        etChangeAddress = (EditText) view.findViewById(R.id.etChangeAddress);
        etChangeAddress.setText(address);
        //**************
        bChangeBack.setOnClickListener(this);
        bChangeSave.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.bChangeBack:
                Fragment myAccount1 = new MyAccount();
                fragmentTransaction.replace(R.id.frame_container, myAccount1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.bChangeSave:
               //************** Updating user details in database
                long user_id= UserDetails.getUser_id();
                    String name = etChangeName.getText().toString();
                    String username = etChangeUsername.getText().toString();
                    String password = etChangePassword.getText().toString();
                    String mob_num = etChangeMobileNumber.getText().toString();
                    String email = etChangeEmail.getText().toString();
                    String address = etChangeAddress.getText().toString();
                    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password)
                            || TextUtils.isEmpty(mob_num) || TextUtils.isEmpty(email) || TextUtils.isEmpty(address)
                            || name.length() < 8 || username.length() < 3 || password.length() < 6 || mob_num.length() < 10
                            || !(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) || address.length() < 15) {

                        if (TextUtils.isEmpty(name)) {
                            etChangeName.setError("This field can't be empty");
                        } else if (name.length() < 8) {
                            etChangeName.setError("You must have at least 8 characters in your name");
                        }

                        if (TextUtils.isEmpty(username)) {
                            etChangeUsername.setError("This field can't be empty");
                        } else if (username.length() < 3) {
                            etChangeUsername.setError("You must have at least 3 characters in your username");
                        }

                        if (TextUtils.isEmpty(password)) {
                            etChangePassword.setError("This field can't be empty");
                        } else if (password.length() < 6) {
                            etChangePassword.setError("You must have at least 6 characters in your password");
                        }

                        if (TextUtils.isEmpty(mob_num)) {
                            etChangeMobileNumber.setError("This field can't be empty");
                        } else if (mob_num.length() < 10) {
                            etChangeMobileNumber.setError("Provide a valid mobile number");
                        }

                        if (TextUtils.isEmpty(email)) {
                            etChangeEmail.setError("This field can't be empty");
                        } else if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
                            etChangeEmail.setError("Invalid Email id");
                        }

                        if (TextUtils.isEmpty(address)) {
                            etChangeAddress.setError("This field can't be empty");
                        } else if (address.length() < 15) {
                            etChangeAddress.setError("Address too small");
                        }

                    } else {
                        cprogress.setVisibility(View.VISIBLE);

                        Response.Listener<String> responseListener = new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");

                                    if (success) {
                                        Intent myIntent = new Intent(getActivity(), LoginActivity.class);
                                        getActivity().startActivity(myIntent);
                                        getActivity().finish();
                                        Context context = getContext();
                                        CharSequence text = "Updated Successfully. Please Login again!";
                                        int duration = Toast.LENGTH_SHORT;
                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.show();
                                        cprogress.setVisibility(View.GONE);

                                    } else {
                                        Context context = getContext();
                                        CharSequence text = "Update unsuccessful!";
                                        int duration = Toast.LENGTH_SHORT;
                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };

                        ChangeDetailsRequest changeDetailsRequest = new ChangeDetailsRequest(user_id, name, username, password, Long.parseLong(mob_num), email, address, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(getContext());
                        queue.add(changeDetailsRequest);
                    }

                break;

            //**************
        }
    }
}
