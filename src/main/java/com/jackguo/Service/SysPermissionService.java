package com.jackguo.Service;

import com.jackguo.common.Result;
import com.jackguo.form.SysPermissionForm;
import com.jackguo.query.SyspermissionQuery;

import java.util.List;

public interface SysPermissionService{


    Result currentLeftMenu();

    List<String> queryUserPermissionTags(Integer id);

    Result selectAllPerm(SyspermissionQuery syspermissionQuery);

    Result queryAll();

    Result addPermission(SysPermissionForm form);

    Result updatePermission(SysPermissionForm form);

    Result deletePermission(Integer id);
}
