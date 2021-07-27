package com.jackguo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jackguo.entil.BusCustomer;

import java.util.Date;

public class BusCustomerVo extends BusCustomer {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
