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

    public AttachFileVO() {
    }
    @Override
    public String toString() {
        return "AttachFileVO{" +
                "uuid='" + getUuid() + '\'' +
                ", id=" + getId() +
                ", uploadPath='" + getUploadPath() + '\'' +
                ", fileName='" + getFileName() + '\'' +
                ", fileType=" + isFileType() +
                '}';
    }
}
