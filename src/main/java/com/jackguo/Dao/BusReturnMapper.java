package com.jackguo.Dao;

import com.jackguo.form.ReturnForm;
import com.jackguo.query.BusReturnQuery;
import com.jackguo.vo.BusReturnVo;

import java.util.List;

public interface BusReturnMapper {


    void insert(ReturnForm returnForm);

    List<BusReturnVo> selectByNum(BusReturnQuery query);

    List<BusReturnVo> selectListByName(BusReturnQuery query);

}