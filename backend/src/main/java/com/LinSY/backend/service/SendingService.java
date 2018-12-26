package com.LinSY.backend.service;

import com.LinSY.backend.utils.pojo.EasyUIDataGrid;

public interface SendingService {

    EasyUIDataGrid getSendingList(Integer page, Integer rows) ;
}
