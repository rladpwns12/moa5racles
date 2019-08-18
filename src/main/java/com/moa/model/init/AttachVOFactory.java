package com.moa.model.init;

import com.moa.model.vo.AttachFileVO;
import com.moa.model.vo.StoreBoardAttachFileVO;
import com.moa.model.vo.StoreRequestAttachFileVO;
import com.moa.model.vo.UserAttachFileVO;

public class AttachVOFactory {
    public static final String STOREBOARD="storeBoard";
    public static final String STOREREQUEST="storeRequest";
    public static final String USER="user";

    public static AttachFileVO init(String key){
        switch (key){
            case STOREBOARD:
                return new StoreBoardAttachFileVO(STOREBOARD);
            case STOREREQUEST:
                return new StoreRequestAttachFileVO(STOREREQUEST);
            case USER:
                return new UserAttachFileVO(USER);
            default:
                return null;
        }
    }
}
