package com.apptrends.volleyandroid;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddProductsActivity extends AppCompatActivity {
    EditText name, email, gender, city, country;
    Button addBtn;
    ProgressDialog progressDialog;
    String nametxt, emailtxt, gendertxt, citytxt, countrytxt,insert;
    String Url = "https://volleyandroid.000webhostapp.com/abdulrehmanvolley/insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        name = findViewById(R.id.nameAdd);
        email = findViewById(R.id.emailAdd);
        gender = findViewById(R.id.genderAdd);
        city = findViewById(R.id.cityAdd);
        country = findViewById(R.id.countryAdd);
        progressDialog = new ProgressDialog(this);
        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nametxt = name.getText().toString().trim();
                emailtxt = email.getText().toString().trim();
                gendertxt = gender.getText().toString().trim();
                citytxt = city.getText().toString().trim();
                countrytxt = country.getText().toString().trim();

                if (TextUtils.isEmpty(nametxt) || TextUtils.isEmpty(emailtxt) || TextUtils.isEmpty(gendertxt) || TextUtils.isEmpty(citytxt) || TextUtils.isEmpty(countrytxt)) {
                    Toast.makeText(AddProductsActivity.this, "Required.", Toast.LENGTH_SHORT).show();
                } else {
                    AddData();
                }
            }
        });
    }

    private void AddData() {
        // Showing progress dialog at user registration time.
        progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
        progressDialog.show();

        // Calling method to get value from EditText.
        GetValueFromEditText();

        // Creating string request with post method.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {

                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();

                        // Showing response message coming from server.
                        Toast.makeText(AddProductsActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();

                        // Showing error message if something goes wrong.
                        Toast.makeText(AddProductsActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                // Adding All values to Params.
                params.put("insert",insert);
                params.put("name",nametxt);
                params.put("email", emailtxt);
                params.put("gender", gendertxt);
                params.put("city", citytxt);
                params.put("country", countrytxt);

                return params;
            }

        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);
    }
    // Creating method to get value from EditText.
    public void GetValueFromEditText(){

        insert = "insert";
        nametxt = name.getText().toString().trim();
        emailtxt = email.getText().toString().trim();
        gendertxt = gender.getText().toString().trim();
        citytxt = city.getText().toString().trim();
        countrytxt = country.getText().toString().trim();

    }
}