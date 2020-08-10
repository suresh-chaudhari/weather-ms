package com.loyalty.one.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Suresh Chaudhari
 */
@Getter
@AllArgsConstructor
public enum ErrorResponseCodes {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(),
            ErrorResponseCodes.VALIDATION_ERROR, "Please Enter Product and Quantity"),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            ErrorResponseCodes.SYSTEM_ERROR, "Unexpected Error");

    private final HttpStatus httpStatus;
    private final String message;
    private final String cause;
    private final String description;

    /**
     * Error constant
     */
    public static final String SYSTEM_ERROR = "SYSTEM";
    public static final String VALIDATION_ERROR = "VALIDATION";

}
