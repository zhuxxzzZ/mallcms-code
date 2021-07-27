package com.jackguo.Service;

import com.jackguo.common.Result;
import com.jackguo.form.ReturnForm;
import com.jackguo.query.BusReturnQuery;

public interface BusReturnService {


    Result add(ReturnForm returnForm);
    Result addCus(ReturnForm returnForm);

    Result queryPage(BusReturnQuery query);
    Result queryPageCus(BusReturnQuery query);
}

