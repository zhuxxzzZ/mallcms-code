package com.jackguo.entil;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusReturn {
    /**
     * 还车记录ID
     */
    private Integer id;

    /**
     * 出租记录ID
     */
    private Integer rentId;

    /**
     * 车牌号
     */
    private String num;

    /**
     * 还车时间
     */
    private String returnTime;

    /**
     * 租金
     */
    private Integer rentPrice;

    /**
     * 赔付金额
     */
    private Integer payMoney;

    /**
     * 问题
     */
    private String problem;

    /**
     * 总金额
     */
    private Integer totalMoney;

    /**
     * 业务员
     */
    private Integer userId;

    /**
     * 创建时间
     */
    private Date createTime;

    private String name;
}