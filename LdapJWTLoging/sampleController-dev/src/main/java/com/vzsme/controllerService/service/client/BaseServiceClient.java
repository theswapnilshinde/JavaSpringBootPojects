package com.vzsme.controllerService.service.client;

import com.vzsme.commonServiceLib.utils.RestClient;

public abstract class BaseServiceClient extends RestClient {

    protected String serviceBaseUrl;

    protected BaseServiceClient(String host) {
        if (host == null || host.trim().length() == 0) {
            throw new RuntimeException("Cannot access service. Missing host configuration.");
        }

        if (!host.startsWith("http")) {
            host = "http://" + host.trim();
        }
        // Ensure the base url does not have a trailing slash
        serviceBaseUrl = host.trim().replaceAll("/+$", "");;
    }

    protected String buildUrl(String uri) throws Exception {
        return serviceBaseUrl + uri;
    }
}
