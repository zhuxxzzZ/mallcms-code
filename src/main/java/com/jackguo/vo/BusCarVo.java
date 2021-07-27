package com.jackguo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jackguo.entil.BusCar;

import java.util.Date;

public class BusCarVo extends BusCar {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
