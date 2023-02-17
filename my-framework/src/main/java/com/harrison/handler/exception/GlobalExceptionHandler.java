package com.harrison.handler.exception;

import com.harrison.domain.result.ResponseResult;
import com.harrison.enums.AppHttpCodeEnum;
import com.harrison.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author: Harrison
 * @date: 2023/2/17 14:01
 * @Description: 统一异常处理类
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(SystemException.class)
    public ResponseResult systemException(SystemException e) {
        log.error("出错了！", e);
        return ResponseResult.errorResult(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult exception(Exception e) {
        log.error("Exception出错了！", e);
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }
}
