package io.daio.surfqueryclient;

import junit.framework.TestCase;

import org.junit.Test;

public class SurfQueryUrlBuilderTest extends TestCase {

    private static String BASE_URL = "http://some_base_url.co.uk";
    private static String SPOT_ID = "1449";

    @Test
    public void testBuildingUrlSingleParam() throws Exception {

        SurfQueryRequest surfQueryRequest = new SurfQueryRequest();
        surfQueryRequest.withParam(SurfQueryRequest.SPOT_ID_PARAM, SPOT_ID);

        SurfQueryUrlBuilder surfQueryUrlBuilder = new SurfQueryUrlBuilder();

        String expected = BASE_URL + "?" + SurfQueryRequest.SPOT_ID_PARAM + "=" + SPOT_ID;
        String result = surfQueryUrlBuilder.build(BASE_URL, surfQueryRequest);

        assertEquals(expected, result);
    }

    @Test
    public void testBuildingUrlMultiParam() throws Exception {

        String maxSwell = "12";
        String minSwell = "2";

        SurfQueryRequest surfQueryRequest = new SurfQueryRequest();
        surfQueryRequest.withParam(SurfQueryRequest.SPOT_ID_PARAM, SPOT_ID)
                .withParam(SurfQueryRequest.MAX_SWELL_PARAM, maxSwell)
                .withParam(SurfQueryRequest.MIN_SWELL_PARAM, minSwell);

        SurfQueryUrlBuilder surfQueryUrlBuilder = new SurfQueryUrlBuilder();

        String expected = BASE_URL + "?" + SurfQueryRequest.MIN_SWELL_PARAM + "=" + minSwell +
                "&" + SurfQueryRequest.MAX_SWELL_PARAM + "=" + maxSwell +
                "&" + SurfQueryRequest.SPOT_ID_PARAM + "=" + SPOT_ID;

        String result = surfQueryUrlBuilder.build(BASE_URL, surfQueryRequest);

        assertEquals(expected, result);
    }
}