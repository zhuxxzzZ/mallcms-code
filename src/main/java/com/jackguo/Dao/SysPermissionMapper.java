package com.jackguo.Dao;

import com.jackguo.form.SysPermissionForm;
import com.jackguo.query.SyspermissionQuery;
import com.jackguo.vo.SysPermissionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPermissionMapper {
    List<SysPermissionVo> selectList(@Param("userId") Integer userId, @Param("type") Integer type);

    List<SysPermissionVo> selectListTags(@Param("id") Integer id);

    List<SysPermissionVo> queryAllPermByTitle(SyspermissionQuery syspermissionQuery);

    void insert(SysPermissionForm form);

    void update(SysPermissionForm form);

    void delete(@Param("id") Integer id);

//    根据父id查询所有子ID
    List<Integer> selectAllChildId(@Param("ids")List<Integer> ids);

//    根据id实现批量删除权限
    void batchDelete(@Param("ids")List<Integer> ids);

//    批量删除角色权限关系
    void batchDeleteRel(@Param("ids")List<Integer> ids);
}