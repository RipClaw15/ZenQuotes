package com.example.zenq;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DailyQuoteActivity extends AppCompatActivity {

    private TextView textViewQuote;
    private TextView textViewAuthor;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_quote);

        textViewQuote = findViewById(R.id.textViewQuote);
        textViewAuthor = findViewById(R.id.textViewAuthor);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        // Initialize a Volley request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = "https://zenquotes.io/api/today/[your_key]";

        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        progressDialog.dismiss();
                        try {
                            if (response.length() > 0) {
                                JSONObject quoteObject = response.getJSONObject(0);
                                String quote = quoteObject.getString("q");
                                String author = quoteObject.getString("a");

                                textViewQuote.setText(quote);
                                textViewAuthor.setText("- " + author);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(DailyQuoteActivity.this, "Error fetching the daily quote", Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the queue
        requestQueue.add(jsonArrayRequest);
    }
}