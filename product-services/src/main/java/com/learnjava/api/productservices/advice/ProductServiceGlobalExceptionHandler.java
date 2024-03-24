package com.learnjava.api.productservices.advice;

import com.learnjava.api.productservices.dto.CustomErrorResponse;
import com.learnjava.api.productservices.dto.GlobalErrorCode;
import com.learnjava.api.productservices.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductServiceGlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException e) {
        CustomErrorResponse customErrorResponse = CustomErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .errorCode(GlobalErrorCode.ERROR_PRODUCT_NOT_FOUND)
                .errorMessage(e.getMessage())
                .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customErrorResponse);
    }
}
