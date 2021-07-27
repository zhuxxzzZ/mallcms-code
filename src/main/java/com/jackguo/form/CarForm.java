package com.jackguo.form;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class CarForm {


    /**
     * 车牌号
     */
    @NotEmpty(message = "车牌号不能为空")
    @Length(min = 7,max = 8,message = "车牌号7~8位")
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
     * 车辆描述
     */
    private String descp;

    /**
     * 汽车图片
     */
    private String img;



}
