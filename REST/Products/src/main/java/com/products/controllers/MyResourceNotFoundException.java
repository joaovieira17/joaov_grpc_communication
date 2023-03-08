package com.products.controllers;

import java.net.MalformedURLException;

public class MyResourceNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MyResourceNotFoundException(final String string) {
        super(string);
    }

    public MyResourceNotFoundException(final String string, final MalformedURLException ex) {
        super(string, ex);
    }
}
