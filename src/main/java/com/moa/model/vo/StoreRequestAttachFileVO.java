package com.moa.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class StoreRequestAttachFileVO extends AttachFileVO {

    public StoreRequestAttachFileVO(String typeFlag) {
        super(typeFlag);
    }

    public StoreRequestAttachFileVO(String uuid, Long id, String uploadPath, String fileName, boolean fileType) {
        super(uuid, id, uploadPath, fileName, fileType,null);
    }

    public StoreRequestAttachFileVO(String uuid, Long id, String uploadPath, String fileName, boolean fileType, String typeFlag) {
        super(uuid, id, uploadPath, fileName, fileType, typeFlag);
    }
}
