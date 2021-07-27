package com.jackguo.Dao;

import com.jackguo.form.RoleForm;
import com.jackguo.query.SysRoleQuery;
import com.jackguo.vo.SysPermissionVo;
import com.jackguo.vo.SysRoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {

    List<SysRoleVo> selectByName(SysRoleQuery query);

    void insert(RoleForm roleForm);

    void update(RoleForm roleForm);

    void delete(int id);

    List<SysRoleVo> selectListRoles(@Param("id") int userId);

    void deleteUserRoles(@Param("id")int userId);

    void insertUserRoles(@Param("userId") int userId, @Param("roleIds") List<Integer> roleId);

    List<Integer> queryPermissionById(@Param("id") Integer id);

    void deleteRolePermission(@Param("roleId") Integer roleId);

    void insertRolePermission(@Param("roleId") Integer roleId,@Param("perIds") List<Integer> permIds);
}