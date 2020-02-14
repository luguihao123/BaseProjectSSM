package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.ischoolbar.programmer.entity.admin.Mingxi;

     @Service
	 public interface MingxiService {
    	    public Mingxi findBytime(String time);
    		public int add(Mingxi mingxi);
    		public List<Mingxi> findList(Map<String, Object> queryMap);
    		public int getTotal(Map<String, Object> queryMap);
    		
}
