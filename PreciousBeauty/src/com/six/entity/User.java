package com.six.entity;

public class User {
	private Integer uid;		//�û����
	private String uname;		//�û�����
	private String upassword;	//�û�����
	private String usex;		//�û��Ա�
	private String uaddress;	//�û���ס��ַ
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getUsex() {
		return usex;
	}
	public void setUsex(String usex) {
		this.usex = usex;
	}
	public String getUaddress() {
		return uaddress;
	}
	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}
	public User(Integer uid, String uname, String upassword, String usex,
			String uaddress) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.upassword = upassword;
		this.usex = usex;
		this.uaddress = uaddress;
	}
	public User() {
		super();
	}
	
}
