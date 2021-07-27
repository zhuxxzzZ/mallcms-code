package com.jackguo.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class SysUserForm {

    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 登录名
     */
    @NotEmpty(message = "登录名不能为空")
    @Length(min = 6,max = 15,message = "登录名是6--15位字符")
    private String loginName;

    /**
     * 登录密码
     */
    private String loginPassword;

    /**
     * 电话号码
     */
    @NotEmpty(message = "手机号不能为空")
    @Length(min = 11,max = 11,message = "手机号只能11位字符")
    private String phone;

    /**
     * 真实姓名
     */
    @NotEmpty(message = "真实姓名不能为空")
    @Length(max = 15,message = "真实姓名最多20位字符")
    private String realname;

    /**
     * 身份证号
     */
    @NotEmpty(message = "身份证号不能为空")
    @Length(min = 18,max = 18,message = "身份证号只能18位字符")
    private String idCard;

    /**
     * 性别1男 2女
     */
    @NotNull(message = "性别不能为空")
    @Range(min=1,max = 2,message = "性别只能是男（1）和女（2）")
    private Integer sex;

    /**
     * 地址
     */
    @NotNull(message = "地址不能为空")
    @Length(max = 100,message = "地址最多100位字符")
    private String address;

    /**
     * 头像
     */
    private String img;
}
