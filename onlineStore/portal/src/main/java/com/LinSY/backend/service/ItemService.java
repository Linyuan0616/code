package com.LinSY.backend.service;

import com.LinSY.backend.utils.pojo.EasyUIDataGrid;
import com.LinSY.backend.utils.pojo.Result;

public interface ItemService {

    /**
     * 获取商品列表
     * @return
     */
    public EasyUIDataGrid getItemList(Integer page, Integer rows) ;

    public Result purchase(long itemId, long userId, Integer num);
}
