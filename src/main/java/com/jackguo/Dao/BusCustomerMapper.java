package com.jackguo.Dao;

import com.jackguo.form.CustomerForm;
import com.jackguo.query.BusCustomerQuery;
import com.jackguo.vo.BusCustomerVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Repository
public interface BusCustomerMapper {

    List<BusCustomerVo> Login(@Param("name") String name,@Param("password") String password);

    List<BusCustomerVo> selectList(BusCustomerQuery query);

    BusCustomerVo selectCustomerByNameOrPhoneOrIdCard(BusCustomerQuery query);

    void insert(CustomerForm customerForm);

    void update(CustomerForm customerForm);

    void delete(int id);

    String selectById(int id);

    void RestPassword(@Param("id") int id,@Param("newPassword") String newPassword);




}