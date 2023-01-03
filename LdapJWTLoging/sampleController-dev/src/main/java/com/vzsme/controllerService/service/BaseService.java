package com.vzsme.controllerService.service;

import com.vzsme.controllerService.AppConfigProperties;
import com.vzsme.commonServiceLib.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;

@Service
public class BaseService {

    protected boolean authenticate = true; // NOTE: FOR TESTING ONLY!

    @Autowired
    protected AppConfigProperties appConfig;

    @PostConstruct
    public void init() {
    }


    protected void validateUser(Cookie[] cks) throws UnauthorizedException {
        if (authenticate) {
            if (cks == null) {
                throw new UnauthorizedException("Unauthorized user.");
            }
        }
    }



}
