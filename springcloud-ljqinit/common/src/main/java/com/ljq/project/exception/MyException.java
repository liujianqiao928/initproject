package com.ljq.project.exception;

import java.io.Serializable;

public class MyException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;


    public MyException(String message) {
        super(message);
    }



}
