package io.daio.surfqueryclient;


import java.io.IOException;

public interface SurfQueryHTTPClient {

    interface OnFailureCallback {
        void onFailure(String url, IOException exception);
    }

    interface OnSuccessCallback {
        void onSuccess(String url, String resultBody);
    }

    void request(String url, OnSuccessCallback onSuccessCallback, OnFailureCallback onFailureCallback);

}
