package com.fcsdm.sdmserver.mvc.service;

import java.util.List;

import com.fcsdm.sdmserver.mvc.mapper.TransactionMapper;
import com.fcsdm.sdmserver.mvc.model.dto.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
	
	@Autowired
	TransactionMapper transactionMapper;

	public List<Transaction> getTransactions(String startDate, String endDate){
		return transactionMapper.selectTransactions(startDate, endDate);
	}
	
	public int addTransaction(Transaction transaction){
		return transactionMapper.insertTransaction(transaction);
	}
}
