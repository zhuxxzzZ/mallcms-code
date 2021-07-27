package com.jackguo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jackguo.entil.BusRent;
import lombok.Data;

import java.util.Date;

@Data
public class BusRentVo extends BusRent {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;




}
