package me.walker.common.exception;

/**
 * 业务异常类
 */
public class BizException extends BaseBizException {

    public BizException(ErrorBaseEnum baseEnum) {
        super(baseEnum);
    }

    public BizException(ErrorBaseEnum baseEnum, Object[] params) {
        super(baseEnum, params);
    }

    public BizException(ErrorBaseEnum baseEnum, Throwable throwable) {
        super(baseEnum, throwable);
    }

    public BizException(ErrorBaseEnum baseEnum, Object[] params, Throwable throwable) {
        super(baseEnum, params, throwable);
    }

    public BizException(ErrorBaseEnum baseEnum, String errMsg) {
        super(baseEnum, errMsg);
    }

    public BizException(ErrorBaseEnum baseEnum, String errMsg, Object[] params) {
        super(baseEnum, errMsg, params);
    }

    public BizException(ErrorBaseEnum baseEnum, String errMsg, Throwable throwable) {
        super(baseEnum, errMsg, throwable);
    }

    public BizException(ErrorBaseEnum baseEnum, String errMsg, Object[] params, Throwable throwable){
        super(baseEnum, errMsg, params, throwable);
    }
}
