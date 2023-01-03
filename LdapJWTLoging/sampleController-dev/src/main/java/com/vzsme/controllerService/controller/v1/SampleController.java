package com.vzsme.controllerService.controller.v1;


import com.vzsme.controllerService.controller.v1.request.SampleRequest;
import com.vzsme.controllerService.controller.v1.response.MessageResponse;
import com.vzsme.controllerService.service.SampleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
@RestController
@RequestMapping(UriConstantsV1.samples)
//@Api(tags = "Sample Service", description="API Commands")
public class SampleController extends BaseController {

    @Autowired
    private SampleService service;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // This is an example of a asynchronous API call.
    // It responds with the appropriate status code (202) with a response message.
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "async", method = RequestMethod.POST)
    @ResponseStatus( HttpStatus.ACCEPTED ) // 202 - Async call is accepted
    public MessageResponse asyncExample( // NOTE: The function name shows in Swagger Spec
           HttpServletRequest request,
           @Valid @RequestBody SampleRequest requestBody // @Valid can throw InvalidInputException
    ) {
        return service.asyncSample(request.getCookies(), requestBody);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // This is an example of a synchronous API call.
    // It responds with the appropriate status code (200) with a response message.
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "sync", method = RequestMethod.POST)
    @ResponseStatus( HttpStatus.OK ) // 200 - Sync call completed successfully
    public MessageResponse syncExample( // NOTE: The function name shows in Swagger Spec
            HttpServletRequest request,
            @Valid @RequestBody SampleRequest requestBody // @Valid can throw InvalidInputException
    ) {
        return service.syncSample(request.getCookies(), requestBody);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // This is an example of how to validate input information.
    // It responds with the appropriate status code and response message
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //@Secured("ROLE_ADMIN")
    //@Validated - DOES NOT WORK HERE - HAS TO BE ON CLASS
    @RequestMapping(value = "validate/{id}", method = RequestMethod.POST)
    public MessageResponse validateExample( // NOTE: The function name shows in Swagger Spec
            HttpServletRequest request,
            @PathVariable("id") @Min(5) int id, // EXAMPLE of path validation
            @RequestParam(value = "param", required = false) @Min(5) Integer param, // EXAMPLE of param validation
            @Valid @RequestBody SampleRequest requestBody
            //@Valid @RequestBody(required = true) Map<String, Object> requestBody
    ) {
        //throw new UnauthorizedException("TESTING NOT AUTHORIZED!!!");
        return service.errorSample(request.getCookies(), null);
    }

}
