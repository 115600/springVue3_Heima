package org.winkey.exception;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.winkey.pojo.Result;

@RestControllerAdvice

public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result ExceptionHandler(Exception e){
        return Result.error(StringUtils.hasLength(e.getMessage())?e.getMessage():"操作失败");
    }
}
