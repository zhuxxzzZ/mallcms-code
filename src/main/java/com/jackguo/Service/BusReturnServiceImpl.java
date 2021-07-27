package com.jackguo.Service;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jackguo.Dao.BusCarMapper;
import com.jackguo.Dao.BusRentMapper;
import com.jackguo.common.CodeMsg;
import com.jackguo.common.Constant;
import com.jackguo.common.Result;
import com.jackguo.common.exception.BussiException;
import com.jackguo.entil.ActiveUser;
import com.jackguo.form.RentForm;
import com.jackguo.form.ReturnForm;
import com.jackguo.query.BusCarQuery;
import com.jackguo.query.BusRentQuery;
import com.jackguo.query.BusReturnQuery;
import com.jackguo.vo.BusCarVo;
import com.jackguo.vo.BusRentVo;
import com.jackguo.vo.BusReturnVo;
import jdk.jfr.events.ThrowablesEvent;
import net.bytebuddy.dynamic.TypeResolutionStrategy;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.jackguo.Dao.BusReturnMapper;
import com.jackguo.Service.BusReturnService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BusReturnServiceImpl implements BusReturnService {

    @Autowired
    private BusReturnMapper busReturnMapper;

    @Autowired
    private BusRentMapper busRentMapper;

    @Autowired
    private BusCarMapper busCarMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result add(ReturnForm returnForm) {
//        业务数据校验,查询出租记录判断出租记录状态，
//        查询出租记录，检查出租记录.是否还车没有还车才能还车，修改出租状态，改为已经还车
//       插入还车记录，修改车辆状态，将已出租改为未出租，计算出租总金额

        BusRentVo busRentVo = busRentMapper.selectById(returnForm.getRentId());
        if (busRentVo.getFlag().equals(Constant.CAR_YES_RETURN) ){
            return  new Result(CodeMsg.RETURN_CAR_ERROR);
        }
        //如果没有还则修改还车记录状态
      int row= busRentMapper.updateByFlag(returnForm.getRentId(),busRentVo.getFlag(),Constant.CAR_YES_RETURN);
        if (row !=1) {
                return new Result(CodeMsg.RETURN_RENT_ERROR) ;
        }
//        修改车辆状态
        BusCarQuery busCarQuery=new BusCarQuery();
        busCarQuery.setNum(returnForm.getNum());
        BusCarVo busCarVo=busCarMapper.selectCarByNum(busCarQuery);
        Integer rows = busCarMapper.updateRentState(busCarVo.getId(), Constant.CAR_NOT_RENT, busCarVo.getVersion());
        if(rows !=1){
            throw new BussiException(CodeMsg.RETURN_FAILD_CAR_CHANGE_ERROR.code,CodeMsg.RETURN_FAILD_CAR_CHANGE_ERROR.msg);
        }
//        计算出租总金额
        String beginTime=busRentVo.getBeginTime();
        String returnTime=returnForm.getReturnTime();
//        出租的单日租金
        int rentPrice =busRentVo.getRentPrice();
        Date begin=DateUtil.parse(beginTime,"yyyy-MM-dd");
        Date end=DateUtil.parse(returnTime,"yyyy-MM-dd");
        int l = (int) DateUtil.betweenDay(begin, end, true);
        rentPrice=rentPrice*l; //计算总租金
//        计算总金额
        int totalMoney=rentPrice+returnForm.getPayMoney();
//        设置总租金和总金额
        returnForm.setRentPrice(rentPrice);
        returnForm.setTotalMoney(totalMoney);
        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser= (ActiveUser) subject.getPrincipal();
        Integer sid = activeUser.getSysUser().getId();
        returnForm.setUserId(sid);
        returnForm.setName(busRentVo.getName());
//        添加返还信息
        busReturnMapper.insert(returnForm);

        return new Result();
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addCus(ReturnForm returnForm) {
//        业务数据校验,查询出租记录判断出租记录状态，
//        查询出租记录，检查出租记录.是否还车没有还车才能还车，修改出租状态，改为已经还车
//       插入还车记录，修改车辆状态，将已出租改为未出租，计算出租总金额

        BusRentVo busRentVo = busRentMapper.selectById(returnForm.getRentId());
        if (busRentVo.getFlag().equals(Constant.CAR_YES_RETURN) ){
            return  new Result(CodeMsg.RETURN_CAR_ERROR);
        }
        //如果没有还则修改还车记录状态
        int row= busRentMapper.updateByFlag(returnForm.getRentId(),busRentVo.getFlag(),Constant.CAR_YES_RETURN);
        if (row !=1) {
            return new Result(CodeMsg.RETURN_RENT_ERROR) ;
        }
//        修改车辆状态
        BusCarQuery busCarQuery=new BusCarQuery();
        busCarQuery.setNum(returnForm.getNum());
        BusCarVo busCarVo=busCarMapper.selectCarByNum(busCarQuery);
        Integer rows = busCarMapper.updateRentState(busCarVo.getId(), Constant.CAR_NOT_RENT, busCarVo.getVersion());
        if(rows !=1){
            throw new BussiException(CodeMsg.RETURN_FAILD_CAR_CHANGE_ERROR.code,CodeMsg.RETURN_FAILD_CAR_CHANGE_ERROR.msg);
        }


        returnForm.setRentPrice(busCarVo.getRentPrice());

//        添加返还信息
        busReturnMapper.insert(returnForm);

        return new Result();
    }



    @Override
    public Result queryPage(BusReturnQuery query) {
        Page<BusReturnVo> busReturnVo = PageHelper.startPage(query.getPage(), query.getLimit());
        busReturnMapper.selectByNum(query);

        return new Result( busReturnVo.toPageInfo());
    }


    @Override
    public Result queryPageCus(BusReturnQuery query) {
        Page<BusReturnVo> busReturnVo = PageHelper.startPage(query.getPage(), query.getLimit());
        busReturnMapper.selectListByName(query);
        Result result=new Result(busReturnVo.toPageInfo());
        return result;
    }
}

