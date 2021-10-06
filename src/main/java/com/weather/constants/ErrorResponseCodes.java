package com.weather.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Suresh Chaudhari
 */
@Getter
@AllArgsConstructor
public enum ErrorResponseCodes {

    BAD_REQUEST_POST(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(),
            ErrorResponseCodes.VALIDATION_ERROR, "Please enter username, content and city."),

    BAD_REQUEST_REPLY_POST(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(),
            ErrorResponseCodes.VALIDATION_ERROR, "Please enter comment and username"),

    UNPROCESSABLE_ENTITY_POST_ID(HttpStatus.UNPROCESSABLE_ENTITY, HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(),
            ErrorResponseCodes.DATABASE_ERROR, "Post is not exist in database."),

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
    public static final String DATABASE_ERROR = "DATABASE";

}
