package com.ischoolbar.programmer.entity.admin;

import org.springframework.stereotype.Component;

@Component
public class Chengji {
   
	private String time;
    private String name;
    private String chengji;
    
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
	public String getChengji() {
		return chengji;
	}
	public void setChengji(String chengji) {
		this.chengji = chengji;
	}
}
