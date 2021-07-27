package com.jackguo.Service;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jackguo.Dao.BusCarMapper;
import com.jackguo.Dao.BusCustomerMapper;
import com.jackguo.common.CodeMsg;
import com.jackguo.common.Constant;
import com.jackguo.common.Result;
import com.jackguo.common.exception.BussiException;
import com.jackguo.entil.ActiveUser;
import com.jackguo.form.RentForm;
import com.jackguo.query.BusCarQuery;
import com.jackguo.query.BusCustomerQuery;
import com.jackguo.query.BusRentQuery;
import com.jackguo.query.Query;
import com.jackguo.vo.BusCarVo;
import com.jackguo.vo.BusCustomerVo;
import com.jackguo.vo.BusRentVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.jackguo.Dao.BusRentMapper;
import com.jackguo.Service.BusRentService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BusRentServiceImpl implements BusRentService{

    @Autowired
    private BusRentMapper busRentMapper;

    @Autowired
    private BusCustomerMapper busCustomerMapper;

    @Autowired
    private BusCarMapper busCarMapper;


    @Override
    public Result page(BusRentQuery query) {
        Page<BusRentVo> busRentVo =PageHelper.startPage(query.getPage(),query.getLimit());
        if(StrUtil.isNotEmpty(query.getBeginTime())){

            String[] split = query.getBeginTime().split("~");
            query.setMinBeginTime(split[0].trim());
            query.setMaxBeginTime(split[1].trim());
        }


         busRentMapper.selectList(query);
        Result result=new Result(busRentVo.toPageInfo());
        return result;
    }


    @Override
    @Transactional(rollbackFor =Exception.class)
    public Result add(RentForm rentForm) {
//        业务数据校验
//        校验客户是否存在根据身份证号查询客户
        BusCustomerQuery query=new BusCustomerQuery();
        query.setIdCard(rentForm.getIdCard());
        BusCustomerVo busCustomerVo = busCustomerMapper.selectCustomerByNameOrPhoneOrIdCard(query);
        System.out.println(busCustomerVo .toString());
        if (busCustomerVo == null){
            return  new Result(CodeMsg.CURTOMER_RENT_ERROR);

        }
//        检验车辆的信息
//        根据车牌号查询车辆信息
        BusCarQuery busCarQuery=new BusCarQuery();
        busCarQuery.setNum(rentForm.getNum());
        BusCarVo busCarVo=busCarMapper.selectCarByNum(busCarQuery);

        if(busCarVo.getIsRent().equals(Constant.CAR_YES_RENT)){
            return new Result(CodeMsg.RENT_CAR_RENT_ERROR);

        }
//        修改车辆状态，新增出租记录
        //        乐观锁处理并发问题
        Integer rows = busCarMapper.updateRentState(busCarVo.getId(), Constant.CAR_YES_RENT, busCarVo.getVersion());
        if(rows !=1){
            throw  new BussiException(CodeMsg.RENT_FAILD_STATE_ERROR.code,CodeMsg.USER_PHONE_EXIST_ERROR.msg );


        }
        String rentTime = rentForm.getRentTime();
        String[] split = rentTime.split("~");
        rentForm.setBeginTime(split[0].trim());
        rentForm.setEndTime(split[1].trim());
        rentForm.setName(busCustomerVo.getName());
        System.out.println(rentForm.getName());
        System.out.println();


        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser= (ActiveUser) subject.getPrincipal();
        Integer sid = activeUser.getSysUser().getId();

        rentForm.setUserId(sid);
        System.out.println(rentForm.getUserId());


        busRentMapper.insert(rentForm);
//        设置客户姓名和操作员id

        return new Result();
    }


    @Transactional(rollbackFor =Exception.class)
    @Override
    public Result addCus(RentForm rentForm) {
        //        业务数据校验
//        校验客户是否存在根据身份证号查询客户
        BusCustomerQuery query=new BusCustomerQuery();
        query.setIdCard(rentForm.getIdCard());
        BusCustomerVo busCustomerVo = busCustomerMapper.selectCustomerByNameOrPhoneOrIdCard(query);
        System.out.println(busCustomerVo .toString());
        if (busCustomerVo == null){
            return  new Result(CodeMsg.CURTOMER_RENT_ERROR);

        }
//        检验车辆的信息
//        根据车牌号查询车辆信息
        BusCarQuery busCarQuery=new BusCarQuery();
        busCarQuery.setNum(rentForm.getNum());
        BusCarVo busCarVo=busCarMapper.selectCarByNum(busCarQuery);

        if(busCarVo.getIsRent().equals(Constant.CAR_YES_RENT)){
            return new Result(CodeMsg.RENT_CAR_RENT_ERROR);

        }
//        修改车辆状态，新增出租记录
        //        乐观锁处理并发问题
        Integer rows = busCarMapper.updateRentState(busCarVo.getId(), Constant.CAR_YES_RENT, busCarVo.getVersion());
        if(rows !=1){
            throw  new BussiException(CodeMsg.RENT_FAILD_STATE_ERROR.code,CodeMsg.USER_PHONE_EXIST_ERROR.msg );


        }
        String rentTime = rentForm.getRentTime();
        String[] split = rentTime.split("~");
        rentForm.setBeginTime(split[0].trim());
        rentForm.setEndTime(split[1].trim());
        rentForm.setName(busCustomerVo.getName());
        System.out.println(rentForm.getName());

        busRentMapper.insertCus(rentForm);
//        设置客户姓名和操作员id

        return new Result();
    }

    @Override
    public Result selectByName(BusRentQuery query) {
        Page<BusRentVo> busRentVo =PageHelper.startPage(query.getPage(),query.getLimit());
        if(StrUtil.isNotEmpty(query.getBeginTime())){

            String[] split = query.getBeginTime().split("~");
            query.setMinBeginTime(split[0].trim());
            query.setMaxBeginTime(split[1].trim());
        }

        busRentMapper.selectListByName(query);
        Result result=new Result(busRentVo.toPageInfo());
        return result;
    }

}
