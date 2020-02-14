package com.ischoolbar.programmer.controller.admin;

import java.util.Date;
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

import com.ischoolbar.programmer.entity.admin.Mingxi;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.MingxiService;

@RequestMapping("/admin/mingxi")
@Controller
public class MingxiController {
	@Autowired
	MingxiService mingxiService;
	
	/**
	 * ������֧��ϸ�б����
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("mingxi/list");
		return model;
	}	
		
	/**
	 * ��ȡ������֧��ϸ����
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name="time",required=false) String time,
			@RequestParam(name="leixing",required=false) String leixing,
			@RequestParam(name="people",required=false) String people
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("time", time);
		queryMap.put("leixing", leixing);
		queryMap.put("people",people);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", mingxiService.findList(queryMap));
		ret.put("total",mingxiService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * ��ӻ�����֧��ϸ
	 * @param mingxi
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Mingxi mingxi){
		Map<String, String> ret = new HashMap<String, String>();
		if(mingxi == null){
			ret.put("type", "error");
			ret.put("msg", "����д������֧��ϸ��");
			return ret;
		}
		if((mingxi.getTime())==null){
			ret.put("type", "error");
			ret.put("msg", "����дʱ�䣡");
			return ret;
		}
		if(StringUtils.isEmpty(mingxi.getLeixing())){
			ret.put("type", "error");
			ret.put("msg", "����д�������֧����");
			return ret;
		}
		if((mingxi.getJine())==null){
			ret.put("type", "error");
			ret.put("msg", "����д��");
			return ret;
		}
		if(StringUtils.isEmpty(mingxi.getPeople())){
			ret.put("type", "error");
			ret.put("msg", "����д�����Դ���ô���");
			return ret;
		}
		
		if(mingxiService.add(mingxi) <= 0){
			ret.put("type", "error");
			ret.put("msg", "������֧��ϸ���ʧ�ܣ�");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "������֧��ϸ��ӳɹ���");
		return ret;
	}
}
