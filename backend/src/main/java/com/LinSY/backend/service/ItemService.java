package com.LinSY.backend.service;

import com.LinSY.backend.pojo.Item;
import com.LinSY.backend.utils.pojo.EasyUIDataGrid;
import com.LinSY.backend.utils.pojo.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ItemService  {



    /**
     * 文件上传
     * @param file
     * @return
     */
    Map fileUpload(MultipartFile file) ;


    /**
     * 新增商品
     * @param item
     * @param desc
     * @return
     * @throws Exception
     */
    Result addItem(Item item, String desc) throws Exception;

    /**
     * 获取商品列表
     * @param page
     * @param rows
     * @return
     */
    EasyUIDataGrid getItemList(Integer page, Integer rows) ;

    /**
     * 获取商品描述
     * @param itemId
     * @return
     */
    Result getItemDesc(long itemId) ;

    /**
     * 批量删除商品
     * @param ids
     * @return
     */
    Result deleteItems(Long[] ids) ;

    Result unStockItems(Long[] ids) ;

    /**
     * 商家商品
     * @param ids
     * @return
     */
    Result inStockItems(Long[] ids) ;
}
