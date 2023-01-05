package com.verizon.authentication.v1;

import com.verizon.authentication.v1.Request.UserRequest;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/// concrete page
@RestController
public class HomeController {

    @GetMapping({"/hello"})
    public String firstPage() {
        return "Hello World";
    }

}