package com.jackguo.Web;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Api(tags = "页面接口")
@RequestMapping("/page")
@Controller
public class PageController {


    @ApiOperation("登录页")
    @RequestMapping("/login")
    public String tologin() {
        return "page/login/login";
    }

    @ApiOperation("登录主页")
    @RequestMapping("/main.do")
    public String main(){
        return "index";
    }

    @ApiOperation("加载首页")
    @RequestMapping("/welcome")
    public String menu(){
        return "page/welcome";
    }

    @ApiOperation("用户管理表")
    @RequestMapping("/userList")
    public String userList(){
        return "page/user/userList";
    }

    @ApiOperation("客户管理表")
    @RequestMapping("/customerList")
    public String customerList(){
        return "page/customer/customerList";
    }

    @ApiOperation("车辆列表")
    @RequestMapping("/carList")
    public String carList(){
        return "page/car/carList";
    }


    @ApiOperation("出租列表")
    @RequestMapping("/rentList")
    public String rentList(){
        return "page/rent/rentList";
    }

    @ApiOperation("还车列表")
    @RequestMapping("/returnList")
    public String returnList(){
        return "page/return/returnList";
    }


    @ApiOperation("角色列表")
    @RequestMapping("/roleList")
    public String roleList(){
        return "page/role/roleList";
    }


    @ApiOperation("permission权限管理")
    @RequestMapping("/permList")
    public String dtreeList(){
        return "page/perm/permList";
    }



}
