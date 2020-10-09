package com.example.jsonparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends AppCompatActivity {

    Button button_1;
    ImageView image_1;
    EditText edit_text;
    TextView text_movie_name, text_year, text_genre, text_plot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_1 = findViewById(R.id.button_1);
        image_1 = findViewById(R.id.image_1);
        edit_text = findViewById(R.id.edit_text);
        text_movie_name = findViewById(R.id.text_movie_name);
        text_genre = findViewById(R.id.text_genre);
        text_year = findViewById(R.id.text_year);
        text_plot = findViewById(R.id.text_plot);


        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "http://www.omdbapi.com/?t=" + edit_text.getText().toString().replaceAll(" ", "%20") + "&apikey=5b293220";
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Picasso.get().load(response.getString("Poster")).into(image_1);
                            text_movie_name.setText(response.getString("Title"));
                            text_genre.setText(response.getString("Genre"));
                            text_year.setText(response.getString("Released"));
                            text_plot.setText(response.getString("Plot"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });

                requestQueue.add(jsonObjectRequest);

            }
        });


    }
}