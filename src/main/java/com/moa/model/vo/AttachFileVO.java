package com.moa.model.vo;

import com.moa.valid.MaxByteLength;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public abstract class AttachFileVO {
    @MaxByteLength(maxValue = 200)
    @NotBlank
    private String uuid;
    private Long id;
    @MaxByteLength(maxValue = 200)
    @NotBlank
    private String uploadPath;
    @MaxByteLength(maxValue = 100)
    @NotBlank
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
