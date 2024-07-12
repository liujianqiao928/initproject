package com.ljq.project.dto.response;

import lombok.Data;

import java.io.Serializable;
@Data
public class ResultResponse implements Serializable {
    private static final long serialVersionUID = 86826321122892189L;
    private  int code;
    private String message;
    private  Object data;
    private static final ResultResponse RESULT_RESPONSE = new ResultResponse();
    private ResultResponse(){

    }
    public static ResultResponse ok(int code,String message,Object data){
        RESULT_RESPONSE.code=code;
        RESULT_RESPONSE.message=message;
        RESULT_RESPONSE.data=data;
        return RESULT_RESPONSE;
    }
    public static ResultResponse fail(int code,String message){
        RESULT_RESPONSE.code=code;
        RESULT_RESPONSE.message=message;
        return RESULT_RESPONSE;
    }

}
