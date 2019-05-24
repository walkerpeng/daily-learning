package me.walker.common.exception;

public enum ErrorEnum implements ErrorBaseEnum{
    SUCCESS(0,"请求成功"),
    UNKNOWN_EXCEPTION(-1, "未知异常"),
    SYSTEM_ERROR(-2, "系统错误"),

    PARAMETER_ERROR(2, "请求参数错误"),

    //用户相关
    USER_OR_PASSWORD_ERROR(1001, "用户名或密码错误")
    ;

    private Integer errCode;
    private String errMsg;

    ErrorEnum(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public Integer getErrCode() {
        return null;
    }

    @Override
    public String getErrMsg() {
        return null;
    }
}
