package com.LinSY.backend.service.impl;

import com.LinSY.backend.mapper.ItemCatMapper;
import com.LinSY.backend.pojo.ItemCat;
import com.LinSY.backend.pojo.ItemCatExample;
import com.LinSY.backend.service.CategoryService;
import com.LinSY.backend.utils.pojo.EasyUITreeNode;
import com.LinSY.backend.utils.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CategoryServiceImpl
 * @Author LinSY
 * @Date 2018/9/28 23:56
 * @Version 1.0
 * @Description 商品分类管理 service
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ItemCatMapper itemCatMapper ;

    @Override
    public List<EasyUITreeNode> getCatList(long parentId) {

        ItemCatExample example = new ItemCatExample() ;
        example.createCriteria().andParentIdEqualTo(parentId) ;
        List<ItemCat> itemCatList = itemCatMapper.selectByExample(example) ;

        List<EasyUITreeNode> resultList = new ArrayList<>();
        //把列表转换成treeNodelist
        for (ItemCat itemCat : itemCatList) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(itemCat.getId());
            node.setText(itemCat.getName());
            node.setState(itemCat.getIsParent()?"closed":"open");
            resultList.add(node);
        }
        return resultList;
    }

    @Override
    public Result addCategory(long parentId, String name) {
        //创建一个pojo
        ItemCat category = new ItemCat();
        category.setName(name);
        category.setIsParent(false);
        //'状态。可选值:1(正常),2(删除)',
        category.setStatus(1);
        category.setParentId(parentId);
        category.setSortOrder(1);
        //添加记录
        itemCatMapper.insert(category);
        //查看父节点的isParent列是否为true，如果不是true改成true
        ItemCat parentCat = itemCatMapper.selectByPrimaryKey(parentId);
        //判断是否为true
        if(!parentCat.getIsParent()) {
            parentCat.setIsParent(true);
            //更新父节点
            itemCatMapper.updateByPrimaryKey(parentCat);
        }
        //返回结果
        return Result.ok(category);
    }

    @Override
    public Result updateCategory(long id, String name) {

        ItemCat itemCat = new ItemCat() ;
        itemCat.setId(id);
        itemCat.setName(name);

        itemCatMapper.updateByPrimaryKeySelective(itemCat) ;

        return Result.ok() ;
    }
}
