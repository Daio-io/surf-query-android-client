package io.daio.surfqueryclient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class DefaultResultTransformer implements SurfResultTransformer {

    public List<SurfQueryResult> transform(String responseBody) throws SurfQueryException {

        ArrayList<SurfQueryResult> results = new ArrayList<>();

        try {
            JSONObject responseObj = new JSONObject(responseBody);

            if (responseObj.getString("status").equals("failed")) {
                throw new SurfQueryException("Surf Query Client Results Transform failed. " +
                        "JSON Response error: " + responseObj.optString("message"));
            }

            JSONArray jsonArray = responseObj.getJSONArray("response");
            for (int i = 0, len = jsonArray.length(); i < len; i++) {
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
        } catch (JSONException e) {
            throw new SurfQueryException(e.getMessage(), e);
        }

        return results;

    }

}
