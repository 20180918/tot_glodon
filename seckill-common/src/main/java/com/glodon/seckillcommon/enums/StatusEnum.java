package com.glodon.seckillcommon.enums;

/**
 * 描述:
 * 状态枚举
 *
 * @author wangpp-b
 * @create 2019-08-14 16:43
 */
public enum StatusEnum {
    SUCCESS(0, "成功"),
    REDO(1, "操作重复"),
    WAIT(2, "等待"),
    FAIL(-1, "失败"),
    UNLOAD(205, "未注册"),
    UNSAFE(206, "密钥验证不通过"),
    WRONG(400, "系统异常");
    private Integer code;
    private String message;

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
