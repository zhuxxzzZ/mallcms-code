package com.jackguo.common;


import org.apache.ibatis.javassist.ClassPath;

//常量接口
//数据库的1，2对应的值
public interface Constant {
//    系统异常码，数据校验不通过
    Integer PARAM_CHECK_ERROR =4000001;
//    md5加密的盐
    String MD5_SALT="zhou wen yi";
//    默认登陆密码
    String DEFAULT_PASSWORD ="123456";
    String UPLOAD_FOLDER= "upload";

//    未出租
    Integer CAR_NOT_RENT=1;
//    已经出租
    Integer CAR_YES_RENT=2;

    Integer CAR_NO_RETURN=1;

    Integer CAR_YES_RETURN=2;
//    权限类型菜单
    Integer PERMISSION_TYPE_MENU=1;
//    权限按钮
    Integer PERMISSION_TYPE_BTN=2;

    Integer MENU_LV1=0;

    Integer MENUONE_LV1=888;

    Integer MENUTWO_LV1=887;


}
