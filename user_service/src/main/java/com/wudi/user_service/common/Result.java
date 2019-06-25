package com.wudi.user_service.common;

public class Result {
    /**
     * 错误码
     */
    private Integer errorcode;
    /**
     * 错误信息
     */
    private String  errorMessage;
    /**
     * 数据字符串
     */
    private Object data;
    /**
     * 是否成功
     */
    private  boolean success;

    public Result(Integer errorcode, Object data, boolean success) {
        this.errorcode = errorcode;
        this.data = data;
        this.success = success;
    }

    public Result(Integer errorcode, String errorMessage, Object data, boolean success) {
        this.errorcode = errorcode;
        this.errorMessage = errorMessage;
        this.data = data;
        this.success = success;
    }

    public Integer getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(Integer errorcode) {
        this.errorcode = errorcode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static Result buildSuccess(Object data){
        return new Result(null,data,true);
    }

    public static Result buildSuccess(Integer errorcode, Object data){
        return new Result(errorcode,data,true);
    }

    public static Result buildFailed(ResultCode codeEnum){
        return new Result(codeEnum.getCode(),codeEnum.getDesign(),null,false);
    }

    public static Result buildFailed(ResultCode codeEnum, Object data){
        return new Result(codeEnum.getCode(),codeEnum.getDesign(),data,false);
    }

    public static Result buildFailed(Integer errorcode,String message, Object data){
        return new Result(errorcode,message,data,false);
    }

}
