package com.jackguo.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jackguo.common.CodeMsg;
import com.jackguo.common.Constant;
import com.jackguo.common.Result;
import com.jackguo.entil.ActiveUser;
import com.jackguo.form.SysUserForm;
import com.jackguo.query.SysUserQuery;
import com.jackguo.vo.SysUserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.jackguo.entil.SysUser;
import com.jackguo.Dao.SysUserMapper;
import com.jackguo.Service.SysUserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService{

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public Result queryUser(String username, String password) {
      SysUser sysUser=  sysUserMapper.selectByNameAndPassword(username,password);
      if(sysUser==null){
          return new Result(CodeMsg.USER_PASSWORD_ERROR);
      }
        return new Result(sysUser);
    }


    @Override
    public Result queryPage(SysUserQuery query) {
        Page<SysUserVo> sysUserVoPage= PageHelper.startPage(query.getPage(), query.getLimit());
         sysUserMapper.selectList(query);
        Result result = new Result(sysUserVoPage.toPageInfo());

//       根据参数查询用户
        return result;
    }

    @Override
    public Result add(SysUserForm sysUserForm) {

        SysUserQuery query=new SysUserQuery();
//        校验用户名
        query.setLoginName(sysUserForm.getLoginName());
        System.out.println("-----"+sysUserForm.getLoginName());
        SysUserVo sysUserVo = sysUserMapper.selectUserByNameOrPhoneOrIdCard(query);
        if(sysUserVo !=null){

            return  new Result(CodeMsg.USER_LOGIN_NAME_EXIST_ERROR);
        }
//校验手机号
        query= new SysUserQuery();
        query.setPhone(sysUserForm.getPhone());
         sysUserVo = sysUserMapper.selectUserByNameOrPhoneOrIdCard(query);
        if(sysUserVo !=null){

            return  new Result(CodeMsg.USER_PHONE_EXIST_ERROR);
        }
//        校验身份证
        query= new SysUserQuery();
        query.setPhone(sysUserForm.getIdCard());
        sysUserVo = sysUserMapper.selectUserByNameOrPhoneOrIdCard(query);
        System.out.println(sysUserVo);
        if(sysUserVo !=null){

            return  new Result(CodeMsg.USER_IDCARD_EXIST_ERROR);
        }


        Md5Hash md5Hash=new Md5Hash(Constant.DEFAULT_PASSWORD,Constant.MD5_SALT,2);
        sysUserForm.setLoginPassword(md5Hash.toString());
        sysUserMapper.insert(sysUserForm);


        return new Result();
    }

    @Override
    public Result resetPassword(Integer id) {
        Md5Hash md5Hash=new Md5Hash(Constant.DEFAULT_PASSWORD,Constant.MD5_SALT,2);

        sysUserMapper.updatePassword(id,md5Hash.toString());
        return new Result();
    }

    @Override
    public Result updatePassword(String password, String newPassword) {
        newPassword=new Md5Hash(newPassword,Constant.MD5_SALT,2).toString();
        ActiveUser activeUser =(ActiveUser) SecurityUtils.getSubject().getPrincipal();
        Integer id = activeUser.getSysUser().getId();
        sysUserMapper.updatePasswordByZWY(newPassword,id);

        return new Result();
    }
}
