package com.moa.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleHostRequestVO {
	private int articleNum;
	private int userId;
	private String nick;
	private UserAttachFileVO profile;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	private String products;
	private int price;
	private String state;

	
	
}
