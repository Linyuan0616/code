package com.LinSY.backend.service.impl;

import com.LinSY.backend.mapper.ItemDescMapper;
import com.LinSY.backend.mapper.ItemMapper;
import com.LinSY.backend.pojo.*;
import com.LinSY.backend.service.ItemService;
import com.LinSY.backend.utils.TimeIDUtil;
import com.LinSY.backend.utils.QiniuUploadUtil;
import com.LinSY.backend.utils.pojo.EasyUIDataGrid;
import com.LinSY.backend.utils.pojo.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper ;
    @Autowired
    private ItemDescMapper itemDescMapper ;


    @Override
    public Map fileUpload(MultipartFile file) {

            Map resultMap = new HashMap() ;
            try {
            // 调用七牛云上传工具
            String url = QiniuUploadUtil.uploadFile(file) ;
            resultMap.put("error", 0);
            resultMap.put("url",url) ;
        } catch (Exception e) {
            resultMap.put("error", 1);
            resultMap.put("message", "文件上传失败");
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Result addItem(Item item, String desc) throws Exception {
        //item补全
        //生成商品ID
        Long itemId = TimeIDUtil.genItemId();
        item.setId(itemId);
        // '商品状态，1-正常，2-下架，3-删除',
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        //插入到数据库
        itemMapper.insert(item);
        //添加商品描述信息
        Result result = insertItemDesc(itemId, desc);
        if (result.getStatus() != 200) {
            throw new Exception();
        }
        return Result.ok();
    }

    @Override
    public EasyUIDataGrid getItemList(Integer page, Integer rows) {
        //查询商品列表
        ItemExample example = new ItemExample();
        example.createCriteria().andStatusNotEqualTo((byte) 3) ;
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
    public Result getItemDesc(long itemId) {
        ItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId) ;
        return Result.build(200,null,itemDesc) ;
    }

    @Override
    public Result deleteItems(Long[] ids) {
        Item item = new Item() ;
        try {
            item.setStatus((byte) (3 & 0xff));
            for (Long id : ids) {
                item.setId(id);
                itemMapper.updateByPrimaryKeySelective(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(500,"error") ;
        }
        return Result.ok() ;
    }

    @Override
    public Result unStockItems(Long[] ids) {
        Item item = new Item() ;
        try {
            item.setStatus((byte) 2);
            for (Long id : ids) {
                item.setId(id);
                itemMapper.updateByPrimaryKeySelective(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(500,"error") ;
        }
        return Result.ok() ;
    }

    @Override
    public Result inStockItems(Long[] ids) {
        Item item = new Item() ;
        try {
            item.setStatus((byte) 1);
            for (Long id : ids) {
                item.setId(id);
                itemMapper.updateByPrimaryKeySelective(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(500,"error") ;
        }
        return Result.ok() ;
    }

    private Result insertItemDesc(Long itemId, String desc) {
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDescMapper.insert(itemDesc);
        return Result.ok();
    }
}
