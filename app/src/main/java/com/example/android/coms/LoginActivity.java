package com.example.android.coms;

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
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private ProgressBar cprogress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername= (EditText) findViewById(R.id.etUsername);
        final EditText etPassword= (EditText) findViewById(R.id.etPassword);

        final Button bLogin= (Button) findViewById(R.id.bLogin);
        cprogress = (ProgressBar)findViewById(R.id.progressBar);
        cprogress.setVisibility(View.GONE);
        final TextView tvRegister = (TextView) findViewById(R.id.tvRegister);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    etUsername.setError("This field can't be empty");
                }
                if (TextUtils.isEmpty(password)) {
                    etPassword.setError("This field can't be empty");
                } else {
                    cprogress.setVisibility(View.VISIBLE);
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    // ******************** Getting user data from DB and saving it in UserDetails class
                                    String jname = jsonResponse.getString("name");
                                    String jusername = jsonResponse.getString("username");
                                    String jpassword = jsonResponse.getString("password");
                                    long jmob_num = jsonResponse.getLong("mob_num");
                                    String jemail = jsonResponse.getString("email");
                                    String jaddress = jsonResponse.getString("address");
                                    UserDetails ud = new UserDetails();
                                    ud.name = jname;
                                    ud.username = jusername;
                                    ud.password = jpassword;
                                    ud.mob_num = jmob_num;
                                    ud.email = jemail;
                                    ud.address = jaddress;
                                    //********************* Display Home Activity
                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    cprogress.setVisibility(View.GONE);
                                    LoginActivity.this.startActivity(intent);
                                    finish();
                                    //********************   TOAST
                                    Context context = getApplicationContext();
                                    CharSequence text = "Welcome user!";
                                    int duration = Toast.LENGTH_SHORT;
                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                    //*******************
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                    builder.setMessage("Login Failed!")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                    cprogress.setVisibility(View.GONE);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                    queue.add(loginRequest);
                }
            }
        });
        }
}

