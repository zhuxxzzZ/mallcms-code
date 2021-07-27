package com.jackguo.Web;


import com.jackguo.Service.BusCustomerService;
import com.jackguo.common.CodeMsg;
import com.jackguo.common.Constant;
import com.jackguo.common.Result;
import com.jackguo.common.validator.ValidatorUtil;
import com.jackguo.entil.ActiveUser;
import com.jackguo.form.CustomerForm;
import com.jackguo.query.BusCustomerQuery;
import com.jackguo.vo.BusCustomerVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*",maxAge = 3600)
@Api(tags = "客户管理数据接口")
@RestController
@RequestMapping("/customer")
public class BusCustomerController {

    @Autowired
    private BusCustomerService busCustomerService;

    @ApiOperation("分页查询数据")
    @RequestMapping("/page.do")
    public Object page(BusCustomerQuery query){
        Result result = busCustomerService.queryPage(query);
        return  result;


    }

    @ApiOperation("客户新增数据")
    @RequestMapping("/add.do")
    public Object add(CustomerForm customerForm){
        ValidatorUtil.validator(customerForm);
        Result result = busCustomerService.add(customerForm);
        return  result;


    }

    @ApiOperation("修改客户数据")
    @RequestMapping("/update.do")
    public Object update(CustomerForm customerForm){
        ValidatorUtil.validator(customerForm);
        Result result = busCustomerService.update(customerForm);
        return  result;


    }

    @ApiOperation("修改客户数据")
    @RequestMapping("/delete.do")
    public Object delete(int id){
        ValidatorUtil.validator(id);
        Result result = busCustomerService.delete(id);
        return  result;


    }

    @RequestMapping("/login.do")
    public Object login(@RequestParam("name") String name, @RequestParam("password") String password){
        Result result = busCustomerService.Login(name,password);
        return  result;


    }


    @ApiOperation("修改密码")
    @RequestMapping("/updatePassword.do")
    public Object updatePassword(int id,String password,String newPassword) {

        String busCustomerVos = busCustomerService.selectById(id);
        if (busCustomerVos != null && busCustomerVos.equals(password) ) {
            busCustomerService.RestPassword(id, newPassword);
            return new Result();
        }
        return new Result(CodeMsg.USER_UPDATE_PASSWORD_ERROR);




    }


}
