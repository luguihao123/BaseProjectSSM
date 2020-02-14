package com.ischoolbar.programmer.entity.admin;

import org.springframework.stereotype.Component;

@Component
public class Yusuan {
	
   private String yongtu;
   private String jine;
   private String beizhu;
   private String fujian;
   
public String getYongtu() {
	return yongtu;
}
public void setYongtu(String yongtu) {
	this.yongtu = yongtu;
}
public String getJine() {
	return jine;
}
public void setJine(String jine) {
	this.jine = jine;
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
