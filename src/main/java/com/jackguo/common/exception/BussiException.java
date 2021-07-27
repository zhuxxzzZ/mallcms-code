package com.jackguo.common.exception;


//自定义业务异常类
//在很多业务场景中会涉及到多张表的更新操作
//如果发生a表更新成功，b表更新失败，需要实现事物的回滚
//而事务的回滚依赖异常，只有发生了异常才会回滚，此时需要程序自定义一个异常
//因为程序抛出异常无法控制，任何类型的异常都有所以自定义异常，开发者在全局
//异常处理器中进行捕获异常，既可以回滚事务，也可以提高程序与用户的交互体验

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BussiException extends RuntimeException {

    private  Integer code;
    private  String msg;



}
