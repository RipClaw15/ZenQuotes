package com.example.zenq;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

public class RandomQuotesActivity extends AppCompatActivity {

    private ListView listViewQuotes;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_quotes);

        listViewQuotes = findViewById(R.id.listViewQuotes);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        // Initialize a Volley request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = "https://zenquotes.io/api/quotes/[your_key]";

        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        progressDialog.dismiss();
                        try {
                            // Create an array of quotes
                            String[] quotes = new String[response.length()];

                            // Populate the array with quotes
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject quoteObject = response.getJSONObject(i);
                                String quote = quoteObject.getString("q");
                                String author = quoteObject.getString("a");
                                quotes[i] = "\"" + quote + "\" - " + author;
                            }

                            // Create an adapter to display the quotes in the ListView
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(RandomQuotesActivity.this,
                                    android.R.layout.simple_list_item_1, quotes);

                            listViewQuotes.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(RandomQuotesActivity.this, "Error fetching random quotes", Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the queue
        requestQueue.add(jsonArrayRequest);
    }
}
