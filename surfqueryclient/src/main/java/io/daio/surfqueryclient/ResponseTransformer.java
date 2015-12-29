package io.daio.surfqueryclient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class ResponseTransformer {

    public List<SurfQueryResult> transform(String responseBody) throws JSONException {

        ArrayList<SurfQueryResult> results = new ArrayList<>();

        JSONObject responseObj = new JSONObject(responseBody);

        if (responseObj.optJSONArray("response") == null) {
            return results;
        }
        JSONArray jsonArray = responseObj.getJSONArray("response");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);

            long timestamp = object.getLong("timestamp");
            String date = object.getString("date");
            String time = object.getString("time");
            int wind = object.getInt("wind");
            int minSwell = object.getInt("minSwell");
            int maxSwell = object.getInt("maxSwell");
            int solidStar = object.getInt("solidStar");
            int fadedStar = object.getInt("fadedStar");

            SurfQueryResult result = new SurfQueryResult(timestamp, date, time, wind,
                    minSwell, maxSwell, solidStar, fadedStar);

            results.add(result);
        }

        return results;

    }

}
