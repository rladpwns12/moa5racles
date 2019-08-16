package com.moa.model.dao;

import com.moa.model.vo.AttachFileVO;
import com.moa.model.vo.StoreBoardAttachFileVO;
import com.moa.model.vo.StoreRequestAttachFileVO;
import com.moa.model.vo.UserAttachFileVO;
import com.moa.mybatis.AttachMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AttachDAOImpl implements AttachDAO {
    @Autowired
    @Qualifier("sqlSession_oracle")
    private SqlSession sqlSession_oracle;

    @Override
    public boolean insertAttach(AttachFileVO attachFileVO) {
        AttachMapper mapper = sqlSession_oracle.getMapper(AttachMapper.class);

        if(attachFileVO instanceof StoreBoardAttachFileVO)
            return  mapper.insertStoreBoard(attachFileVO)>0?true:false;
        else if(attachFileVO instanceof StoreRequestAttachFileVO)
            return  mapper.insertStoreRequest(attachFileVO)>0?true:false;
        else if(attachFileVO instanceof UserAttachFileVO)
            return  mapper.insertUser(attachFileVO)>0?true:false;
        else
            return false;
    }

    @Override
    public boolean deleteAttachSB(String uuid) {
        AttachMapper mapper = sqlSession_oracle.getMapper(AttachMapper.class);
        return  mapper.deleteStoreBoard(uuid)>0?true:false;
    }
    @Override
    public boolean deleteAttachSR(String uuid) {
        AttachMapper mapper = sqlSession_oracle.getMapper(AttachMapper.class);
        return  mapper.deleteStoreRequest(uuid)>0?true:false;
    }
    @Override
    public boolean deleteAttachUSER(String uuid) {
        AttachMapper mapper = sqlSession_oracle.getMapper(AttachMapper.class);
        return  mapper.deleteUser(uuid)>0?true:false;
    }

    @Override
    public List<AttachFileVO> searchByArticleSB(Long articleNum) {
        AttachMapper mapper = sqlSession_oracle.getMapper(AttachMapper.class);
        return  mapper.findByArticleNumSB(articleNum);
    }
    @Override
    public List<AttachFileVO> searchByArticleSR(Long articleNum) {
        AttachMapper mapper = sqlSession_oracle.getMapper(AttachMapper.class);
        return  mapper.findByArticleNumSR(articleNum);
    }
    @Override
    public AttachFileVO searchByUserId(Long userId) {
        AttachMapper mapper = sqlSession_oracle.getMapper(AttachMapper.class);
        return  mapper.findByUserId(userId);
    }

    @Override
    public List<AttachFileVO> getOldFiles() {
        AttachMapper mapper = sqlSession_oracle.getMapper(AttachMapper.class);
        List<AttachFileVO> attachFileVOList=new ArrayList<AttachFileVO>();
        attachFileVOList.addAll(mapper.getOldFilesUser());
        attachFileVOList.addAll(mapper.getOldFilesSB());
        attachFileVOList.addAll(mapper.getOldFilesSR());
        return attachFileVOList;
    }
}
