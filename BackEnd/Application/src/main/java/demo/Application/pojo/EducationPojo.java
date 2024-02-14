package demo.Application.pojo;

import java.util.Date;
import demo.Application.model.User;

import lombok.Data;

@Data
public class EducationPojo {
	
	private Long eduId;
	private Long userId;
	private String course;
	private String streem;
	private String Major;
	private String clgname;
	private String uniname;
	private String ecity;
	private String estate;
	private String econtry;
	private String emarks;
	private Date adddate;
	private Date passdate;

}
