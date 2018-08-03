package com.fcsdm.sdmserver.mvc.mapper;

import com.fcsdm.sdmserver.mvc.model.dto.Transaction;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Peter on 2018-02-06.
 */
@Mapper
public interface TransactionMapper {    
    public List<Transaction> selectTransactions(@Param("startDate") String startDate, @Param("endDate") String endDate);
    public int insertTransaction(Transaction transaction);
}
