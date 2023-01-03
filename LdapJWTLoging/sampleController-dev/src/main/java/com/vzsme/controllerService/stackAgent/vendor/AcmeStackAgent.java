package com.vzsme.controllerService.stackAgent.vendor;


import com.vzsme.controllerService.controller.v1.request.SampleRequest;

public class AcmeStackAgent extends BaseStackAgent {

    public void doSomethingAsync(SampleRequest request) {
        this.doSomething();
    }

    public boolean doSomethingSync(SampleRequest request) {
        return this.doSomething();
    }


    private boolean doSomething() {
        boolean resp = false;
        try {
            this.wait(3000);
            resp = true;
        } catch (Exception ignore) {

        }
        return resp;
    }

}
