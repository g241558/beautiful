package com.six.entity;

import java.io.Serializable;

public class Type implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer tid;	//商品类型编号
	private String tname;	//商品类型
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public Type(Integer tid, String tname) {
		super();
		this.tid = tid;
		this.tname = tname;
	}
	public Type() {
		super();
	}
	
}
