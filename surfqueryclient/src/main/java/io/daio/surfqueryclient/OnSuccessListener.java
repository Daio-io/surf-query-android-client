package io.daio.surfqueryclient;

import android.support.annotation.NonNull;

import java.util.List;

public interface OnSuccessListener {

    void onSuccess(String url, @NonNull List<SurfQueryResult> results);

}
