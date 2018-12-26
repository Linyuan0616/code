package com.LinSY.backend.service.impl;

import com.LinSY.backend.mapper.SendingMapper;
import com.LinSY.backend.mapper.UserDepositMapper;
import com.LinSY.backend.pojo.*;
import com.LinSY.backend.service.SendingService;
import com.LinSY.backend.utils.TimeIDUtil;
import com.LinSY.backend.utils.pojo.EasyUIDataGrid;
import com.LinSY.backend.utils.pojo.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SendingSeriveImpl
 * @Author LinSY
 * @Date 2018/10/9 11:17
 * @Version 1.0
 * @Description
 */

@Service
public class SendingSeriveImpl implements SendingService {


    @Autowired
    private UserDepositMapper userDepositMapper ;
    @Autowired
    private SendingMapper sendingMapper ;

    @Override
    public Result getDepositList(int pageNum, int pageSize, Long userId) {

        UserDepositExample example = new UserDepositExample() ;
        example.createCriteria().andUserIdEqualTo(userId) ;
        PageHelper.startPage(pageNum, pageSize);
        List<UserDeposit> list = userDepositMapper.selectByExample(example) ;

        EasyUIDataGrid result = new EasyUIDataGrid();
        result.setRows(list);
        //取记录总条数
        PageInfo<UserDeposit> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());

        return Result.ok(result) ;
    }

    @Override
    public Result getSendingList(int pageNum, int pageSize, Long userId) {

        SendingExample example = new SendingExample() ;
        example.createCriteria().andUserIdEqualTo(userId) ;
        PageHelper.startPage(pageNum, pageSize);
        List<Sending> list = sendingMapper.selectByExample(example) ;

        EasyUIDataGrid result = new EasyUIDataGrid();
        result.setRows(list);
        //取记录总条数
        PageInfo<Sending> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());

        return Result.ok(result) ;
    }

    @Override
    public Result addSending(Sending sending) {

        sending.setSendingId(TimeIDUtil.genItemId());
        sending.setStatus(1);
        try {
            sendingMapper.insertSelective(sending) ;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(500, "sending error!");
        }

        return Result.ok();
    }


}
