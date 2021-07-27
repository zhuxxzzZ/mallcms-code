package com.jackguo.config.shiro;

import com.jackguo.Service.SysPermissionService;
import com.jackguo.Service.SysRoleService;
import com.jackguo.Service.SysUserService;
import com.jackguo.common.CodeMsg;
import com.jackguo.common.Result;
import com.jackguo.entil.ActiveUser;
import com.jackguo.entil.SysRole;
import com.jackguo.entil.SysUser;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


//自定义的realm 领域
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysPermissionService sysPermissionService;

//    认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//       获取用户名和密码
        System.out.println("执行了验证方法");
        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        char[] pwd=usernamePasswordToken.getPassword();
        String password=String.valueOf(pwd);
//        根据用户名和密码·查询用户
        //       校验业务是否成功
       Result rs= sysUserService.queryUser(username,password);
        if( !rs.getCode().equals(CodeMsg.SUCCESS.code)){
            return null;
        }

            SysUser sysUser= (SysUser) rs.getData();
            String realname = sysUser.getRealname();
            String img=sysUser.getImg();

//            TODD 未完成用TODD暂时
//            根据用户id查询角色和权限
//        根据用户Id查询用户所有的角色标识
          List<String> roleTags= sysRoleService.queryUserRolesTags(sysUser.getId());
//          根据用户id查询用户所有的权限
            List<String> permissionTags =sysPermissionService.queryUserPermissionTags(sysUser.getId());
              ActiveUser activeUser=new ActiveUser();
              activeUser.setSysUser(sysUser);
              activeUser.setRealname(realname);
              activeUser.setImg(img);
              activeUser.setPermissions(permissionTags);

        AuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(activeUser,password,realname);
        return authenticationInfo;
    }


//    授权
@Override
protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
             ActiveUser activeUser= (ActiveUser) principalCollection.getPrimaryPrincipal();
            List<String> roles = activeUser.getRoles();
            List<String> permissions = activeUser.getPermissions();
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addRoles(roles);
            simpleAuthorizationInfo.addStringPermissions(permissions);


         return simpleAuthorizationInfo;
}
}
