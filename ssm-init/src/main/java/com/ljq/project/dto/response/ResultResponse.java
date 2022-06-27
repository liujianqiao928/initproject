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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
