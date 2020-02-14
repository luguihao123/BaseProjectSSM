package com.ischoolbar.programmer.entity.admin;

import org.springframework.stereotype.Component;

@Component
public class Jilu {
    private String name;
    private String xiepeople;
    private String shijian;
    private String zuopeople;
    private String cailiao;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getXiepeople() {
		return xiepeople;
	}
	public void setXiepeople(String xiepeople) {
		this.xiepeople = xiepeople;
	}
	public String getShijian() {
		return shijian;
	}
	public void setShijian(String shijian) {
		this.shijian = shijian;
	}
	public String getZuopeople() {
		return zuopeople;
	}
	public void setZuopeople(String zuopeople) {
		this.zuopeople = zuopeople;
	}
	public String getCailiao() {
		return cailiao;
	}
	public void setCailiao(String cailiao) {
		this.cailiao = cailiao;
	}
    
}
