package com.example.inspirationalquotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity {

    private Button buttonQuote;
    private TextView tvQuote;
    private ImageView share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonQuote = findViewById(R.id.btn_quote);
        tvQuote = findViewById(R.id.tv_quote);
        share = findViewById(R.id.btn_share);

        Gson gson = new Gson();
        buttonQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create instance of Retrofit
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.chucknorris.io")
                        //to indicate we want GSON to parse the response from the API
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                //Calls upon the Call method in ChuckNorrisApi interface
                ChuckNorrisApi chuckNorrisApi = retrofit.create(ChuckNorrisApi.class);
                //execute request using Call object
                Call<Quote> quoteCall = chuckNorrisApi.getQuotes();
                quoteCall.enqueue(new Callback<Quote>() {
                    @Override
                    public void onResponse(Call<Quote> call, Response<Quote> response) {
                        if (response.isSuccessful()) {
                            tvQuote.setText(String.valueOf(response.body().getQuote()));
                        }
                    }

                    @Override
                    //When something goes wrong in the server, fail to get response
                    public void onFailure(Call<Quote> call, Throwable t) {

                    }
                });
            }
        });
        //Share button allows users to share quotes/jokes that have inspired them
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, String.valueOf(tvQuote));
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
        }
    }
