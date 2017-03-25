package com.example.android.coms;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername= (EditText) findViewById(R.id.etUsername);
        final EditText etPassword= (EditText) findViewById(R.id.etPassword);

        final Button bLogin= (Button) findViewById(R.id.bLogin);
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
                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success) {
                                // ******************** Getting uer data from DB and saving it in UserDetails class
                                String name = jsonResponse.getString("name");
                                String username = jsonResponse.getString("username");
                                String password = jsonResponse.getString("password");
                                long mob_num = jsonResponse.getLong("mob_num");
                                String email = jsonResponse.getString("email");
                                String address = jsonResponse.getString("address");
                                UserDetails ud = new UserDetails();
                                ud.name=name;
                                ud.username=username;
                                ud.password=password;
                                ud.mob_num=mob_num;
                                ud.email=email;
                                ud.address=address;
                                //*********************
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                LoginActivity.this.startActivity(intent);
                                finish();


                                Context context = getApplicationContext();
                                CharSequence text = "Welcome user!";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                            }else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Invalid Username or Password!")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
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
        });

    }
}
