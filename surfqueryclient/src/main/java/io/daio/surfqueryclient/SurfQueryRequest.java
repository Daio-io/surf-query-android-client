package io.daio.surfqueryclient;


import android.support.annotation.NonNull;

import java.util.HashMap;

public final class SurfQueryRequest {

    private HashMap<String, String> params;

    public SurfQueryRequest() {
        this.params = new HashMap<>();
    }

    public SurfQueryRequest withParam(@NonNull String key, @NonNull String value) {
        this.params.put(key, value);
        return this;
    }

    public HashMap<String, String> getParams() {
        return this.params;
    }


}
