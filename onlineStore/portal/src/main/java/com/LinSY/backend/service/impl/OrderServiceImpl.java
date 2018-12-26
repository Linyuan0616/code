package com.LinSY.backend.service.impl;

import com.LinSY.backend.mapper.OrderMapper;
import com.LinSY.backend.mapper.UserDepositMapper;
import com.LinSY.backend.pojo.Order;
import com.LinSY.backend.pojo.OrderExample;
import com.LinSY.backend.pojo.UserDeposit;
import com.LinSY.backend.service.OrderService;
import com.LinSY.backend.utils.TimeIDUtil;
import com.LinSY.backend.utils.pojo.EasyUIDataGrid;
import com.LinSY.backend.utils.pojo.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName OrderServiceImpl
 * @Author LinSY
 * @Date 2018/10/7 13:14
 * @Version 1.0
 * @Description
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper ;
    @Autowired
    private UserDepositMapper userDepositMapper;

    @Override
    public EasyUIDataGrid getOrderList(Integer pageNum, Integer pageSize, long userId, int status) {
        //查询商品列表
        OrderExample example = new OrderExample();
        example.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(status) ;
        //分页处理
        PageHelper.startPage(pageNum, pageSize);
        List<Order> list = orderMapper.selectByExample(example);
        //创建一个返回值对象
        EasyUIDataGrid result = new EasyUIDataGrid();
        result.setRows(list);
        //取记录总条数
        PageInfo<Order> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public Result addOrder(Long userId, Long itemId, int num) {

        //  新增订单
        Order order = new Order() ;
        Long orderId = TimeIDUtil.genItemId() ;
        order.setOrderId(orderId);
        order.setNum(num);
        order.setUserId(userId);
        order.setItemId(itemId);
        order.setUpdateTime(new Date());
        order.setCreatTime(new Date());
        order.setStatus(0);

        // 新增商品库存
        UserDeposit userDeposit = new UserDeposit();
        userDeposit.setItemId(itemId);
        userDeposit.setNum(num);
        userDeposit.setUserId(userId);

        try {
            orderMapper.insert(order);
            userDepositMapper.insert(userDeposit);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(500, "purchase error!") ;
        }

        return Result.ok(new HashMap<>().put("orderId", orderId));
    }
}
