package com.vzsme.controllerService.service;

import com.vzsme.commonServiceLib.exception.SystemException;
import com.vzsme.controllerService.controller.v1.request.SampleRequest;
import com.vzsme.controllerService.controller.v1.response.MessageResponse;
import com.vzsme.commonServiceLib.exception.DbQueryException;
import com.vzsme.controllerService.stackAgent.StackAgentFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import java.util.Map;

@Service
public class SampleService extends BaseService {

    // was setting authenticate to false at server start
    @PostConstruct
    public void init() {
        //authenticate = true;
    }


    public MessageResponse asyncSample(Cookie[] cks, SampleRequest request) {
        // props are being set from application properties file
        String vendor = super.appConfig.props.getVendor();

        // checked 401 by setting authenticate variable to true
        // Validate User - throws 401 UNAUTHORIZED
        this.validateUser(cks);

        // request from postman(in body) should be equal to specified string value("acme_db") below
        // Validate Input - throws 400 BAD_REQUEST
       // if(!request.getDbInfo().getDbUsr().equalsIgnoreCase("acme_db"))
            request.validateInput();

        //in thread when vendor is correctly set in properties file it will work
        //
        // Process the request on another thread
        //
        new Thread() {
            public void run() {
                System.out.println(String.format("**** Received request: asyncSample"));
                StackAgentFactory.StackAgent(vendor).doSomethingAsync(request);
                System.out.println(String.format("**** Request complete: Async Done!"));
            }
        }.start();

        // irrespective of anything this response will always be sent
        // Return response to request.
        return new MessageResponse(String.format("Starting asyncSample request"));
    }


    public MessageResponse syncSample(Cookie[] cks, SampleRequest request) {
        String vendor = super.appConfig.props.getVendor();

        // Validate User - throws 401 UNAUTHORIZED
        this.validateUser(cks);

        // Validate Input - throws 400 BAD_REQUEST
        request.validateInput();

        System.out.println(String.format("**** Received request: syncSample"));

        //
        // Process the request - wait for response
        //
        boolean resp = StackAgentFactory.StackAgent(vendor).doSomethingSync(request);
        if (!resp) {
            throw new SystemException(String.format("Failed to do something")); // 500
        }

        System.out.println(String.format("**** Request complete: Done doing something"));
        return new MessageResponse(String.format("Successfully did something!"));
    }


    public MessageResponse errorSample(Cookie[] cks, Map<String, Object> attachInput) {
       // Make sure we have a valid user making the request
        validateUser(cks);

        try {
            int x = 5;
            if (x == 5)
                throw new Exception("This is my sql query exception");
        } catch (Exception e) {
            throw new DbQueryException("SQL TESTING: Failed to do something.", e);
        }

        // Build the response
        return new MessageResponse(">>>> Testing Successful! <<<<");
    }

}

