package com.ljq.project.enums;

public enum TipEnum {

    SUCCESS(200, "成功"),
    FAIL(400, "失败"),
    UNAUTHORIZED(401, "未认证"),
    FORBIDDEN(403, "未授权"),
    NOT_FOUND(404, "未找到"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    BAD_REQUEST(400, "请求错误"),
    NOT_SUPPORTED(406, "不支持的请求"),
    UNKNOWN_ERROR(500, "未知错误"),
    PARAM_ERROR(400, "参数错误"),
    PARAM_MISSING(400, "缺少参数"),
    PARAM_TYPE_ERROR(400, "参数类型错误"),
    PARAM_LENGTH_ERROR(400, "参数长度错误"),
    PARAM_RANGE_ERROR(400, "参数范围错误"),
    PARAM_FORMAT_ERROR(400, "参数格式错误"),
    PARAM_NOT_NULL_ERROR(400, "参数不能为空"),
    PARAM_NOT_EMPTY_ERROR(400, "参数不能为空"),
    PARAM_NOT_EXIST_ERROR(400, "参数不存在"),
    PARAM_EXIST_ERROR(400, "参数已存在"),
    PARAM_NOT_UNIQUE_ERROR(400, "参数不唯一"),
    PARAM_NOT_IN_RANGE_ERROR(400, "参数不在范围内"),
    PARAM_NOT_IN_LIST_ERROR(400, "参数不在列表内"),
    PARAM_NOT_IN_SET_ERROR(400, "参数不在集合内"),
    PARAM_NOT_IN_MAP_ERROR(400, "参数不在Map内"),
    PARAM_NOT_IN_ENUM_ERROR(400, "参数不在枚举内"),
    PARAM_NOT_IN_ARRAY_ERROR(400, "参数不在数组内")
    //登录失败
    ,LOGIN_FAIL(401, "登录失败")
    //登录成功
    ,LOGIN_SUCCESS(200, "登录成功")
    //注册失败
    ,REGISTER_FAIL(401, "注册失败")
    //注册成功
    ,REGISTER_SUCCESS(200, "注册成功")
    //注销成功
    ,LOGOUT_SUCCESS(200, "注销成功")
    //注销失败
    ,LOGOUT_FAIL(401, "注销失败")
    //更新失败
    ,UPDATE_FAIL(401, "更新失败")
    //更新成功
    ,UPDATE_SUCCESS(200, "更新成功")
    //删除失败
    ,DELETE_FAIL(401, "删除失败")
    //删除成功
    ,DELETE_SUCCESS(200, "删除成功")
    //添加失败
    ,ADD_FAIL(401, "添加失败")
    //添加成功
    ,ADD_SUCCESS(200, "添加成功")
    //查询失败
    ,QUERY_FAIL(401, "查询失败")
    //查询成功
    ,QUERY_SUCCESS(200, "查询成功")
    //查询失败
    ,QUERY_FAIL_NO_DATA(401, "查询失败，没有数据")
    //查询成功
    ,QUERY_SUCCESS_NO_DATA(200, "查询成功，没有数据")
    ;

    private int code;
    private String message;
    TipEnum(int code, String msg) {
        this.code = code;
        this.message = msg;

    }

    public int getCode() {
        return code;
    }



    public String getMessage() {
        return message;
    }




}
