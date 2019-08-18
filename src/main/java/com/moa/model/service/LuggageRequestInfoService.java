package com.moa.model.service;

import com.moa.model.dao.AttachDAO;
import com.moa.model.dao.StoreRequestDAO;
import com.moa.model.vo.ReadStoreRequestVO;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Log4j
public class LuggageRequestInfoService {
	@Autowired
	private StoreRequestDAO storeRequestDAO;
	@Autowired
	private AttachDAO attachDAO;

	public ReadStoreRequestVO selectLuggageRequestInfo(int requestId) {
		ReadStoreRequestVO readStoreRequestVO = storeRequestDAO.search(requestId);
		log.info(new Long(requestId));
		readStoreRequestVO.setAttachFileList(attachDAO.searchByArticleSR(new Long(requestId)));
		return readStoreRequestVO;
	}
}
