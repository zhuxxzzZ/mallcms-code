package com.jackguo.Web;


import com.jackguo.Service.BusRentService;
import com.jackguo.common.CodeMsg;
import com.jackguo.common.Result;
import com.jackguo.common.validator.ValidatorUtil;
import com.jackguo.entil.BusRent;
import com.jackguo.form.RentForm;
import com.jackguo.query.BusRentQuery;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@Api(tags ="出租数据管理")
@RestController
@RequestMapping("/rent")
public class BusRentController {

    @Autowired
    private BusRentService busRentService;

    @RequestMapping("/page.do")
    public Object page(BusRentQuery query){
        ValidatorUtil.validator(query);
       Result result= busRentService.page(query);
       return result;




    }

    @RequestMapping("/add.do")
    public Object add(RentForm rentForm){

        Result result= busRentService.add(rentForm);

        return result;


    }

    @RequestMapping("/addCus.do")
    public Object addCus(RentForm rentForm){

        if (rentForm.getUserId() ==null){
            return new Result(CodeMsg.LOGIN_NOT_HAVE);
        }
        Result result= busRentService.addCus(rentForm);

        return result;

    }
    @RequestMapping("/selectByName")
    public Object selectByName(BusRentQuery query){

        Result result= busRentService.selectByName(query);
        return result;

    }

}
