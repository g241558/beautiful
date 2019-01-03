package com.six.entity;

public class Order {
	private Integer oid;		//订单编号
	private Integer sid;		//商品编号
	private Integer uid;		//用户编号
	private Integer onum;		//订单数量
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getOnum() {
		return onum;
	}
	public void setOnum(Integer onum) {
		this.onum = onum;
	}
	
}
