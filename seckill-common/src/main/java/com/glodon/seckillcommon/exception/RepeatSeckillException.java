package com.glodon.seckillcommon.exception;

/**
 * 描述:
 * 自定义重复秒杀异常，错误代码410
 *
 * @author wangpp-b
 * @create 2019-08-12 13:27
 */
public class RepeatSeckillException extends RuntimeException{
    private String erCode ="402";  //异常对应的返回码
    private String erMessage="禁止重复秒杀";  //异常对应的描述信息

    public RepeatSeckillException(){
        super();
    }
    public RepeatSeckillException(String erMessage) {
        super(erMessage);
        this.erMessage = erMessage;
    }

    public String getErCode() {
        return erCode;
    }

    public void setErCode(String erCode) {
        this.erCode = erCode;
    }

    public String getErMessage() {
        return erMessage;
    }

    public void setErMessage(String erMessage) {
        this.erMessage = erMessage;
    }
}
