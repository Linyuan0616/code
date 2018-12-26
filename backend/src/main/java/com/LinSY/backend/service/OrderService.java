package com.LinSY.backend.service;

import com.LinSY.backend.utils.pojo.EasyUIDataGrid;

public interface OrderService {

    EasyUIDataGrid getOrderList(Integer page, Integer rows) ;
}
