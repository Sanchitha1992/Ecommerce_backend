package com.learnjava.api.productservices.exception;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
