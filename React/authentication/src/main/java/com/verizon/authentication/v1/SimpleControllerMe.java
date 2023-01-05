package com.verizon.authentication.v1;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
//@RequestMapping(value = "/simple")
public class SimpleControllerMe {

    @GetMapping({"/simple1"})
    @PreAuthorize("hasRoll('otherpeople')")
    public String firstPage() {
        return "Hello Simple World";
    }

    
    @GetMapping({"/simple2"})
    @PreAuthorize("hasRoll('people')")
    public String SecondPage() {
        return "Hello Simple World";
    }
}
