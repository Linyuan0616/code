package com.LinSY.backend.controller;

import com.LinSY.backend.service.ItemService;
import com.LinSY.backend.utils.JSONUtil;
import com.LinSY.backend.utils.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName ItemController
 * @Author LinSY
 * @Date 2018/10/1 1:05
 * @Version 1.0
 * @Description 商品 controller
 */

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService ;

    @ResponseBody
    @RequestMapping("/list")
    public String getItemList(Integer pageNumber, Integer pageSize){
        return JSONUtil.objectToJson(itemService.getItemList(pageNumber, pageSize) );
    }

    @ResponseBody
    @RequestMapping("/purchase")
    public Result purchase(long itemId, long userId, Integer num){
        return itemService.purchase(itemId, userId, num);
    }

}
