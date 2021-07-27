package com.jackguo.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jackguo.common.Constant;
import com.jackguo.common.Result;
import com.jackguo.entil.ActiveUser;
import com.jackguo.form.SysPermissionForm;
import com.jackguo.query.SyspermissionQuery;
import com.jackguo.vo.SysPermissionVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.jackguo.Dao.SysPermissionMapper;
import com.jackguo.Service.SysPermissionService;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SysPermissionServiceImpl implements SysPermissionService{

    @Autowired
    private SysPermissionMapper sysPermissionMapper;


    @Override
    public Result currentLeftMenu() {
        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser =(ActiveUser) subject.getPrincipal();
        Integer userId = activeUser.getSysUser().getId();
//        查询出用户所有的菜单
        List<SysPermissionVo> sysPermissionVos = sysPermissionMapper.selectList(userId, Constant.PERMISSION_TYPE_MENU);
//      将数组转化为前端所能接受的json格式，转换为父子关系
        Map<Integer,SysPermissionVo> menu=new HashMap<>();


        for (SysPermissionVo vo : sysPermissionVos) {
//            获取父菜单如果为0则为一级
            Integer parentId = vo.getParentId();
            if (parentId.equals(Constant.MENU_LV1)){
//                初始化一级菜单容器
                vo.setChildren(new ArrayList<SysPermissionVo>());
                menu.put(vo.getId(),vo);

            }

        }
//        便利所有菜单，为一级菜单设置二级菜单
        for (SysPermissionVo vo : sysPermissionVos) {
//            判断一级菜单容器中是否有当前菜单的父菜单
            if(menu.containsKey(vo.getParentId())){
//                获取`当前菜单的父菜单
                SysPermissionVo vo1 = menu.get(vo.getParentId());
//                将当前菜单放入到一级菜单下
                vo1.getChildren().add(vo);


            }

        }
//        获取mao中所有一级菜单的集合
        Collection<SysPermissionVo> values = menu.values();

        return new Result(values);
    }


    @Override
    public List<String> queryUserPermissionTags(Integer id) {
    List<SysPermissionVo> sysPermissionVo=sysPermissionMapper.selectListTags(id);
    List<String> permissionTags=new ArrayList<>();
        for (SysPermissionVo vo : sysPermissionVo) {
            permissionTags.add(vo.getTag());

        }
        System.out.println(permissionTags);
        return permissionTags;
    }

    @Override
    public Result selectAllPerm(SyspermissionQuery syspermissionQuery) {
      Page<SysPermissionVo>  vo = PageHelper.startPage(syspermissionQuery.getPage(), syspermissionQuery.getLimit());
       sysPermissionMapper.queryAllPermByTitle(syspermissionQuery);
        return new Result(vo.toPageInfo());
    }

    @Override
    public Result queryAll() {
    List<SysPermissionVo> sysPermissionVOS = sysPermissionMapper.queryAllPermByTitle(new SyspermissionQuery());
    return new Result(sysPermissionVOS);

    }

    @Override
    public Result addPermission(SysPermissionForm form) {
        sysPermissionMapper.insert(form);
        return new Result();
    }

    @Override
    public Result updatePermission(SysPermissionForm form) {
        sysPermissionMapper.update(form);
        return new Result();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deletePermission(Integer id) {
        /**
         *  1. 将所有子权限查询出来
         *  2. 将当前权限ID 和 子权限ID 统一删除
         *  3. 将将当前权限ID 和 子权限ID 相应的角色权限关系表数据删除
         */
        //当前ID  和  子ID 容器
        List<Integer> ids = new ArrayList<>();
        //当前ID 存进去
        ids.add(id);  //
        //根据当前ID 查询子权限
        //二级
        List<Integer> childIdLv2 =  sysPermissionMapper.selectAllChildId(ids);

        //三级
        List<Integer> childIdLv3 = new ArrayList<>();
        if (!childIdLv2.isEmpty()){
            childIdLv3 = sysPermissionMapper.selectAllChildId(childIdLv2);
        }
        //将二级菜单数据合并
        ids.addAll(childIdLv2);
        //将三级菜单数据合并
        ids.addAll(childIdLv3);
        //将权限删除
        sysPermissionMapper.batchDelete(ids);
        //将角色权限关系删除
        sysPermissionMapper.batchDeleteRel(ids);
        return new Result();
    }

}
