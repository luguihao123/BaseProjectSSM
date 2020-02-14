package com.ischoolbar.programmer.entity.admin;

import org.springframework.stereotype.Component;

@Component
public class Fankui {
   private String time;
   private String name;
   private String xinxi;
   private String fname;
   
   
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
public String getXinxi() {
	return xinxi;
}
public void setXinxi(String xinxi) {
	this.xinxi = xinxi;
}
public String getFname() {
	return fname;
}
public void setFname(String fname) {
	this.fname = fname;
}

}
