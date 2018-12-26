package com.LinSY.backend.service.impl;

import com.LinSY.backend.mapper.SendingMapper;
import com.LinSY.backend.pojo.Item;
import com.LinSY.backend.pojo.ItemExample;
import com.LinSY.backend.pojo.Sending;
import com.LinSY.backend.pojo.SendingExample;
import com.LinSY.backend.service.SendingService;
import com.LinSY.backend.utils.pojo.EasyUIDataGrid;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SendingServiceImpl
 * @Author LinSY
 * @Date 2018/10/9 14:50
 * @Version 1.0
 * @Description
 */

@Service
public class SendingServiceImpl implements SendingService {

    @Autowired
    private SendingMapper sendingMapper ;

    @Override
    public EasyUIDataGrid getSendingList(Integer page, Integer rows) {
        //查询商品列表
        SendingExample example = new SendingExample();
        //分页处理
        PageHelper.startPage(page, rows);
        List<Sending> list = sendingMapper.selectByExample(example);
        //创建一个返回值对象
        EasyUIDataGrid result = new EasyUIDataGrid();
        result.setRows(list);
        //取记录总条数
        PageInfo<Sending> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
