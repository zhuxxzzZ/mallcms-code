package com.jackguo.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jackguo.common.CodeMsg;
import com.jackguo.common.Constant;
import com.jackguo.common.Result;
import com.jackguo.entil.ActiveUser;
import com.jackguo.form.CustomerForm;
import com.jackguo.query.BusCustomerQuery;
import com.jackguo.vo.BusCustomerVo;
import com.jackguo.vo.SysUserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.jackguo.entil.BusCustomer;
import com.jackguo.Dao.BusCustomerMapper;
import com.jackguo.Service.BusCustomerService;

import java.util.List;

@Service
public class BusCustomerServiceImpl implements BusCustomerService{

    @Autowired
    private BusCustomerMapper busCustomerMapper;


    @Override
    public Result queryPage(BusCustomerQuery query) {
        Page<BusCustomerVo> busCustomerVoPage= PageHelper.startPage(query.getPage(), query.getLimit());
        busCustomerMapper.selectList(query);
        Result result = new Result(busCustomerVoPage.toPageInfo());
//       根据参数查询用户
        return result;

    }

    @Override
    public Result add(CustomerForm customerForm) {
        BusCustomerQuery query=new BusCustomerQuery();
        query.setName(customerForm.getName());
        BusCustomerVo busCustomerVo= busCustomerMapper.selectCustomerByNameOrPhoneOrIdCard(query);
        if (busCustomerVo != null){
            return new Result(CodeMsg.CUSTOMER_NAME_EXIST_ERROR);
        }
         query=new BusCustomerQuery();
        query.setPhone(customerForm.getPhone());
         busCustomerVo= busCustomerMapper.selectCustomerByNameOrPhoneOrIdCard(query);
        if (busCustomerVo != null){
            return new Result(CodeMsg.USER_PHONE_EXIST_ERROR);
        }

        query=new BusCustomerQuery();
        query.setIdCard(customerForm.getIdCard());
        busCustomerVo= busCustomerMapper.selectCustomerByNameOrPhoneOrIdCard(query);
        if (busCustomerVo != null){
            return new Result(CodeMsg.USER_IDCARD_EXIST_ERROR);
        }
        busCustomerMapper.insert(customerForm);


        return new Result();

    }

    @Override
    public Result update(CustomerForm customerForm) {
        BusCustomerQuery query=new BusCustomerQuery();
        query.setName(customerForm.getName());
        BusCustomerVo busCustomerVo= busCustomerMapper.selectCustomerByNameOrPhoneOrIdCard(query);
        if (busCustomerVo != null && !busCustomerVo.getId().equals(customerForm.getId())){
            return new Result(CodeMsg.CUSTOMER_NAME_EXIST_ERROR);
        }

        query=new BusCustomerQuery();
        query.setPhone(customerForm.getPhone());
        busCustomerVo= busCustomerMapper.selectCustomerByNameOrPhoneOrIdCard(query);
        if (busCustomerVo != null && !busCustomerVo.getId().equals(customerForm.getId())){
            return new Result(CodeMsg.USER_PHONE_EXIST_ERROR);
        }

        query=new BusCustomerQuery();
        query.setIdCard(customerForm.getIdCard());
        busCustomerVo= busCustomerMapper.selectCustomerByNameOrPhoneOrIdCard(query);
        if (busCustomerVo != null && !busCustomerVo.getId().equals(customerForm.getId())){
            return new Result(CodeMsg.USER_IDCARD_EXIST_ERROR);
        }


        busCustomerMapper.update(customerForm);

        return new Result();
    }

    @Override
    public Result delete(int id) {
        busCustomerMapper.delete(id);

        return new Result();
    }

    @Override
    public Result Login(String name,String password) {
        List<BusCustomerVo> login = busCustomerMapper.Login(name, password);
        if(login.size() ==0){
            return new Result(CodeMsg.USER_PASSWORD_ERROR);
        }else {

        return new Result(login);
    }
    }

    @Override
    public String selectById(int id) {
        String busCustomerVos = busCustomerMapper.selectById(id);

            return busCustomerVos ;

    }



    @Override
    public Result RestPassword(int id, String newPassword) {
        busCustomerMapper.RestPassword(id,newPassword);
        return new Result();
    }
}
