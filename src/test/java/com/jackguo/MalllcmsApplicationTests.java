package com.jackguo;

import com.jackguo.Dao.BusRentMapper;
import com.jackguo.Dao.BusReturnMapper;
import com.jackguo.Service.BusCustomerServiceImpl;
import com.jackguo.Service.BusReturnServiceImpl;
import com.jackguo.Web.BusCustomerController;
import com.jackguo.common.Result;
import com.jackguo.query.BusReturnQuery;
import com.jackguo.vo.BusCustomerVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MalllcmsApplicationTests {

    @Autowired
    private BusCustomerController busCustomerController;

    @Autowired
    private BusReturnServiceImpl busReturnService;

    @Test
    void contextLoads() {
        BusReturnQuery query= new BusReturnQuery();
        query.setName("用户1");
        Result result = busReturnService.queryPage(query);
        System.out.println(result.getData());
    }

}
