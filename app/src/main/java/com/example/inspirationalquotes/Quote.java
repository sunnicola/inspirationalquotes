package com.example.inspirationalquotes;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Quote {

    @SerializedName("value")
    private String quote;


    public String getQuote() {
        return quote;
    }
}
