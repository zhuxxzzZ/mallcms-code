package com.jackguo.Dao;

import com.jackguo.form.CarForm;
import com.jackguo.query.BusCarQuery;
import com.jackguo.vo.BusCarVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusCarMapper {


    List<BusCarVo> selectList(BusCarQuery query);

    List<BusCarVo> selectAll();

    BusCarVo selectCarByNum(BusCarQuery busCarQuery);

    void insert(CarForm carForm);


   Integer  updateRentState(@Param("id") Integer id, @Param("rent") Integer carYesRent, @Param("version") Integer version);

    List<BusCarVo> selectById(int id);

}