package com.jackguo.query;

import io.swagger.annotations.Api;
import lombok.Data;

//专门用来查询SysUser:
@Api(tags = "用户查询参数类")
@Data
public class SysUserQuery extends Query {
//    用户姓名,电话,地址

    private String realname;
    private String phone;
    private String address;
    private String idCard;
    private String loginName;


}
