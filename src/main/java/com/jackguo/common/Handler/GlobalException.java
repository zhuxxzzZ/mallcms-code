package com.jackguo.common.Handler;

import com.jackguo.common.CodeMsg;
import com.jackguo.common.Result;
import com.jackguo.common.exception.BussiException;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
//全局异常处理器

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler
    @ResponseBody
    public Object handlerException(Exception exception){
        exception.printStackTrace();
//        判断是否是我们自己定义的异常信息
        if (exception instanceof BussiException){
            BussiException bussiException= (BussiException) exception;
            return new Result(bussiException);


        }
//        也有可能是其他异常: shiro
        if(exception instanceof ShiroException){
            if(exception instanceof UnknownAccountException){

                return new Result(CodeMsg.USER_PASSWORD_ERROR);
            }
            if (exception instanceof AuthenticationException){

                return new Result(CodeMsg.USER_NOT_HAVE_SOME_PERMISSION);
            }
            if(exception instanceof AuthenticationToken){
                return  new Result(CodeMsg.USER_PASSWORD_ERROR);

            }
        }
        return new Result(CodeMsg.ERROR);


    }
}
