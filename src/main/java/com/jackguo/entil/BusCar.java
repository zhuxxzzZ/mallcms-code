package com.jackguo.entil;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusCar {
    /**
    * 车辆ID
    */
    private Integer id;

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
    * 出租状态1未出租2已出租
    */
    private Integer isRent;

    /**
    * 车辆描述
    */
    private String descp;

    /**
    * 汽车图片
    */
    private String img;

    /**
    * 版本号标识
    */
    private Integer version;

    /**
    * 创建时间
    */
    private Date createTime;
}