package com.LinSY.backend.service;

import com.LinSY.backend.utils.pojo.EasyUITreeNode;
import com.LinSY.backend.utils.pojo.Result;

import java.util.List;

public interface CategoryService {


    /**
     * 獲取所有商品類目
     * @return
     */
    List<EasyUITreeNode> getCatList(long parentId) ;

    /**
     * 新增商品分类
     */
    Result addCategory(long parentId, String name) ;

    /**
     * 重命名
     * @param d
     * @param name
     * @return
     */
    Result updateCategory(long d, String name) ;
}
