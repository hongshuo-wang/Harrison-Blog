package com.harrison.exception;

import com.harrison.enums.AppHttpCodeEnum;

/**
 * @author: Harrison
 * @date: 2023/2/17 13:52
 * @Description: TODO
 */
public class SystemException extends RuntimeException{

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }

}
