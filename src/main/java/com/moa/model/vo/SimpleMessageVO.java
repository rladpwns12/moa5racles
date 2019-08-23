package com.moa.model.vo;

import com.moa.valid.MaxByteLength;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.nio.charset.StandardCharsets;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleMessageVO {
    @NotBlank
    private String senderNick;
    @NotBlank
    private String receiverNick;
    @MaxByteLength(maxValue = 3000)
    @NotBlank
    private String content;
}
