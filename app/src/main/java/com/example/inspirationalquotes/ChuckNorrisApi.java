package com.example.inspirationalquotes;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ChuckNorrisApi {

    //No body in getQuote(); method as this is interface
    //Define endpoints
    //GET annotation to tell Retrofit to get data
    //Parameter being the relative URL - /jokes/random
    @GET("/jokes/random?category=dev")
    //Call object used to execute GET request
    Call<Quote> getQuotes();
}
