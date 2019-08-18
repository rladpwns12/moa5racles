package com.moa.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminHostSimpleVO {
    private int userId;
    private String nick;
    private String storageType;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date applicationDate;
    @JsonFormat(pattern="tt:mm:ss")
    private Time applicationTime;
}
