package com.workflow.pro.modules.qywx.entity;

import java.io.Serializable;

public class JsonData implements Serializable {

    private Integer code;

    private Object data;

    private String message;

    public JsonData() {}

    public JsonData(Integer code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static JsonData buildSuccess(){
        return new JsonData(200,null,"操作成功");
    }

    public static JsonData buildSuccess(Object data){
        return new JsonData(200, data ,"操作成功");
    }

    public static JsonData buildError(){
        return new JsonData(-1,null,"系统异常，请稍后再试");
    }

    public static JsonData buildError(Integer code, String message){
        return new JsonData(code,null,message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
