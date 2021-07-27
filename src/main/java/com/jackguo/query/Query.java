package com.jackguo.query;


import io.swagger.annotations.Api;
import lombok.Data;
//来实现分页操作
@Api(tags = "所查询的基类")
@Data
public abstract class Query {
//    页码
    private Integer page = 1;
    private Integer limit = 10;
}
