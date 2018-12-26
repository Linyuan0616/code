package com.LinSY.backend.controller;

import com.LinSY.backend.service.SendingService;
import com.LinSY.backend.utils.pojo.EasyUIDataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName SendingController
 * @Author LinSY
 * @Date 2018/10/9 14:48
 * @Version 1.0
 * @Description
 */

@Controller
@RequestMapping("/sending")
public class SendingController {

    @Autowired
    private SendingService sendingService ;

    @ResponseBody
    @RequestMapping("/list")
    public EasyUIDataGrid getSendingList(Integer page, Integer rows){

        return sendingService.getSendingList(page, rows);
    }

}
