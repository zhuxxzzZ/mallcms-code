package com.jackguo.common;

//业务结果包装类
//因为layui前端需要的json格式为code,msg,data这样的格式
//所以后端对数据进行再一次封装

import com.jackguo.common.exception.BussiException;
import lombok.Data;

@Data
public class Result {
//    业务码
    private Integer code;
//    业务消息
    private String msg;
//    业务数据
    private Object data;

//    业务就分为两种情况
//    成功:
//            1:成功了没有数据  2.成功了有返回值
//    失败:
//          失败没有数据,失败原因
//
//         成功没有数据
    public Result(){
        this.code=CodeMsg.SUCCESS.code;
        this.msg=CodeMsg.SUCCESS.msg;


    }


//    成功有数据,有参默认调用无参构造方法
    public Result(Object data){
        this.code=CodeMsg.SUCCESS.code;
        this.msg=CodeMsg.SUCCESS.msg;
        this.data=data;

    }
//    失败但是有错误码和错误消息
    public Result(CodeMsg codeMsg){

        this.code=codeMsg.code;
        this.msg=codeMsg.msg;

    }


//   兼容异常信息

//    因为我们定义的异常是code和msg
    public  Result (BussiException bussiException){

        this.code=bussiException.getCode();
        this.msg=bussiException.getMsg();


    }

}
