package com.moa.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class StoreBoardAttachFileVO extends AttachFileVO {
    public StoreBoardAttachFileVO(String typeFlag) {
        super(typeFlag);
    }

    public StoreBoardAttachFileVO(String uuid, Long id, String uploadPath, String fileName, boolean fileType) {
        super(uuid, id, uploadPath, fileName, fileType,null);
    }

    public StoreBoardAttachFileVO(String uuid, Long id, String uploadPath, String fileName, boolean fileType, String typeFlag) {
        super(uuid, id, uploadPath, fileName, fileType, typeFlag);
    }
}
