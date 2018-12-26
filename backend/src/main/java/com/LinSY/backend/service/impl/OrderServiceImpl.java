package com.LinSY.backend.service.impl;

import com.LinSY.backend.mapper.OrderMapper;
import com.LinSY.backend.pojo.Item;
import com.LinSY.backend.pojo.ItemExample;
import com.LinSY.backend.pojo.Order;
import com.LinSY.backend.pojo.OrderExample;
import com.LinSY.backend.service.OrderService;
import com.LinSY.backend.utils.pojo.EasyUIDataGrid;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName OrderServiceImpl
 * @Author LinSY
 * @Date 2018/10/9 14:30
 * @Version 1.0
 * @Description
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public EasyUIDataGrid getOrderList(Integer page, Integer rows) {
        //查询商品列表
        OrderExample example = new OrderExample();
        //分页处理
        PageHelper.startPage(page, rows);
        List<Order> list = orderMapper.selectByExample(example);
        //创建一个返回值对象
        EasyUIDataGrid result = new EasyUIDataGrid();
        result.setRows(list);
        //取记录总条数
        PageInfo<Order> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
