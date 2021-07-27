package com.jackguo.common.validator;


//自定义一个数据校验器

//前端储阿生农户数据给后端
//    后端：数据格式校验和数据业务校验
//      数据格式校验：手机号：11位，如果12位数据库会报错sql异常，无法添加到数据库
//      数据业务校验：查询数据表中是否已经存在该手机号
//一般在开发中，会在控制层进行数据的格式校验，在service层进行业务校验
//只有格式校验和数据校验都成功了你才能进行数据的crud操作

import com.jackguo.common.Constant;
import com.jackguo.common.exception.BussiException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidatorUtil {

//    数据校验器对象
    public static final Validator VALIDATOR;

    /*
    * 实例化校验器对象
    *
    * */
    static {
        VALIDATOR=Validation.buildDefaultValidatorFactory().getValidator();

    }
    /*
    * 进行数据校验
    * */
    public static void validator(Object o){
//      进行一个具体的数据校验
        Set<ConstraintViolation<Object>> validateSet = VALIDATOR.validate(o);
//        如果数据校验器校验的结果不为空，说明校验有不通过
        if( validateSet !=null && !validateSet.isEmpty()) {
//            便利校验不通过的信息
             for (ConstraintViolation<Object> objectConstraintViolation : validateSet) {
//              校验不通过的原因
                 String message = objectConstraintViolation.getMessage();
                 Integer code= Constant.PARAM_CHECK_ERROR;
//                 将异常交给自定义异常处理器
                 throw new BussiException(code,message);
             }

    }


    }



}
