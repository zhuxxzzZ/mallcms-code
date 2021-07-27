package com.jackguo.entil;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusCustomer {
    /**
    * 客户ID
    */
    private Integer id;

    /**
    * 客户名称
    */
    private String name;

    /**
    * 客户电话
    */
    private String phone;

    /**
    * 客户地址
    */
    private String address;

    /**
    * 客户身份证
    */
    private String idCard;

    /**
    * 客户性别1男2女
    */
    private Integer sex;

    /**
    * 创建时间
    */
    private Date createTime;

    private Date updateTime;

    private String password;
}