package com.LinSY.backend.service;

import com.LinSY.backend.utils.pojo.EasyUIDataGrid;
import com.LinSY.backend.utils.pojo.Result;

public interface OrderService {


    public EasyUIDataGrid getOrderList(Integer pageNum, Integer pageSize, long userId, int status) ;

    Result addOrder(Long userId, Long itemId, int num) ;
}
