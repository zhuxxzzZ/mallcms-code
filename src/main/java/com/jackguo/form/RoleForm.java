package com.jackguo.form;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RoleForm {

    private String id;

    @NotEmpty(message = "角色名称不能为空")
    private  String name;

    @NotEmpty(message = "角色标识不能为空")
    private  String tag;

    private  String descp;
}
