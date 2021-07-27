package com.jackguo.Dao;

import com.jackguo.entil.SysUser;
import com.jackguo.form.SysUserForm;
import com.jackguo.query.SysUserQuery;
import com.jackguo.vo.SysUserVo;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMapper {

    @ApiOperation("根据用户名密码查询用户")
    SysUser selectByNameAndPassword(@Param("username") String username, @Param("password") String password);


   @ApiOperation("根据参数查询用户数据列表")
    List<SysUserVo>  selectList(SysUserQuery query);

   SysUserVo selectUserByNameOrPhoneOrIdCard(SysUserQuery query);

    void insert(SysUserForm sysUserForm);

    void updatePassword(@Param("id") Integer id,@Param("password") String password);

    void updatePasswordByZWY(@Param("newPassword")String newPassword,@Param("id") Integer id);
}