package com.vzsme.controllerService.stackAgent.vendor;

import com.vzsme.controllerService.controller.v1.request.SampleRequest;
import com.vzsme.controllerService.stackAgent.IStackAgent;

public abstract class BaseStackAgent implements IStackAgent {

    public void doSomethingAsync(SampleRequest request) { throw new java.lang.UnsupportedOperationException();  }
    public boolean doSomethingSync(SampleRequest request) { throw new java.lang.UnsupportedOperationException();  }

}