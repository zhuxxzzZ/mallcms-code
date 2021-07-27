package com.jackguo.Service;

import com.jackguo.common.Result;
import com.jackguo.entil.BusCustomer;
import com.jackguo.form.CustomerForm;
import com.jackguo.query.BusCustomerQuery;
import com.jackguo.vo.BusCustomerVo;

import java.util.List;

public interface BusCustomerService{


    Result queryPage(BusCustomerQuery query);

    Result add(CustomerForm customerForm);

    Result update(CustomerForm customerForm);

    Result delete(int id);

    Result Login(String name,String password);

    String selectById(int id);

    Result RestPassword(int id,String newPassword);
}
