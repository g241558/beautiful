package com.six.entity;

import java.io.Serializable;

public class Shops implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer shid;		//��Ʒ���
	private Integer tid;		//��Ʒ���ͱ��
	private String shname;		//��Ʒ����
	private Integer shnum;		//��Ʒ����
	private Integer shprice;	//��Ʒ�۸�
	private String shaddress;	//��Ʒ������ַ
	public Integer getShid() {
		return shid;
	}
	public void setShid(Integer shid) {
		this.shid = shid;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getShname() {
		return shname;
	}
	public void setShname(String shname) {
		this.shname = shname;
	}
	public Integer getShnum() {
		return shnum;
	}
	public void setShnum(Integer shnum) {
		this.shnum = shnum;
	}
	public Integer getShprice() {
		return shprice;
	}
	public void setShprice(Integer shprice) {
		this.shprice = shprice;
	}
	public String getShaddress() {
		return shaddress;
	}
	public void setShaddress(String shaddress) {
		this.shaddress = shaddress;
	}
	public Shops(Integer shid, Integer tid, String shname, Integer shnum,
			Integer shprice, String shaddress) {
		super();
		this.shid = shid;
		this.tid = tid;
		this.shname = shname;
		this.shnum = shnum;
		this.shprice = shprice;
		this.shaddress = shaddress;
	}
	public Shops() {
		super();
	}
	
}
