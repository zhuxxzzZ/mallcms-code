package com.jackguo.Service;

import com.jackguo.common.Result;
import com.jackguo.form.SysUserForm;
import com.jackguo.query.SysUserQuery;

public interface SysUserService{


//    根据用户名和密码查询用户
    Result queryUser(String username, String password);
//      分页查询用户数据
    Result queryPage(SysUserQuery query);
//      用户数据新增
    Result add(SysUserForm sysUserForm);
//重置密码
    Result resetPassword(Integer id);

    Result updatePassword(String password, String newPassword);
}
