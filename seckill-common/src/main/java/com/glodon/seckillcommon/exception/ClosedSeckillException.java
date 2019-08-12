package com.glodon.seckillcommon.exception;

/**
 * 描述:
 * 自定义订单关闭异常，错误代码410
 *
 * @author wangpp-b
 * @create 2019-08-12 13:27
 */

public class ClosedSeckillException extends RuntimeException {
    private String erCode ="500";  //异常对应的返回码
    private String erMessage="秒杀已经关闭";  //异常对应的描述信息

    public ClosedSeckillException() {
        super();
    }

    public ClosedSeckillException(String erMessage) {
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
