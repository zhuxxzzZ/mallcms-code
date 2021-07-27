package com.jackguo.query;


import lombok.Data;

@Data
public class BusRentQuery extends Query{

    private  String num;
    private  String name;
    private  String flag;
    private  String beginTime;
    private  String maxBeginTime;
    private  String minBeginTime;





}
