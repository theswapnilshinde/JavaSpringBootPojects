package com.vzsme.controllerService;

import org.springframework.web.bind.annotation.ControllerAdvice;
import com.vzsme.commonServiceLib.exception.base.GlobalExceptionHandler;

// This is the application global exception handler.
// Any exception thrown back to the requesting service
// will be translated in this common class to provide
// uniformity for all services.
@ControllerAdvice
public class AppExceptionHandler extends GlobalExceptionHandler {

}
