package me.walker.common.exception;

/**
 * 公共异常基类
 */
public abstract class BaseBizException extends RuntimeException{

    private Integer errorCode;

    private String errorMsg;

    private Object[] params;

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public BaseBizException(ErrorBaseEnum baseEnum){
        super("[" + baseEnum.getErrCode() + "]" + baseEnum.getErrMsg());
        this.errorCode = baseEnum.getErrCode();
        this.errorMsg = baseEnum.getErrMsg();
    }

    public BaseBizException(ErrorBaseEnum baseEnum, Object[] params){
        super("[" + baseEnum.getErrCode() + "]" + baseEnum.getErrMsg());
        this.errorCode = baseEnum.getErrCode();
        this.errorMsg = baseEnum.getErrMsg();
        this.params = params;
    }

    public BaseBizException(ErrorBaseEnum baseEnum, Throwable throwable){
        super("[" + baseEnum.getErrCode() + "]" + baseEnum.getErrMsg(), throwable);
        this.errorCode = baseEnum.getErrCode();
        this.errorMsg = baseEnum.getErrMsg();
    }

    public BaseBizException(ErrorBaseEnum baseEnum, Object[] params, Throwable throwable){
        super("[" + baseEnum.getErrCode() + "]" + baseEnum.getErrMsg(), throwable);
        this.errorCode = baseEnum.getErrCode();
        this.errorMsg = baseEnum.getErrMsg();
        this.params = params;
    }

    public BaseBizException(ErrorBaseEnum baseEnum, String errorMsg){
        super("[" + baseEnum.getErrCode() + "]" + errorMsg);
        this.errorCode = baseEnum.getErrCode();
        this.errorMsg = errorMsg;
    }

    public BaseBizException(ErrorBaseEnum baseEnum, String errorMsg, Object[] params){
        super("[" + baseEnum.getErrCode() + "]" + errorMsg);
        this.errorCode = baseEnum.getErrCode();
        this.errorMsg = errorMsg;
        this.params = params;
    }

    public BaseBizException(ErrorBaseEnum baseEnum, String errorMsg, Throwable throwable){
        super("[" + baseEnum.getErrCode() + "]" + errorMsg, throwable);
        this.errorCode = baseEnum.getErrCode();
        this.errorMsg = errorMsg;
    }

    public BaseBizException(ErrorBaseEnum baseEnum, String errorMsg, Object[] params, Throwable throwable){
        super("[" + baseEnum.getErrCode() + "]" + errorMsg, throwable);
        this.errorCode = baseEnum.getErrCode();
        this.errorMsg = errorMsg;
        this.params = params;
    }

    public String getMessage(){
        if (params == null || params.length == 0) {
            return "[" + errorCode + "]" + errorMsg;
        } else {
            StringBuilder msg = new StringBuilder("[");
            msg.append(errorCode);
            msg.append("][");
            msg.append(errorMsg);
            msg.append("]");
            for (Object param : params) {
                msg.append(param).append(",");
            }
            msg.deleteCharAt(msg.lastIndexOf(","));
            return msg.toString();
        }
    }
}
