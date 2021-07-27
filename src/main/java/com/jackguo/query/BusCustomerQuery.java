package com.jackguo.query;


import com.jackguo.form.CustomerForm;
import io.swagger.annotations.Api;
import lombok.Data;
@Api(tags = "客户查询参数类")
@Data
public class BusCustomerQuery extends Query{

    private String name;
    private String phone;
    private String address;
    private String idCard;
    private String password;


}
