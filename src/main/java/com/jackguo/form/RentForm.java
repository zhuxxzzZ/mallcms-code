package com.jackguo.form;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class RentForm {
    /**
     * 车牌号
     */
    private String num;

    /**
     * 车型1轿车2suv3跑车
     */
    private Integer type;

    /**
     * 汽车颜色
     */
    private String color;

    /**
     * 汽车价格
     */
    private Integer price;

    /**
     * 出组价格
     */
    private Integer rentPrice;

    /**
     * 押金
     */
    private Integer deposit;

    /**
     * 姓名
     */

    private  String name;
    /**
     * 身份证信息
     */
    private String idCard;

    /**
     * 出租开始时间
     */
    private String beginTime;
    /**
     * 出租结束时间
     */
    private String endTime;


    private String rentTime;

    /**
     * 汽车图片
     */
    private String img;

    private Integer userId;


}
