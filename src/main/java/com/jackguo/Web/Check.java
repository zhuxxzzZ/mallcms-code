package com.jackguo.Web;


import com.jackguo.Service.SysUserService;
import com.jackguo.common.CodeMsg;
import com.jackguo.common.Constant;
import com.jackguo.common.Result;
import com.jackguo.common.exception.BussiException;
import com.jackguo.common.validator.ValidatorUtil;
import com.jackguo.entil.ActiveUser;
import com.jackguo.form.SysUserForm;
import com.jackguo.query.SysUserQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.validation.Validator;
import java.net.SocketTimeoutException;
import java.util.zip.Adler32;

@Api(tags = "数据模块")
@RestController
@CrossOrigin
@RequestMapping("/sysuser")
public class Check {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("登录管理")
    @RequestMapping("/login.do")
    public Object login( String loginName,String loginPassword) {

//        对密码进行加密
        Md5Hash md5Hash=new Md5Hash(loginPassword, Constant.MD5_SALT,2);
        System.out.println(md5Hash.toString());
//          使用登录名和密码huanqutoken
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(loginName,md5Hash.toString());
//      获取认证主体
        Subject subject = SecurityUtils.getSubject();
//        进行认证
        subject.login(usernamePasswordToken);
//        表示已经认证   if (subject.isAuthenticated()){
//
//        }
        Result result =new Result();
        return result;
    }

    @ApiOperation("退出管理")
    @RequestMapping("/logout")
    public Object logout(){
        Subject subject=SecurityUtils.getSubject();
         if (subject.isAuthenticated()) {
             subject.logout();

        }

         return new Result();


    }

   @ApiOperation("分页查询数据")
    @RequestMapping("/page.do")
    public Object page(SysUserQuery query){
       Result result = sysUserService.queryPage(query);
       return  result;


    }
    @ApiOperation("用户新增数据")
    @RequestMapping("/add.do")
    public Object add(SysUserForm sysUserForm){
        ValidatorUtil.validator(sysUserForm);
       Result result = sysUserService.add(sysUserForm);
       return  result;


    }

    @ApiOperation("重置密码")
    @RequestMapping("/reset.do")
    public Object reset(Integer id){
        if (id ==null){
            throw new BussiException(Constant.PARAM_CHECK_ERROR,"id不能为空");
        }
        Result result = sysUserService.resetPassword(id);
        return  result;


    }

    @ApiOperation("修改密码")
    @RequestMapping("/updatePassword.do")
    public Object updatePassword(String password,String newPassword){
//        校验原密码是否正确
        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser=(ActiveUser)subject.getPrincipal();
        String loginPassword = activeUser.getSysUser().getLoginPassword();
        password=new Md5Hash(password, Constant.MD5_SALT,2).toString();
        System.out.println(password);
        if(loginPassword.equals(password)){
              Result result=  sysUserService.updatePassword(password,newPassword);
            return  result;

        }

        return new Result(CodeMsg.USER_UPDATE_PASSWORD_ERROR);


    }

}
