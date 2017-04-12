package com.example.android.coms;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;


public class RegisterActivity extends AppCompatActivity {
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etName= (EditText) findViewById(R.id.etName);
        final EditText etUserName= (EditText) findViewById(R.id.etUserName);
        final EditText etPassword= (EditText) findViewById(R.id.etPassword);
        final EditText etMobileNumber= (EditText) findViewById(R.id.etMobileNumber);
        final EditText etEmail= (EditText) findViewById(R.id.etEmail);
        final EditText etAddress= (EditText) findViewById(R.id.etAddress);

        final Button bRegister = (Button) findViewById(R.id.bRegister);
         dialog = ProgressDialog.show(RegisterActivity.this, "",
                "Loading...", true);
        dialog.hide();
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = etName.getText().toString();
                final String username = etUserName.getText().toString();
                final String password = etPassword.getText().toString();
                final String mob_num = etMobileNumber.getText().toString();
                final String email = etEmail.getText().toString();
                final String address = etAddress.getText().toString();
                //**********   Error for null values on text fields
                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password)
                        || TextUtils.isEmpty(mob_num) || TextUtils.isEmpty(email) || TextUtils.isEmpty(address)
                        || name.length()<8 || username.length()<3 ||password.length() <6 || mob_num.length()<10
                        || !(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) || address.length() <15) {

                    if (TextUtils.isEmpty(name)) {
                        etName.setError("This field can't be empty");
                    } else if (name.length() < 8) {
                        etName.setError("You must have atleast 8 characters in your name");
                    }

                    if (TextUtils.isEmpty(username)) {
                        etUserName.setError("This field can't be empty");
                    } else if (username.length() < 3) {
                        etUserName.setError("You must have atleast 3 characters in your username");
                    }

                    if (TextUtils.isEmpty(password)) {
                        etPassword.setError("This field can't be empty");
                    } else if (password.length() < 6) {
                        etPassword.setError("You must have atleast 6 characters in your password");
                    }

                    if (TextUtils.isEmpty(mob_num)) {
                        etMobileNumber.setError("This field can't be empty");
                    } else if (mob_num.length() < 10) {
                        etMobileNumber.setError("Provide a valid mobile number");
                    }

                    if (TextUtils.isEmpty(email)) {
                        etEmail.setError("This field can't be empty");
                    } else if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
                        etEmail.setError("Invalid Email id");
                    }

                    if (TextUtils.isEmpty(address)) {
                        etAddress.setError("This field can't be empty");
                    } else if (address.length() < 15) {
                        etAddress.setError("Address too small");
                    }

                }
                //**********
                else {
                    dialog.show();

                    Response.Listener<String> responseListener = new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success) {
                                    Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    dialog.hide();
                                    RegisterActivity.this.startActivity(loginIntent);
                                    finish();

                                    Context context = getApplicationContext();
                                    CharSequence text = "Registerd Successfully!";
                                    int duration = Toast.LENGTH_SHORT;
                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                    finish();
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                    builder.setMessage("Register Failed")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                    dialog.hide();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    RegisterRequest registerRequest = new RegisterRequest(name, username, password, Long.parseLong(mob_num), email, address, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(registerRequest);
                }
            }
        });

    }
}
