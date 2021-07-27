package com.jackguo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jackguo.entil.SysUser;

import java.util.Date;

public class SysUserVo extends SysUser {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
