package com.LinSY.backend.service.impl;

import com.LinSY.backend.mapper.UserMapper;
import com.LinSY.backend.pojo.Item;
import com.LinSY.backend.pojo.ItemExample;
import com.LinSY.backend.pojo.User;
import com.LinSY.backend.pojo.UserExample;
import com.LinSY.backend.service.UserService;
import com.LinSY.backend.utils.pojo.EasyUIDataGrid;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Author LinSY
 * @Date 2018/10/9 14:17
 * @Version 1.0
 * @Description
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper ;

    @Override
    public EasyUIDataGrid getUserList(Integer page, Integer rows) {
        //查询商品列表
        UserExample example = new UserExample();
        example.createCriteria().andStatusEqualTo(2) ;
        //分页处理
        PageHelper.startPage(page, rows);
        List<User> list = userMapper.selectByExample(example);
        //创建一个返回值对象
        EasyUIDataGrid result = new EasyUIDataGrid();
        result.setRows(list);
        //取记录总条数
        PageInfo<User> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
