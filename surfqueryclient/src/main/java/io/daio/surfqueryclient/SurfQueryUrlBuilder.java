package io.daio.surfqueryclient;

import java.util.HashMap;


public final class SurfQueryUrlBuilder {

    public String build(String baseUrl, SurfQueryRequest surfQueryRequest) {

        HashMap<String, String> requestParams = surfQueryRequest.getParams();

        String builtParams = "";

        for (String paramKey : requestParams.keySet()) {

            String param = requestParams.get(paramKey);
            if (builtParams.isEmpty()) {
                builtParams += "?" + paramKey + "=" + param;
            } else {
                builtParams += "&" + paramKey + "=" + param;
            }
        }
        return baseUrl + builtParams;
    }

}
