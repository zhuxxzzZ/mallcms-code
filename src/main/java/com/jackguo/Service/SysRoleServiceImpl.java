package com.jackguo.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jackguo.common.Result;
import com.jackguo.form.RoleForm;
import com.jackguo.query.SysRoleQuery;
import com.jackguo.vo.SysPermissionVo;
import com.jackguo.vo.SysRoleVo;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jackguo.Dao.SysRoleMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService{

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public Result pageQuery(SysRoleQuery query) {

       Page<SysRoleVo> sysRoleVo= PageHelper.startPage(query.getPage(),query.getLimit());
       sysRoleMapper.selectByName(query);

        return new Result( sysRoleVo.toPageInfo());

    }

    @Override
    public Result add(RoleForm roleForm) {

      sysRoleMapper.insert(roleForm);

            return new Result();
    }

    @Override
    public Result update(RoleForm roleForm) {

        sysRoleMapper.update(roleForm);
        return new Result();
    }

    @Override
    public Result delete(int id) {
        sysRoleMapper.delete(id);
        return new Result();
    }

    @Override
    public Result listAll() {
        List<SysRoleVo> sysRoleVos = sysRoleMapper.selectByName(new SysRoleQuery());
        return new Result(sysRoleVos);
    }

    @Override
    public Result queryUserRoles(int userId) {
       List<SysRoleVo> roleVos= sysRoleMapper.selectListRoles(userId);

        return new Result(roleVos);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result setUserRoles(Integer userId, List<Integer> roleId) {
        sysRoleMapper.deleteUserRoles(userId);

        sysRoleMapper.insertUserRoles(userId,roleId);

        return new Result();
    }

    @Override
    public List<String> queryUserRolesTags(Integer id) {

      List<SysRoleVo> userRoles =  sysRoleMapper.selectListRoles(id);
      List<String> roleTags=new ArrayList<>();
//      userRoles.forEach(role->{
//          roleTags.add(role.getTag());
//      });
        for (SysRoleVo userRole : userRoles) {
            roleTags.add(userRole.getTag());
        }
        System.out.println(roleTags);
        return roleTags ;
    }

    @Override
    public Result queryPermissionId(Integer id) {

      List<Integer> pid=  sysRoleMapper.queryPermissionById(id);

        return new Result(pid);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result setRolePermission(Integer roleId, List<Integer> permIds) {
        sysRoleMapper.deleteRolePermission(roleId);
        sysRoleMapper.insertRolePermission(roleId,permIds);



        return new Result();
    }


}
