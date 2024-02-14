package demo.Application.message;

import demo.Application.model.User;

public class ResponseFile {
	
	private String dname;
	private String durl;
	private String dtype;
	private String didentity;
	private User user;
	private Long dsize;
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDurl() {
		return durl;
	}
	public void setDurl(String durl) {
		this.durl = durl;
	}
	public String getDtype() {
		return dtype;
	}
	public void setDtype(String dtype) {
		this.dtype = dtype;
	}
	public String getDidentity() {
		return didentity;
	}
	public void setDidentity(String didentity) {
		this.didentity = didentity;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Long getDsize() {
		return dsize;
	}
	public void setDsize(Long dsize) {
		this.dsize = dsize;
	}
	public ResponseFile(String dname, String durl, String dtype, String didentity, User user, Long dsize) {
		super();
		this.dname = dname;
		this.durl = durl;
		this.dtype = dtype;
		this.didentity = didentity;
		this.user = user;
		this.dsize = dsize;
	}
	
	
	
	

}
