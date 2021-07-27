package com.jackguo.Service;

import com.jackguo.common.Result;
import com.jackguo.entil.BusCar;
import com.jackguo.form.CarForm;
import com.jackguo.query.BusCarQuery;
import com.jackguo.query.BusCustomerQuery;

public interface BusCarService{



    Result queryPage(BusCarQuery query);

    Result queryAll();

    Result add(CarForm carForm);

    Result selectById(int id);
}
