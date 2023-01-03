package com.vzsme.commonServiceLib.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vzsme.commonServiceLib.exception.ApiException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class RestClient {


    public static Map<String, Object> get2(String url) throws Exception {
        return get2(url, null);
    }
    public static Map<String, Object> get2(String url, String body) throws Exception {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpUriRequest request = RequestBuilder.get(url)
                    .setEntity(new StringEntity(body == null ? "{}" : body))
                    .setHeader("content-type", "application/json")
                    .build();

            System.out.println(String.format("GET: %s", url));
            HttpResponse response = httpClient.execute(request);
            return ValidateJsonResponse2(response);

        } finally {
            try {
                httpClient.close();
            } catch(IOException ex) {
                // TODO: Log an warning!
            }
        }
    }

    public static Map<String, Object> post2(String url, String body) throws Exception {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpUriRequest request = RequestBuilder.post(url)
                    .setEntity(new StringEntity(body == null ? "{}" : body))
                    .setHeader("content-type", "application/json")
                    .build();

            System.out.println(String.format("POST: %s", url));
            HttpResponse response = httpClient.execute(request);
            return ValidateJsonResponse2(response);

        } finally {
            try {
                httpClient.close();
            } catch(IOException ex) {
                // TODO: Log an warning!
            }
        }
    }


    public static Map<String, Object> put2(String url, String body) throws Exception {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpUriRequest request = RequestBuilder.post(url)
                    .setEntity(new StringEntity(body == null ? "{}" : body))
                    .setHeader("content-type", "application/json")
                    .build();

            System.out.println(String.format("PUT: %s", url));
            HttpResponse response = httpClient.execute(request);
            return ValidateJsonResponse2(response);
        } finally {
            try {
                httpClient.close();
            } catch(IOException ex) {
                // TODO: Log warning!
            }
        }
    }

    private static Map<String, Object> ValidateJsonResponse2(HttpResponse response) throws Exception {
        int statusCode = response.getStatusLine().getStatusCode();
        String entity = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

        if (statusCode >= 200 && statusCode < 300) { // Success is all 200 level status codes
            return new ObjectMapper().readValue(entity, LinkedHashMap.class);
        } else {
            throw new ApiException(statusCode, entity);
        }
    }

}
