package com.moa.model.dao;

import com.moa.model.vo.*;
import com.moa.mybatis.StoreBoardMapper;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
@NoArgsConstructor
@Log4j
public class StoreBoardDAOImpl implements StoreBoardDAO {
    @Autowired
    @Qualifier("sqlSession_oracle")
    private SqlSession sqlSession_oracle;

    /*init*/
    @Override
    public List<SimpleStorageAddressVO> selectStorageAddress(String hostId) {
        StoreBoardMapper mapper = sqlSession_oracle.getMapper(StoreBoardMapper.class);
        List<SimpleStorageAddressVO> storage = mapper.selectStorages(Integer.parseInt(hostId));
        return storage;
    }
    @Override
    public List<String> selectTransaction() {
        StoreBoardMapper mapper = sqlSession_oracle.getMapper(StoreBoardMapper.class);
        List<String> transactionList = mapper.selectTransaction();
        return transactionList;
    }
    @Override
    public List<String> selectCategory() {
        StoreBoardMapper mapper = sqlSession_oracle.getMapper(StoreBoardMapper.class);
        List<String> categoryList = mapper.selectCategory();
        return categoryList;
    }
    @Override
    public List<String> selectStoragePeriod() {
        StoreBoardMapper mapper = sqlSession_oracle.getMapper(StoreBoardMapper.class);
        List<String> periodList = mapper.selectStoragePeriod();
        return periodList;
    }
    @Override
    public List<String> selectPrice() {
        StoreBoardMapper mapper = sqlSession_oracle.getMapper(StoreBoardMapper.class);
        List<String> priceList = mapper.selectPrice();
        return priceList;
    }
    @Override
    public void insert(StoreBoardFormVO storeBoardFormVO) {
        StoreBoardMapper mapper = sqlSession_oracle.getMapper(StoreBoardMapper.class);
        mapper.insertStoreBoard(storeBoardFormVO);
        if(storeBoardFormVO.getPet()!=null)
            mapper.insertPet(storeBoardFormVO);
    }

    @Override
    public int delete(int articleNum) {
        StoreBoardMapper mapper=sqlSession_oracle.getMapper(StoreBoardMapper.class);
        return mapper.deleteOne(articleNum);
    }

    public List<EntrustSearchVO> searchEntrust(DetailOptionVO detail) {
        StoreBoardMapper mapper = sqlSession_oracle.getMapper(StoreBoardMapper.class);
        List<EntrustSearchVO> entrustAry = mapper.searchEntrust(detail);
        return entrustAry;
    }
    @Override
    public StoreBoardVO searchOne(int articleNum) {
        StoreBoardMapper mapper=sqlSession_oracle.getMapper(StoreBoardMapper.class);
        Map<String,Object> map=mapper.selectOne(articleNum);

        StoreBoardVO vo=new StoreBoardVO();

        vo.setArticleNum(articleNum);
        vo.setTitle((String)map.get("제목"));
        vo.setContent((String)map.get("내용"));
        vo.setWriteDate(((java.sql.Timestamp)map.get("작성일자")).toString());
        vo.setTransactionType((String)map.get("거래방식"));
        vo.setStorageType((String)map.get("보관지형태"));
        vo.setStoragePeriodType((String)map.get("보관기간"));
        vo.setBaseAddress((String)map.get("기본주소"));
        vo.setDetailAddress((String)map.get("상세주소"));
        vo.setForbiddenProduct((String)map.get("보관금지물품"));
        vo.setSecurityFacility((String)map.get("보안시설"));
        vo.setPet((String)map.get("펫"));
        List<String> detailPriceNameList = mapper.selectDetailPrice();
        List<String> detailPriceList=Arrays.asList(((String)map.get("상세가격")).split(","));
        Map<String,Integer> detailPriceMap=new HashMap<String,Integer>();
        try {
            for (int i = 0; i < detailPriceNameList.size(); i++)
                detailPriceMap.put(detailPriceNameList.get(i), Integer.parseInt(detailPriceList.get(i)));
        }catch(ArrayIndexOutOfBoundsException e){
            List<Integer> storepriceList = mapper.selectStorePrice(articleNum);
            for (int i = 0; i < detailPriceNameList.size(); i++)
                detailPriceMap.put(detailPriceNameList.get(i), storepriceList.get(i));
        }
        vo.setDetailPrice(detailPriceMap);
        return vo;
    }
    @Override
    public List<SimpleStorageBoardVO> searchMyStorage(Map<String, Object> storageInfo) {
        StoreBoardMapper mapper = sqlSession_oracle.getMapper(StoreBoardMapper.class);

        return mapper.searchMyStorage(storageInfo);
    }

    @Override
    public int searchMyStorageCnt(int hostId) {
        StoreBoardMapper mapper = sqlSession_oracle.getMapper(StoreBoardMapper.class);

        return mapper.searchMyStorageCnt(hostId);
    }

}
