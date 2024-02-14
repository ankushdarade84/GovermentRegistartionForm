package demo.Application.model;

import java.util.Date;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import demo.Application.model.User;

@Entity
@Table(name = "Education")
public class Education {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long eduId;
	@ManyToOne
	@JoinColumn(name = "UserId")
	private User userId;
	private String course;
	private String streem;
	private String major;
	private String clgname;
	private String uniname;
	private String ecity;
	private String estate;
	private String econtry;
	private String emarks;
	private Date adddate;
	private Date passdate;
	
	
	public Education() {
		super();
		this.eduId = eduId;
		this.userId = userId;
		this.course = course;
		this.streem = streem;
		this.major = major;
		this.clgname = clgname;
		this.uniname = uniname;
		this.ecity = ecity;
		this.estate = estate;
		this.econtry = econtry;
		this.emarks = emarks;
		this.adddate = adddate;
		this.passdate = passdate;
	}


	public Long getEduId() {
		return eduId;
	}


	public void setEduId(Long eduId) {
		this.eduId = eduId;
	}


	public User getUserId() {
		return userId;
	}


	public void setUserId(User userId) {
		this.userId = userId;
	}


	public String getCourse() {
		return course;
	}


	public void setCourse(String course) {
		this.course = course;
	}


	public String getStreem() {
		return streem;
	}


	public void setStreem(String streem) {
		this.streem = streem;
	}


	public String getMajor() {
		return major;
	}


	public void setMajor(String major) {
		this.major = major;
	}


	public String getClgname() {
		return clgname;
	}


	public void setClgname(String clgname) {
		this.clgname = clgname;
	}


	public String getUniname() {
		return uniname;
	}


	public void setUniname(String uniname) {
		this.uniname = uniname;
	}


	public String getEcity() {
		return ecity;
	}


	public void setEcity(String ecity) {
		this.ecity = ecity;
	}


	public String getEstate() {
		return estate;
	}


	public void setEstate(String estate) {
		this.estate = estate;
	}


	public String getEcontry() {
		return econtry;
	}


	public void setEcontry(String econtry) {
		this.econtry = econtry;
	}


	public String getEmarks() {
		return emarks;
	}


	public void setEmarks(String emarks) {
		this.emarks = emarks;
	}


	public Date getAdddate() {
		return adddate;
	}


	public void setAdddate(Date adddate) {
		this.adddate = adddate;
	}


	public Date getPassdate() {
		return passdate;
	}


	public void setPassdate(Date passdate) {
		this.passdate = passdate;
	}
	
	
	
	

}
