package com.jackguo.Service;

import com.jackguo.common.Result;
import com.jackguo.form.RentForm;
import com.jackguo.query.BusRentQuery;

public interface BusRentService{


    Result page(BusRentQuery query);

    Result add(RentForm rentForm);

    Result addCus(RentForm rentForm);

    Result selectByName(BusRentQuery query);
}
