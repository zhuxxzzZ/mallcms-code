package com.jackguo.Web;


import com.jackguo.Service.SysPermissionService;
import com.jackguo.common.Result;
import com.jackguo.entil.SysPermission;
import com.jackguo.form.SysPermissionForm;
import com.jackguo.query.SyspermissionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/permission")
public class SysPermissionController {

    @Autowired
    private SysPermissionService sysPermissionService;


    @RequestMapping("/leftMenu.do")
    public Object getUserMenu(){

     Result result= sysPermissionService.currentLeftMenu();

     return  result;


    }


    @RequestMapping("/all.do")
    public Object allPermission(){

        Result result= sysPermissionService.queryAll();

        return  result;

    }

    @RequestMapping("page.do")
    public Object PagePermission(SyspermissionQuery syspermissionQuery){

        Result result= sysPermissionService.selectAllPerm(syspermissionQuery);

        return  result;

    }

    @RequestMapping("add.do")
    public Object addPermission(SysPermissionForm form){

        Result result= sysPermissionService.addPermission(form);

        return  result;

    }
    @RequestMapping("update.do")
    public Object updatePermission(SysPermissionForm form){

        Result result= sysPermissionService.updatePermission(form);

        return  result;

    }

    @RequestMapping("delete.do")
    public Object deletePermission(@RequestParam("id") Integer id){

        Result result= sysPermissionService.deletePermission(id);

        return  result;

    }

}
