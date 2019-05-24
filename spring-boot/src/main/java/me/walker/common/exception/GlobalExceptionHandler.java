package me.walker.common.exception;

import me.walker.common.utils.ExceptionUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    public Object customHandler(BizException e){
        return ExceptionUtil.fillException(e);
    }
}
