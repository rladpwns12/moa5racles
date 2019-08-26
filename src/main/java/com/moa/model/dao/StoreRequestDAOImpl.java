package com.moa.model.dao;

import com.moa.model.vo.*;
import com.moa.mybatis.CheckLuggageMapper;
import com.moa.mybatis.StoreRequestMapper;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@NoArgsConstructor
@Log4j
public class StoreRequestDAOImpl implements StoreRequestDAO {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public int insert(StoreRequestVO storeRequestVO) {
        CheckLuggageMapper mapper=sqlSession.getMapper(CheckLuggageMapper.class);

        Map<String,Object> map=new HashMap<String, Object>();
        map.put("storeRequestVO",storeRequestVO);

        List<ProductVO> productsList=storeRequestVO.getProductList();
        StringBuffer categoryList = new StringBuffer();
        StringBuffer productCntList = new StringBuffer();
        StringBuffer productList = new StringBuffer();

       for(ProductVO p : productsList) {
           categoryList.append("," + p.getCategory());
           productList.append("," + p.getProduct());
           productCntList.append("," + p.getProductCnt());
       }

        map.put("categoryList",categoryList.substring(1));
        map.put("productList",productList.substring(1));
        map.put("productCntList",productCntList.substring(1));

        String productSizeList = storeRequestVO.getProductSize().toString();
        productSizeList=productSizeList.substring(1,productSizeList.length()-1);

        map.put("productSizeList",productSizeList);
        map.forEach((k,v)-> System.out.println(k+" / "+v));
        mapper.insert(map);
        return storeRequestVO.getStoreRequestNum();
    }
    public ReadStoreRequestVO search(int requestId){
        StoreRequestMapper mapper = sqlSession.getMapper(StoreRequestMapper.class);
        ReadStoreRequestVO readStoreRequestVO;

        readStoreRequestVO = mapper.searchRequestInfo(requestId);
        List<RequestProductVO> productList = new ArrayList<>();

        productList = mapper.searchRequestProduct(requestId);

        for(int i = 0 ; i < productList.size(); i++){
            readStoreRequestVO.getProductCategory().add(productList.get(i).getProductCategory());
            readStoreRequestVO.getProductCnt().add(productList.get(i).getProductCnt());
            readStoreRequestVO.getProductName().add(productList.get(i).getProductName());
        }

        List<String> pictureList = new ArrayList<>();

        return readStoreRequestVO;
    }
    public List<SimpleUserRequestVO> searchList(int userId, int pageNum){
        StoreRequestMapper mapper = sqlSession.getMapper(StoreRequestMapper.class);
        List<SimpleUserRequestVO> simpleList = new ArrayList<SimpleUserRequestVO>();
        Map<String, Object> map = new HashMap<>();

        map.put("userId", userId);
        map.put("pageNum", pageNum);
        simpleList = mapper.searchRequestList(map);

        List<RequestProductVO> productList = new ArrayList<RequestProductVO>();
        for(int i = 0 ; i < simpleList.size(); i++) {
            productList = mapper.searchRequestProduct(simpleList.get(i).getRequestId());

            for(int j = 0 ; j < productList.size(); j++){
                simpleList.get(i).getProductCategory().add(productList.get(j).getProductCategory());
                simpleList.get(i).getProductCnt().add(productList.get(j).getProductCnt());
                simpleList.get(i).getProductName().add(productList.get(j).getProductName());
            }
        }

        return simpleList;
    }

    @Override
    public int countRequestList(int userId) {
        StoreRequestMapper mapper = sqlSession.getMapper(StoreRequestMapper.class);

        return mapper.searchRequestListCount(userId);
    }
    @Override
    public List<SimpleHostRequestVO> searchListByHost(Map<String,Object> map){
        StoreRequestMapper mapper = sqlSession.getMapper(StoreRequestMapper.class);
        return mapper.searchListByHost(map);
    }
    @Override
    public int searchAllListCnt(Map<String,Object> map){
        StoreRequestMapper mapper = sqlSession.getMapper(StoreRequestMapper.class);

        return mapper.searchAllListCnt(map);
    }

    @Override
    public int deleteConfrimdoneRequest(int articleNum) {
        StoreRequestMapper mapper = sqlSession.getMapper(StoreRequestMapper.class);

        return mapper.updateRequestDelFlag(articleNum);
    }

    @Override
    public boolean checkBoard(Map<String, Object> map) {
        StoreRequestMapper mapper = sqlSession.getMapper(StoreRequestMapper.class);

        return mapper.checkSelfBoard(map)>0 ? true:false;
    }
}
