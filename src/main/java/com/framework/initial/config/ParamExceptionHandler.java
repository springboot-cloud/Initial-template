package com.framework.initial.config;

import com.framework.initial.vo.api.ApiResponse;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author XiongFeiYang
 * @description 请求参数校验
 * @createTime 2019-08-08 09:31
 **/
@RestControllerAdvice
@ResponseBody
public class ParamExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object MethodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
        FieldError fieldError = exception.getBindingResult().getFieldErrors().get(0);
        return ApiResponse.buildCommonErrorResponse(fieldError.getDefaultMessage());
    }
}