package io.daio.surfqueryclient;

import android.support.annotation.NonNull;

import java.util.List;

public interface OnSuccessListener {

    void onSuccess(String url, List<SurfQueryResult> results);

}
