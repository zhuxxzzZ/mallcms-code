package com.jackguo.Dao;

import com.jackguo.form.RentForm;
import com.jackguo.query.BusRentQuery;
import com.jackguo.vo.BusRentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Profile;

import java.util.List;

public interface BusRentMapper {

    void insert(RentForm rentForm);

    void insertCus(RentForm rentForm);

    List<BusRentVo> selectList(BusRentQuery query);


    Integer  updateByFlag(@Param("id") Integer id,@Param("oldFlag") Integer oldFlag,@Param("flag") Integer carYesReturn);

    BusRentVo selectById(Integer id);

    List<BusRentVo> selectListByName(BusRentQuery query);

}