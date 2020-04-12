package com.example.inspirationalquotes;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Quote {

    //API uses value as the string name so need to annotate with Serialized
    @SerializedName("value")
    private String quote;

    public String getQuote() {
        return quote;
    }
}
