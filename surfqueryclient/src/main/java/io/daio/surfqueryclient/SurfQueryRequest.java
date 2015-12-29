package io.daio.surfqueryclient;


import android.support.annotation.NonNull;

import java.util.HashMap;

public final class SurfQueryRequest {

    /**
     * Parameter key to set the spot / beach id to submit the query against
     *
     * e.g. spotid=1449 will return results for Rest Bay
     */
    public static final String SPOT_ID_PARAM = "spotid";

    /**
     * Parameter key to set the maximum swell range in results.
     *
     * e.g. maxswell = 10 will only return results with a swell up to and inclusive of 10
     */
    public static final String MAX_SWELL_PARAM = "maxswell";

    /**
     * Parameter key to set the minimum swell range in results.
     *
     * e.g. minswell=2 will only return results with a swell above and inclusive of 2
     */
    public static final String MIN_SWELL_PARAM = "minswell";

    /**
     * Set the start time you want results from
     *
     * e.g. setting start=8 will only return results starting from 8am on that day.
     * Clock is 24 hour in the query
     */
    public static final String START_TIME_PARAM = "start";

    /**
     * Set the end time you want results from
     *
     * e.g. setting end=22 will only return results up to 10pm on that day.
     * Clock is 24 hour in the query
     */
    public static final String END_TIME_PARAM = "end";

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
