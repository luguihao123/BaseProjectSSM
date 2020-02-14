package com.ischoolbar.programmer.entity.admin;

import org.springframework.stereotype.Component;

@Component
public class Diaoyan {
	   private String name;
	   private String people;
	   private String beizhu;
	   private String fujian;
	   
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
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
