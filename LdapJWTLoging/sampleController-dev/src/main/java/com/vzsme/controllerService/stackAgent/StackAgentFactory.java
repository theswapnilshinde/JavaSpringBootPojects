package com.vzsme.controllerService.stackAgent;

import com.vzsme.commonServiceLib.exception.SystemException;
import com.vzsme.controllerService.stackAgent.vendor.GlobexStackAgent;
import com.vzsme.controllerService.stackAgent.vendor.AcmeStackAgent;

public class StackAgentFactory {

    public static IStackAgent StackAgent(String vendor) {

        if (vendor.equalsIgnoreCase("Acme")) {  // Looney Tunes
            return new AcmeStackAgent();
        } else if (vendor.equalsIgnoreCase("Globex")) { // The Simpsons
            return new GlobexStackAgent();
        }

        throw new SystemException(String.format("**ERROR** Invalid vendor '%s' for creating Stack Agent.", vendor));
    }

}
