package com.moa.model.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ReplyVO {
    @NotBlank
    @Size(min = 1, max = 1000)
    private String comment;
    @NotBlank
    @Size(min = 1, max = 5)
    private int rating;
    @NotBlank
    private int articleNum;
    @NotBlank
    private int parentNum;
    @NotBlank
    private int userId;

    @Override
    public String toString() {
        return "ReplyVO{" +
                "comment='" + comment + '\'' +
                ", rating=" + rating +
                ", articleNum=" + articleNum +
                ", parentNum=" + parentNum +
                ", userId=" + userId +
                '}';
    }
}
