package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.ischoolbar.programmer.entity.admin.Niandu;

     @Service
	 public interface NianduService {
    	    public Niandu findByname(String name);
    		public int add(Niandu niandu);
    		public int edit(Niandu niandu);
    		public List<Niandu> findList(Map<String, Object> queryMap);
    		public int getTotal(Map<String, Object> queryMap);
}
