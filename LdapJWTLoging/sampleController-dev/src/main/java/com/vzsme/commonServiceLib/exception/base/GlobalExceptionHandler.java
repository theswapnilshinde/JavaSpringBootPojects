package com.vzsme.commonServiceLib.exception.base;

import com.vzsme.commonServiceLib.exception.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Adds direct field access so we can have public properties instead of getters and setters
    @InitBinder
    private void activateDirectFieldAccess(DataBinder dataBinder) {
        dataBinder.initDirectFieldAccess();
    }

    @ExceptionHandler(BaseApiException.class)
    public ResponseEntity apiException(BaseApiException ex) {
        HttpStatus status = ex.getHttpStatus();
        ExceptionResponse response = new ExceptionResponse(status, ex);
        return new ResponseEntity<>(response, status);
    }

    // This method is called when the @Valid annotation exception occurs.
    // Typically the @RequestBody has the @Valid annotations AND the body
    // object has annotations like @NotNull etc.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity inputException(MethodArgumentNotValidException ex) {
        //String s = ex.getBindingResult().getFieldErrors().stream().map(f -> f.getDefaultMessage() ).collect(Collectors.joining(", "));
        String m = buildErrorMessage(ex.getBindingResult().getFieldErrors());
        return this.apiException(new InvalidInputException(m));
    }
    // From the given list of validation errors, format a readable message
    // for each field error.
    private String buildErrorMessage(List<FieldError> fieldErrors) {
        // First put the list in order of the field names.
        List<FieldError> sortableList = new ArrayList<FieldError>(fieldErrors);
        Collections.sort(sortableList, (fe1, fe2) -> fe1.getField().compareToIgnoreCase(fe2.getField()));

        //String m = String.format("Validation Errors (%d): ", fieldErrors.size());
        String m = "Validation Errors: ";
        for (int ndx = 0; ndx < sortableList.size(); ndx++) {
            FieldError fieldError = sortableList.get(ndx);
            String fieldErrMsg = this.fieldErrorMessage(fieldError.getCodes(), fieldError.getDefaultMessage());
            m += String.format("(%d) Property '%s' %s, ", ndx+1, fieldError.getField(), fieldErrMsg);
        }
        m = m.substring(0, m.length()-2); // Remove the last ", "
        return m;
    }
    // Override the default interpolator message.
    private String fieldErrorMessage(String[] errorCodes, String defaultMsg) {
        List<String> codes = Arrays.asList(errorCodes);
        if (codes.contains("NotNull") && defaultMsg.equalsIgnoreCase("must not be null") ||
            codes.contains("NotEmpty") && defaultMsg.equalsIgnoreCase("must not be empty")) {
            return "is required";
        }
        return defaultMsg;
    }

    // This method is called when the @Validated annotation exception occurs.
    // Typically the @PathVariable and/or @RequestParam uses annotations like @Min(1), @Max(9), etc.
    // AND the controller class (@RestController) has the @Validated annotation.
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity inputException(ConstraintViolationException ex, HttpServletRequest request) {
        return this.apiException(new InvalidInputException(ex.getMessage()));
    }

    // This method is called when the @Validated annotation exception occurs for type mismatch
    // Typically this is when a api query parameter value does not match the specified type
    // (expecting integer but user provided a string)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity inputException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        return this.apiException(new InvalidInputException(ex.getMessage()));
    }

}
