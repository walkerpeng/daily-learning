package me.walker.common;

import lombok.Getter;
import lombok.Setter;
import me.walker.common.exception.ErrorEnum;

@Setter
@Getter
public class Result<T> {
    private int code;

    private String Message;

    private T data;

    public Result() {
    }

    public Result(int code, String message) {
        this.code = code;
        Message = message;
    }

    public Result(int code, String message, T data) {
        this.code = code;
        Message = message;
        this.data = data;
    }

    public static <T> Result<T> OK(){
        Result<T> result = new Result<T>();
        result.setCode(ErrorEnum.SUCCESS.getErrCode());
        result.setMessage(ErrorEnum.SUCCESS.getErrMsg());
        return result;
    }

    public static <T> Result<T> FAILURE(){
        Result<T> result = new Result<T>();
        result.setCode(ErrorEnum.UNKNOWN_EXCEPTION.getErrCode());
        result.setMessage(ErrorEnum.UNKNOWN_EXCEPTION.getErrMsg());
        return result;
    }
}
