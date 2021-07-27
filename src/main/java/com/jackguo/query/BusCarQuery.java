package com.jackguo.query;


import io.swagger.annotations.Api;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Api(tags = "车辆查询参数类")
@Data
public class BusCarQuery  extends  Query {

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
    private Integer minPrice;
    private Integer maxPrice;

    /**
     * 出组价格
     */
    private Integer minRentPrice;
    private Integer maxRentPrice;

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


}
