package com.ischoolbar.programmer.entity.admin;

import org.springframework.stereotype.Component;

@Component
public class Huiyi {
  private String name;
  private String time;
  private String faqiren;
  private String canyuren;
  private String neirong;
  
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public String getFaqiren() {
	return faqiren;
}
public void setFaqiren(String faqiren) {
	this.faqiren = faqiren;
}

public String getCanyuren() {
	return canyuren;
}
public void setCanyuren(String canyuren) {
	this.canyuren = canyuren;
}
public String getNeirong() {
	return neirong;
}
public void setNeirong(String neirong) {
	this.neirong = neirong;
}
  
}
