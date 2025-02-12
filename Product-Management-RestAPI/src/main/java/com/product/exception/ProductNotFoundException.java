package com.product.exception;

public class ProductNotFoundException extends RuntimeException { // Correct: extends RuntimeException directly

    public ProductNotFoundException(String message) {
        super(message);
    }
}