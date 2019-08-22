package com.moa.model.dao;

import com.moa.model.vo.MessageVO;
import com.moa.model.vo.SimpleMessageVO;
import com.moa.mybatis.MessengerMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface MessengerDAO {
    public List<MessageVO> searchMessage(Map<String,Object> messageInfo);
    public boolean updateReadState(int messageNum);
    public boolean deleteMessage(Map<String,Object> messageInfo);
    public int searchAllMessageCnt(Map<String,Object> messageinfo);
    public MessageVO searchOneMessage(int messageId);
    public boolean insertOneMessage(SimpleMessageVO insertMessageInfo);

}
