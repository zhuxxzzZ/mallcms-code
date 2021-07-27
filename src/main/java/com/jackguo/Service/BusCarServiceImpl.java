package com.jackguo.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jackguo.common.CodeMsg;
import com.jackguo.common.Result;
import com.jackguo.form.CarForm;
import com.jackguo.query.BusCarQuery;
import com.jackguo.query.BusCustomerQuery;
import com.jackguo.vo.BusCarVo;
import com.jackguo.vo.BusCustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.jackguo.entil.BusCar;
import com.jackguo.Dao.BusCarMapper;
import com.jackguo.Service.BusCarService;

import java.util.List;

@Service
public class BusCarServiceImpl implements BusCarService{

    @Autowired
    private BusCarMapper busCarMapper;


    @Override
    public Result queryPage(BusCarQuery query) {
        Page<BusCarVo> busCarVoPage= PageHelper.startPage(query.getPage(), query.getLimit());
        busCarMapper.selectList(query);
        Result result = new Result(busCarVoPage.toPageInfo());

//       根据参数查询用户
        return result;

    }

    @Override
    public Result queryAll() {
        List<BusCarVo> busCarVos = busCarMapper.selectAll();
        return new Result(busCarVos);
    }


    @Override
    public Result add(CarForm carForm) {
        BusCarQuery busCarQuery = new BusCarQuery();
        busCarQuery.setNum(carForm.getNum());
        BusCarVo busCarVo=busCarMapper.selectCarByNum(busCarQuery);
        if (busCarVo !=null){
            return new Result(CodeMsg.CAR_NUM_ERROR);

        }
        busCarMapper.insert(carForm);


        return new Result();
    }

    @Override
    public Result selectById(int id) {
        List<BusCarVo> busCarVos = busCarMapper.selectById(id);
        if (busCarVos.size() ==0){
            return  new Result(CodeMsg.CAR_NUM_ERROR);
        }
        return new Result(busCarVos);
    }


}
