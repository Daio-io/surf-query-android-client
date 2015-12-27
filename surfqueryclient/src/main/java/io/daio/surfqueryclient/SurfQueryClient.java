package io.daio.surfqueryclient;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class SurfQueryClient {

    private static final String API_KEY_PARAM = "apikey";
    private static final String DEFAULT_BASE_URL = "http://surf-query.herokuapp.com/";

    private String apiKey;
    private String baseUrl;
    private SurfQueryHTTPClient httpClient;

    public SurfQueryClient(@NonNull String apiKey, @NonNull SurfQueryHTTPClient httpClient, @Nullable String baseUrl) {
        this.apiKey = apiKey;
        this.httpClient = httpClient;
        this.baseUrl = baseUrl != null ? baseUrl : DEFAULT_BASE_URL;
    }

}
