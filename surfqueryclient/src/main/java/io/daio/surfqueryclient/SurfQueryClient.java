package io.daio.surfqueryclient;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.List;

public final class SurfQueryClient {

    private static final String API_KEY_PARAM = "apikey";
    private static final String DEFAULT_BASE_URL = "http://surf-query.herokuapp.com/";

    private String apiKey;
    private String baseUrl;
    private SurfQueryHTTPClient httpClient;
    private SurfQueryUrlBuilder urlBuilder;
    private final SurfResultTransformer resultTransformer;

    public SurfQueryClient(@NonNull String apiKey,
                           @NonNull SurfQueryHTTPClient httpClient,
                           @Nullable String baseUrl,
                           @Nullable SurfResultTransformer resultTransformer) {

        this.apiKey = apiKey;
        this.httpClient = httpClient;
        this.baseUrl = baseUrl != null ? baseUrl : DEFAULT_BASE_URL;
        this.urlBuilder = new SurfQueryUrlBuilder();
        this.resultTransformer = resultTransformer != null ? resultTransformer : new DefaultResultTransformer();
    }

    public void makerequest(@NonNull SurfQueryRequest surfQueryRequest,
                            final OnSuccessListener onSuccessListener,
                            final OnFailureListener onFailureListener) {

        surfQueryRequest.withParam(API_KEY_PARAM, this.apiKey);
        String stringRequest = urlBuilder.build(this.baseUrl, surfQueryRequest);

        this.httpClient.request(stringRequest, new SurfQueryHTTPClient.OnSuccessCallback() {

            @Override
            public void onSuccess(String url, String resultBody) {
                try {
                    if (onSuccessListener != null) {
                        List<SurfQueryResult> results = resultTransformer.transform(resultBody);
                        onSuccessListener.onSuccess(url, results);
                    }
                } catch (SurfQueryException exception) {
                    if (onFailureListener != null) {
                        onFailureListener.onFailure(url, exception);
                    }
                }
            }

        }, new SurfQueryHTTPClient.OnFailureCallback() {

            @Override
            public void onFailure(String url, IOException exception) {
                if (onFailureListener != null) {
                    onFailureListener.onFailure(url, new SurfQueryException(exception.getMessage(), exception));
                }
            }
        });

    }

}
