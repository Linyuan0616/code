package com.LinSY.backend.service.impl;

import com.LinSY.backend.mapper.ItemMapper;
import com.LinSY.backend.mapper.OrderMapper;
import com.LinSY.backend.pojo.Item;
import com.LinSY.backend.pojo.ItemExample;
import com.LinSY.backend.pojo.Order;
import com.LinSY.backend.service.ItemService;
import com.LinSY.backend.utils.TimeIDUtil;
import com.LinSY.backend.utils.pojo.EasyUIDataGrid;
import com.LinSY.backend.utils.pojo.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName ItemServiceImpl
 * @Author LinSY
 * @Date 2018/10/1 1:07
 * @Version 1.0
 * @Description
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper ;
    @Autowired
    private OrderMapper orderMapper ;

    @Override
    public EasyUIDataGrid getItemList(Integer page, Integer rows) {
        //查询商品列表
        ItemExample example = new ItemExample();
        example.createCriteria().andStatusEqualTo((byte) 1) ;
        //分页处理
        PageHelper.startPage(page, rows);
        List<Item> list = itemMapper.selectByExample(example);
        //创建一个返回值对象
        EasyUIDataGrid result = new EasyUIDataGrid();
        result.setRows(list);
        //取记录总条数
        PageInfo<Item> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public Result purchase(long itemId, long userId, Integer num) {

        Order order = new Order();
        order.setCreatTime(new Date());
        order.setUpdateTime(new Date());
        order.setItemId(itemId);
        order.setUserId(userId);
        order.setNum(num);
        order.setOrderId(TimeIDUtil.genItemId());

        try {
            orderMapper.insert(order);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(500, "购买失败！");
        }

        return Result.ok() ;
    }
}
