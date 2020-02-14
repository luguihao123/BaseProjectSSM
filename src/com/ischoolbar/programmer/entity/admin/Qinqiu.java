package com.ischoolbar.programmer.entity.admin;

import org.springframework.stereotype.Component;

@Component
public class Qinqiu {
   private String name;
   private String qinqiu;
   private long phone;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getQinqiu() {
	return qinqiu;
}
public void setQinqiu(String qinqiu) {
	this.qinqiu = qinqiu;
}
public long getPhone() {
	return phone;
}
public void setPhone(long phone) {
	this.phone = phone;
}
   
   
}
