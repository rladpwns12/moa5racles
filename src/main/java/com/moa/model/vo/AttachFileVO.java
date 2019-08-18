package com.moa.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class AttachFileVO {
    private String uuid;
    private Long id;
    private String uploadPath;
    private String fileName;
    private boolean fileType;
    private String typeFlag;

    public AttachFileVO(){
    }

    public AttachFileVO(String typeFlag) {
        this.typeFlag=typeFlag;
    }

    @Override
    public String toString() {
        return "AttachFileVO{" +
                "uuid='" + uuid + '\'' +
                ", id=" + id +
                ", uploadPath='" + uploadPath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileType=" + fileType +
                ", typeFlag='" + typeFlag + '\'' +
                '}';
    }
}
