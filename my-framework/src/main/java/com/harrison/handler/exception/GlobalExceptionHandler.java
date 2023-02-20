package com.harrison.handler.exception;

import com.harrison.domain.result.ResponseResult;
import com.harrison.enums.AppHttpCodeEnum;
import com.harrison.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.ValidationException;
import java.util.function.Function;
import java.util.stream.Collectors;


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
        log.error("自定义异常！ex={}", e.getMsg(), e);
        return ResponseResult.errorResult(e.getCode(), e.getMsg());
    }
    @ExceptionHandler(BindException.class)
    public ResponseResult validationException(BindException e) {
        log.error("校验异常！ex={}", e.getMessage(), e);
        return ResponseResult.errorResult(AppHttpCodeEnum.PARAMETER_NOT_NULL.getCode(),
                e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult exception(Exception e) {
        log.error("全局异常！ex={}！", e.getMessage(), e);
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }
}
