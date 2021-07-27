package com.jackguo.entil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysPermission {
    /**
    * 权限ID
    */
    private Integer id;

    /**
    * 权限名称
    */
    private String title;

    /**
    * 权限图标
    */
    private String icon;

    /**
    * 权限连接 菜单连接
    */
    private String href;

    /**
    * 是否展开1展开0不展开
    */
    private Boolean spread;

    /**
    * 权限类型1 菜单 2 按钮
    */
    private Integer type;

    /**
    * 权限自定义标识
    */
    private String tag;

    /**
    * 排序值，越大越靠前 控制权限的显示排序
    */
    private String sort;

    /**
    * 父权限ID
    */
    private Integer parentId;
}