package io.daio.surfqueryclient;


public interface OnFailureListener {

    void onFailure(String url, Exception exception);

}
