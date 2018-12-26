package com.LinSY.backend.service;

import com.LinSY.backend.utils.pojo.EasyUIDataGrid;

public interface UserService {

    EasyUIDataGrid getUserList(Integer page, Integer rows) ;
}
