package com.jackguo.Web;


import com.jackguo.Service.BusCarService;
import com.jackguo.Service.BusCustomerService;
import com.jackguo.common.Result;
import com.jackguo.common.validator.ValidatorUtil;
import com.jackguo.form.CarForm;
import com.jackguo.form.CustomerForm;
import com.jackguo.query.BusCarQuery;
import com.jackguo.query.BusCustomerQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@Api(tags = "车辆管理数据接口")
@RestController
@RequestMapping("/car")
public class BusCarController {

    @Autowired
    private BusCarService busCarService;

    @ApiOperation("分页查询数据")
    @RequestMapping("/page.do")
    public Object page(BusCarQuery query){
        Result result = busCarService.queryPage(query);
        return  result;


    }

    @ApiOperation("查询所有数据")
    @RequestMapping("/pageAll.do")
    public Object pageAll(){
        Result result = busCarService.queryAll();
        return  result;


    }



    @ApiOperation("车辆新增数据")
    @RequestMapping("/add.do")
    public Object add(CarForm carForm){
        ValidatorUtil.validator(carForm);
        Result result = busCarService.add(carForm);
        return  result;
    }


    @ApiOperation("车辆查询数据")
    @RequestMapping("/selectById.do")
    public Object selectById(int id){
        ValidatorUtil.validator(id);
        Result result = busCarService.selectById(id);
        return  result;
    }

}
