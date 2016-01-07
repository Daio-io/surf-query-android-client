package io.daio.surfqueryclient;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SurfQueryClientTest extends TestCase {

    private SurfQueryResult mockResult;

    @Before
    public void setup() {
        mockResult = new SurfQueryResult(10000,
                "now", "12:12", 12, 10, 15, 2, 4);
    }

    @Test
    public void testMakeRequestIsSuccessful() throws Exception {

        SurfQueryRequest mockRequest = new SurfQueryRequest();
        mockRequest.withParam("start", "12");

        SurfQueryClient surfQueryClient = getMockSuccessfullClient();

        surfQueryClient.makeRequest(mockRequest, new OnSuccessListener() {
            @Override
            public void onSuccess(String url, List<SurfQueryResult> results) {
                assertNotNull(url);
                assertNotNull(results);
                assertEquals(mockResult, results.get(0));
            }
        }, null);
    }

    @Test
    public void testMakeRequestFails() throws Exception {

        SurfQueryRequest mockRequest = new SurfQueryRequest();
        mockRequest.withParam("start", "12");

        SurfQueryClient surfQueryClient = getMockFailureClient();

        surfQueryClient.makeRequest(mockRequest, null, new OnFailureListener() {
            @Override
            public void onFailure(String url, SurfQueryException exception) {
                assertNotNull(url);
                assertNotNull(exception);
            }
        });
    }

    private SurfResultTransformer getMockResultTransformer() {
        return new SurfResultTransformer() {
            @Override
            public List<SurfQueryResult> transform(String jsonBody)
                    throws SurfQueryException {

                ArrayList<SurfQueryResult> results = new ArrayList<SurfQueryResult>();
                results.add(mockResult);

                return results;
            }
        };
    }

    private SurfQueryClient getMockSuccessfullClient() {
        return new SurfQueryClient("api_key",
                getMockHttpClientSuccess(),
                null, getMockResultTransformer());
    }

    private SurfQueryClient getMockFailureClient() {
        return new SurfQueryClient("api_key",
                getMockHttpClientFailure(),
                null, getMockResultTransformer());
    }

    private SurfQueryHTTPClient getMockHttpClientSuccess() {
        return new SurfQueryHTTPClient() {
            @Override
            public void request(String url,
                                OnSuccessCallback onSuccessCallback,
                                OnFailureCallback onFailureCallback) {

                onSuccessCallback.onSuccess(url, "result body");
            }
        };
    }

    private SurfQueryHTTPClient getMockHttpClientFailure() {
        return new SurfQueryHTTPClient() {
            @Override
            public void request(String url,
                                OnSuccessCallback onSuccessCallback,
                                OnFailureCallback onFailureCallback) {

                onFailureCallback.onFailure(url, new IOException("Failed"));
            }
        };
    }
}