package com.LinSY.backend.mapper;

import com.LinSY.backend.pojo.Sending;
import com.LinSY.backend.pojo.SendingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SendingMapper {
    int deleteByPrimaryKey(Long sendingId);

    int insert(Sending record);

    int insertSelective(Sending record);

    List<Sending> selectByExample(SendingExample example);

    Sending selectByPrimaryKey(Long sendingId);

    int updateByExampleSelective(@Param("record") Sending record, @Param("example") SendingExample example);

    int updateByExample(@Param("record") Sending record, @Param("example") SendingExample example);

    int updateByPrimaryKeySelective(Sending record);

    int updateByPrimaryKey(Sending record);
}