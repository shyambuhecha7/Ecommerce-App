package com.app.ecommerceapp.volly;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.ecommerceapp.R;

import org.json.JSONException;
import org.json.JSONObject;

public class VollyAPIActivity extends AppCompatActivity {

    Button btnStringRequest, btnJsonRequest, btnImageRequest, btnPostRequest;
    TextView response;
    ImageView responseImage;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volly_apiactivity);

        btnStringRequest = findViewById(R.id.btn_str_request);
        btnJsonRequest = findViewById(R.id.btn_json_request);
        btnImageRequest = findViewById(R.id.btn_image_request);
        btnPostRequest = findViewById(R.id.btn_post_request);
        responseImage = findViewById(R.id.img_response);
        response = findViewById(R.id.response);

        queue = Volley.newRequestQueue(this);

        btnStringRequest.setOnClickListener(v -> stringRequest());

        btnJsonRequest.setOnClickListener(v -> jsonRequest());

        btnImageRequest.setOnClickListener( v -> imageRequest());

        btnPostRequest.setOnClickListener(v -> postRequest());


    }

    private void stringRequest() {
        String url = "https://reqres.in/api/users/2";
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                response.setText(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                response.setText(volleyError.toString());
            }
        });
        queue.add(stringRequest);
    }

    private void jsonRequest() {
        String url = "https://reqres.in/api/users/2";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONObject object = jsonObject.getJSONObject("data");
                    String fullName = object.getString("first_name") + " " + object.getString("last_name");
                    response.setText(fullName);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        queue.add(jsonObjectRequest);
    }

    private void imageRequest() {
        String url = "https://reqres.in/img/faces/2-image.jpg";

        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                responseImage.setImageBitmap(bitmap);
            }
        }, 400, 400, ImageView.ScaleType.FIT_XY, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        queue.add(imageRequest);
    }

    //Create User using Post Request. Send user information to server and get response from server
    private void postRequest() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name","morpheus");
            jsonObject.put("job","leader");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        String url = "https://reqres.in/api/users";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    String result = "ID : " + jsonObject.getString("id") + "\n" +  "Name : " + jsonObject.getString("name") + "\n" +  "Job : " + jsonObject.getString("job");
                    response.setText(result);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                response.setText(volleyError.toString());
            }
        });
        queue.add(jsonObjectRequest);
    }
}