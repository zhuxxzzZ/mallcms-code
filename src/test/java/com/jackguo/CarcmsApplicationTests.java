package com.jackguo;

import com.jackguo.Dao.BusRentMapper;
import com.jackguo.Dao.SysUserMapper;
import com.jackguo.Service.SysPermissionService;
import com.jackguo.Service.SysUserService;
import com.jackguo.common.Result;
import com.jackguo.entil.SysUser;
import com.jackguo.form.RentForm;
import com.jackguo.form.SysUserForm;
import com.jackguo.query.SysUserQuery;
import com.jackguo.query.SyspermissionQuery;
import com.jackguo.vo.SysUserVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CarcmsApplicationTests {

    @Autowired
    private SysPermissionService sysPermissionService;


    @Test
    void contextLoads() {


    }

}
