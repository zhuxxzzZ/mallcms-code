package com.jackguo.entil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRole {
    /**
    * 角色ID
    */
    private Integer id;

    /**
    * 角色名称
    */
    private String name;

    /**
    * 角色标识
    */
    private String tag;

    /**
    * 角色描述
    */
    private String descp;
}