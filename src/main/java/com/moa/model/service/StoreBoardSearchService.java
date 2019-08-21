package com.moa.model.service;

import com.moa.model.dao.StoreBoardDAO;
import com.moa.model.vo.DetailOptionVO;
import com.moa.model.vo.EntrustSearchVO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StoreBoardSearchService {
	public  List<EntrustSearchVO> search(DetailOptionVO detail);
}
