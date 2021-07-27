package com.jackguo.entil;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser {
    /**
    * 用户ID
    */
    private Integer id;

    /**
    * 登录名
    */
    private String loginName;

    /**
    * 登录密码
    */
    private String loginPassword;

    /**
    * 电话号码
    */
    private String phone;

    /**
    * 真实姓名
    */
    private String realname;

    /**
    * 身份证号
    */
    private String idCard;

    /**
    * 性别1男 2女
    */
    private Integer sex;

    /**
    * 地址
    */
    private String address;

    /**
    * 头像
    */
    private String img;

    /**
    * 创建时间
    */
    private Date createTime;
}