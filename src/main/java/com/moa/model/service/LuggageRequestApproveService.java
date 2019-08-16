package com.moa.model.service;

import com.moa.model.dao.TransactionDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


interface LuggageRequestApproveService {
	boolean updateTransactionHistory(Map<String, Object> transactionInfo);
}
