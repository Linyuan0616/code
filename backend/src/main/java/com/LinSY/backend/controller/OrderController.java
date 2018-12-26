package com.LinSY.backend.controller;

import com.LinSY.backend.service.OrderService;
import com.LinSY.backend.utils.pojo.EasyUIDataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName OrderController
 * @Author LinSY
 * @Date 2018/10/9 14:33
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
    public EasyUIDataGrid getOrderList(Integer page, Integer rows){
        return orderService.getOrderList(page, rows);
    }

}
