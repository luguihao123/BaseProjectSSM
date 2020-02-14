package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.Diaoyan;
import com.ischoolbar.programmer.entity.admin.Jihua;

     @Service
	 public interface JihuaService {
    	    public Jihua findByname(String name);
    		
    	    public int add(Jihua jihua);
    		
    	    public int edit(Jihua jihua);
    		
    	    public int delete(String name);
    		
    		public int review(String state);
    		
    		public List<Jihua> findList(Map<String, Object> queryMap);
    		
    		public int getTotal(Map<String, Object> queryMap);
}
