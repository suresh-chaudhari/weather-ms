package com.loyalty.one.exceptions;

import com.loyalty.one.constants.ErrorResponseCodes;
import com.loyalty.one.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Suresh Chaudhari
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionManager {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> handleServiceException(ServiceException ex){
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getErrorResponse());
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ErrorResponse> handleSystemException(SystemException ex){
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getErrorResponse());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex){
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(ErrorResponseCodes.INTERNAL_SERVER_ERROR));
    }

}
