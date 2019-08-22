package com.moa.model.service;

import com.moa.model.dao.MessengerDAOImpl;
import com.moa.model.vo.MessageVO;
import com.moa.model.vo.SimpleMessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface MessengerListService {
    public List<MessageVO>  messageList(Map<String,Object> messageInfo);
    public int messageListCnt(Map<String,Object> messageInfo);
    public boolean messageDelete(Map<String,Object> messageInfo);
    public boolean messageRead(int messageNum);
    public MessageVO messageDetail(int messageId);
    public boolean messageSend(SimpleMessageVO sentMessage);
}
