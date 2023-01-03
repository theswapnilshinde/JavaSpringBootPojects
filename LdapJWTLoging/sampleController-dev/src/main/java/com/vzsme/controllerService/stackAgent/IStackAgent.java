package com.vzsme.controllerService.stackAgent;

import com.vzsme.controllerService.controller.v1.request.SampleRequest;

public interface IStackAgent {

    void doSomethingAsync(SampleRequest request); // Async call
    boolean doSomethingSync(SampleRequest request); // Sync call
}
