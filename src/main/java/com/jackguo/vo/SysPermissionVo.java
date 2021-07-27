package com.jackguo.vo;



import com.jackguo.entil.SysPermission;
import lombok.Data;

import java.util.List;

@Data
public class SysPermissionVo extends SysPermission {


    private List<SysPermissionVo> children;

//    dtree复选框标记
    private String checkArr= "0";
}
