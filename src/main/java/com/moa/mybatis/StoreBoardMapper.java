
  // IntelliJ API Decompiler stub source generated from a class file
  // Implementation of methods is not available

package com.moa.mybatis;

  import com.moa.model.vo.StoreBoardFormVO;

  public interface StoreBoardMapper {
    int deleteOne(int articleNum);
    void insertStoreBoard(StoreBoardFormVO storeBoardFormVO);
    java.util.Map<String, Object> selectOne(int i);
    java.util.List<String> selectDetailPrice();
    java.util.List<com.moa.model.vo.EntrustSearchVO> searchEntrust(com.moa.model.vo.DetailOptionVO detailOptionVO);
    java.util.List<com.moa.model.vo.SimpleStorageBoardVO> searchMyStorage(java.util.Map<String, Object> map);
    int searchMyStorageCnt(int i);
    java.util.List<com.moa.model.vo.SimpleStorageAddressVO> selectStorages(int i);
    java.util.List<String> selectTransaction();
    java.util.List<String> selectCategory();
    java.util.List<String> selectStoragePeriod();
    java.util.List<String> selectPrice();
    void insertPet(StoreBoardFormVO storeBoardFormVO);
  }
