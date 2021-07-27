package com.jackguo.entil;

import lombok.Data;

import java.util.List;

@Data
public class ActiveUser {
    private SysUser sysUser;
    //    用户真实姓名
    private String realname;
    //    用户的所有角色
    private List<String> roles;

    //    用户的所有权限
    private List<String> permissions;

    private  String img;
}
