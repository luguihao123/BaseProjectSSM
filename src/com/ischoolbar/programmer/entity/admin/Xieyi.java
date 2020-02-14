package com.ischoolbar.programmer.entity.admin;

import org.springframework.stereotype.Component;

@Component
public class Xieyi {

	private String name;
	private String phone;
	private String jine;
	private String fangshi;
	private String xiangmu;
	private String time;
	private String beizhu;
	private String fujian;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getJine() {
		return jine;
	}
	public void setJine(String jine) {
		this.jine = jine;
	}
	public String getFangshi() {
		return fangshi;
	}
	public void setFangshi(String fangshi) {
		this.fangshi = fangshi;
	}
	public String getXiangmu() {
		return xiangmu;
	}
	public void setXiangmu(String xiangmu) {
		this.xiangmu = xiangmu;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public String getFujian() {
		return fujian;
	}
	public void setFujian(String fujian) {
		this.fujian = fujian;
	}
	
	
}
