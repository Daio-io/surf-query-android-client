package io.daio.surfquerysample.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;

import io.daio.surfqueryclient.SurfQueryHTTPClient;

public class SurfHttpClient implements SurfQueryHTTPClient {
    private Context mContext;

    public SurfHttpClient(Context context) {
        this.mContext = context;
    }

    @Override
    public void request(final String url,
                        final OnSuccessCallback onSuccessCallback,
                        final OnFailureCallback onFailureCallback) {


        RequestQueue queue = Volley.newRequestQueue(mContext);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onSuccessCallback.onSuccess(url, response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        onFailureCallback.onFailure(url, new IOException(error.getMessage()));
                    }

                });

        queue.add(stringRequest);

    }
}
