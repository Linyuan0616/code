package com.LinSY.backend.service;

import com.LinSY.backend.pojo.Sending;
import com.LinSY.backend.utils.pojo.Result;

public interface SendingService {

    Result getDepositList(int pageNum, int pageSize, Long userId) ;

    Result getSendingList(int pageNum, int pageSize, Long userId) ;

    Result addSending(Sending sending) ;
}
