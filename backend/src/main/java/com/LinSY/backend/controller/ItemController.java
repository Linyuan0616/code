package com.LinSY.backend.controller;

import com.LinSY.backend.pojo.Item;
import com.LinSY.backend.service.ItemService;
import com.LinSY.backend.utils.pojo.EasyUIDataGrid;
import com.LinSY.backend.utils.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName ItemController
 * @Author LinSY
 * @Date 2018/9/27 14:00
 * @Version 1.0
 * @Description 商品编辑 controller
 */

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService ;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result itemadd(Item item, String desc) throws Exception {
        return itemService.addItem(item, desc) ;
    }

    @RequestMapping("/list")
    @ResponseBody
    public EasyUIDataGrid getItemList(Integer page, Integer rows) {
        EasyUIDataGrid result = itemService.getItemList(page, rows);
        return result;
    }

    @RequestMapping("/desc/{itemId}")
    @ResponseBody
    public Result getItemDesc(@PathVariable Long itemId) {
        return itemService.getItemDesc(itemId) ;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteItems(Long[] ids) {
        return itemService.deleteItems(ids) ;
    }

    @RequestMapping(value = "/unstock", method = RequestMethod.POST)
    @ResponseBody
    public Result unStockItems(Long[] ids) {
        return itemService.unStockItems(ids) ;
    }

    @RequestMapping(value = "/instock", method = RequestMethod.POST)
    @ResponseBody
    public Result nStockItems(Long[] ids) {
        return itemService.inStockItems(ids) ;
    }
}
