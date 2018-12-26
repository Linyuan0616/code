package com.LinSY.backend.controller;

import com.LinSY.backend.service.UserService;
import com.LinSY.backend.utils.pojo.EasyUIDataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName UserController
 * @Author LinSY
 * @Date 2018/10/9 14:16
 * @Version 1.0
 * @Description
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService ;

    @RequestMapping("/list")
    @ResponseBody
    public EasyUIDataGrid getItemList(Integer page, Integer rows) {
        EasyUIDataGrid result = userService.getUserList(page, rows);
        return result;
    }
}
