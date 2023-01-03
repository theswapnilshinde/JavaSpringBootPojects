package com.vzsme.controllerService.service.client;

import java.util.Map;


//////////////////////////////////////////////////////////////////////////////////////////////////////////////
// This is an example service client. There are times when your service needs to send API request
// to another service. This class is an example of how that can be accomplished. This class is
// expecting a host url to be given. You should add an application property for this.
// Here is an example property:
// example-service-base-url=http://localhost:9999/example-service
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class ExampleServiceClient extends BaseServiceClient {

    private final String uriVersion = "/api/v1/version";
    private final String uriGet     = "/api/v1/apps/get-something";
    private final String uriPut     = "/api/v1/apps/put-something";
    private final String uriPost    = "/api/v1/apps/post-something";

    public ExampleServiceClient(String host) {
        super(host);
    }

    ///
    /// Get Service Version Information
    ///
    public Map<String, Object> getVersion() throws Exception {
        String url = buildUrl(uriVersion);
        return get2(url);
    }

    ///
    /// Get Example
    ///
    public Map<String, Object> getExample() throws Exception {
        String url = buildUrl(uriGet);
        return get2(url);
    }

    ///
    /// Put Example
    ///
    public Map<String, Object> putExample(String body) throws Exception {
        String url = buildUrl(uriPut);
        return put2(url, body);
    }

    ///
    /// Post Example
    ///
    public Map<String, Object> postExample(String body) throws Exception {
        String url = buildUrl(uriPost);
        return post2(url, body);
    }
}

