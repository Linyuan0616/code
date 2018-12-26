package com.LinSY.backend.controller;

import com.LinSY.backend.pojo.Sending;
import com.LinSY.backend.service.SendingService;
import com.LinSY.backend.utils.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName SendingController
 * @Author LinSY
 * @Date 2018/10/9 11:15
 * @Version 1.0
 * @Description
 */

@Controller
@RequestMapping("/sending")
public class SendingController {

    @Autowired
    private SendingService sendingService ;

    @ResponseBody
    @RequestMapping("/deposit")
    public Result getDepositList(int pageNumber, int pageSize, Long userId){

        return sendingService.getDepositList(pageNumber, pageSize, userId) ;
    }

    @ResponseBody
    @RequestMapping("/list")
    public Result getSendingList(int pageNumber, int pageSize, Long userId){

        return sendingService.getSendingList(pageNumber, pageSize, userId) ;
    }

    @ResponseBody
    @RequestMapping("/add")
    public Result addSending(Sending sending){
        return sendingService.addSending(sending) ;
    }
}
