package com.LinSY.backend.controller;

import com.LinSY.backend.service.OrderService;
import com.LinSY.backend.utils.pojo.EasyUIDataGrid;
import com.LinSY.backend.utils.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName OrderController
 * @Author LinSY
 * @Date 2018/10/7 13:11
 * @Version 1.0
 * @Description
 */

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService ;

    @ResponseBody
    @RequestMapping("/list")
    public EasyUIDataGrid getOrderList(Integer pageNumber, Integer pageSize, Long userId , int status){

        return orderService.getOrderList(pageNumber, pageSize, userId, status) ;
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addOrder(Long itemId, Long userId, int num){

        return orderService.addOrder(userId, itemId, num) ;
    }
}
