package me.walker.common.utils;

import me.walker.common.Result;
import me.walker.common.exception.BaseBizException;
import me.walker.common.exception.ErrorEnum;

public class ExceptionUtil {
    public static <T> Result<T> fillException(Exception e){
        Result<T> result = Result.FAILURE();
        if (e instanceof BaseBizException) {
            result.setCode(((BaseBizException)e).getErrorCode());
            result.setMessage(e.getMessage());
        }else {
            result.setCode(ErrorEnum.SYSTEM_ERROR.getErrCode());
            result.setMessage(ErrorEnum.SYSTEM_ERROR.getErrMsg());
        }
        return result;
    }
}
