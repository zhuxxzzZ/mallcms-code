package com.jackguo.Service;

import com.jackguo.common.Result;
import com.jackguo.form.RoleForm;
import com.jackguo.query.SysRoleQuery;

import java.util.List;

public interface SysRoleService{


    Result pageQuery(SysRoleQuery query);

    Result add(RoleForm roleForm);

    Result update(RoleForm roleForm);

    Result delete(int id);

    Result listAll();

    Result queryUserRoles(int userId);

    Result setUserRoles(Integer userId, List<Integer> roleId);

    List<String> queryUserRolesTags(Integer id);

    Result queryPermissionId(Integer id);

    Result setRolePermission(Integer roleId, List<Integer> permIds);
}
