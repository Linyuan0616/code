package com.LinSY.backend.mapper;

import com.LinSY.backend.pojo.UserDeposit;
import com.LinSY.backend.pojo.UserDepositExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserDepositMapper {
    int insert(UserDeposit record);

    int insertSelective(UserDeposit record);

    List<UserDeposit> selectByExample(UserDepositExample example);

    int updateByExampleSelective(@Param("record") UserDeposit record, @Param("example") UserDepositExample example);

    int updateByExample(@Param("record") UserDeposit record, @Param("example") UserDepositExample example);
}