package com.ischoolbar.programmer.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.admin.Chengji;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.ChengjiService;

@RequestMapping("/admin/chengji")
@Controller
public class ChengjiController {
	@Autowired
	ChengjiService chengjiService;
	
	/**
	 * ���񱨸��б����
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		Map<String, Object> queryMap = new HashMap<String, Object>();
		model.addObject("chengjiList", chengjiService.findList(queryMap));
		model.setViewName("chengji/list");
		return model;
	}	
		
	/**
	 * ��ȡ���񱨸����
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name="name",required=false,defaultValue="") String name,
			@RequestParam(name="people",required=false) String people,
			@RequestParam(name="fujian",required=false) String fujian
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", name);
		queryMap.put("people", people);
		queryMap.put("fujian", fujian);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", chengjiService.findList(queryMap));
		ret.put("total",chengjiService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * ��Ӳ��񱨸�
	 * @param chengji
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Chengji chengji){
		Map<String, String> ret = new HashMap<String, String>();
		if(chengji == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ�ĳɼ���Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(chengji.getName())){
			ret.put("type", "error");
			ret.put("msg", "����дԱ�����ƣ�");
			return ret;
		}
		if(StringUtils.isEmpty(chengji.getTime())){
			ret.put("type", "error");
			ret.put("msg", "����дʱ��Σ�");
			return ret;
		}
		if(StringUtils.isEmpty(chengji.getChengji())){
			ret.put("type", "error");
			ret.put("msg", "����дԱ�����˳ɼ���");
			return ret;
		}
		
		if(chengjiService.add(chengji) <= 0){
			ret.put("type", "error");
			ret.put("msg", "Ա�����˳ɼ����ʧ�ܣ�");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "Ա�����˳ɼ���ӳɹ���");
		return ret;
	}
}
