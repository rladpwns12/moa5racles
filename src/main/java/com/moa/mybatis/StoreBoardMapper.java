
  // IntelliJ API Decompiler stub source generated from a class file
  // Implementation of methods is not available

package com.moa.mybatis;

public interface StoreBoardMapper {
    java.util.List<com.moa.model.vo.SimpleStorageAddressVO> selectStorages(int i);

    java.util.List<String> selectTransaction();

    java.util.List<String> selectCategory();

    java.util.List<String> selectStoragePeriod();

    java.util.List<String> selectPrice();

    java.util.Map<String, Object> selectParameters(java.util.Map<String, Object> map);

    int insertIntoStoreBoard(java.util.Map<String, Object> map);

    int insertIntoPetType(java.util.Map<String, Object> map);

    int insertIntoSF_SB(java.util.Map<String, Object> map);

    int insertIntoSBAttachedPicture(java.util.Map<String, Object> map);

    int insertIntoDetailPriceFibo(java.util.Map<String, Object> map);

    int selectCategoryId(String s);

    int insertIntoForbiddenProduct(java.util.Map<String, Object> map);
    int deleteOne(int articleNum);

    java.util.Map<String, Object> selectOne(int i);

    java.util.List<String> selectDetailPrice();

    java.util.List<com.moa.model.vo.EntrustSearchVO> searchEntrust(com.moa.model.vo.DetailOptionVO detailOptionVO);

    java.util.List<com.moa.model.vo.SimpleStorageBoardVO> searchMyStorage(java.util.Map<String, Object> map);

    int searchMyStorageCnt(int i);


}
