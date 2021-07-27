package com.jackguo.Web;


import com.jackguo.Service.BusReturnService;
import com.jackguo.common.Result;
import com.jackguo.form.ReturnForm;
import com.jackguo.query.BusReturnQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/return")
public class BusReturnController {

    @Autowired
    private BusReturnService busReturnService;

    @RequestMapping("/add.do")
    public Object add(ReturnForm returnForm){
       Result result= busReturnService.add(returnForm);

        return result;


    }
    @RequestMapping("/addCus.do")
    public Object addCus(ReturnForm returnForm){
       Result result= busReturnService.addCus(returnForm);

        return result;


    }


    @RequestMapping("/page.do")
    public Object page(BusReturnQuery query){
        Result result= busReturnService.queryPage(query);

        return result;


    }


    @RequestMapping("/pageCus.do")
    public Object pageCus(BusReturnQuery query){
        Result result= busReturnService.queryPageCus(query);

        return result;


    }

}
