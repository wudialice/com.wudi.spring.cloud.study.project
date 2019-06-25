package com.wudi.user_service.common;


public enum ResultCode implements ResultInterface {
    VALIDATE_CODE_ERROR(100, "验证码错误"),
    UN_KOWN_EXCEPTION(0000, "内部未知错误"),

    ;


    ResultCode(int code, String design) {
        this.code = code;
        this.design = design;
    }

    private int code;
    private String design;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDesign() {
        return design;
    }
}
