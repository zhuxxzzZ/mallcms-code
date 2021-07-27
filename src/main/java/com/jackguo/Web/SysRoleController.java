package com.jackguo.Web;


import com.jackguo.Service.SysRoleService;
import com.jackguo.common.Result;
import com.jackguo.common.validator.ValidatorUtil;
import com.jackguo.form.RoleForm;
import com.jackguo.query.SysRoleQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("/page.do")
    public Result page(SysRoleQuery query){

     Result result= sysRoleService.pageQuery(query);
     return result;

    }

    @RequestMapping("/add.do")
    public Result add(RoleForm roleForm){
        ValidatorUtil.validator(roleForm);
      Result result=  sysRoleService.add(roleForm);
        return  result;
    }

    @RequestMapping("/update.do")
    public  Result update(RoleForm roleForm){
        ValidatorUtil.validator(roleForm);
        Result result =  sysRoleService.update(roleForm);

        return result;

    }

    @RequestMapping("/delete.do")
    public Result delete(int id){
        Result result=sysRoleService.delete(id);
        return result;

    }
    @RequestMapping("/all.do")
    public Result all(){

        Result result=sysRoleService.listAll();
        System.out.println(result);
        return result;

    }

    @RequestMapping("/userRoles.do")
    public Result userRoles(@RequestParam("userId") int userId){

        Result result=sysRoleService.queryUserRoles(userId);
        System.out.println(result);
        return result;

    }

    @RequestMapping("/setRole.do")
    public Result setRole(@RequestParam("userId") Integer userId, @RequestParam(value="roleId") List<Integer> roleId){
        Result result=sysRoleService.setUserRoles(userId,roleId);
        return result;

    }

    @RequestMapping("/setPermission.do")
    public Result setPermission(@RequestParam("roleId") Integer roleId, @RequestParam("permIds") List<Integer> permIds){
        Result result=sysRoleService.setRolePermission(roleId,permIds);
        return result;

    }
//根据角色查询所有权限ID
    @RequestMapping("/permissionIds.do")
    public Result permissionIds(@RequestParam("id") Integer id){
        Result result=sysRoleService.queryPermissionId(id);

        return result;

    }
}

