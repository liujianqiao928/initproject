package com.ljq.project.dto.response;

import java.io.Serializable;

public class ResultResponse<T> implements Serializable {
    private static final long serialVersionUID = 86826321122892189L;
    private  int code;
    private String message;
    private  T data;

    public  ResultResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;

    }

}
