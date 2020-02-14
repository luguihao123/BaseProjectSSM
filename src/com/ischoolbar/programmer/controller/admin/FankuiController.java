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

import com.ischoolbar.programmer.entity.admin.Fankui;
import com.ischoolbar.programmer.entity.admin.Fankui;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.FankuiService;

@RequestMapping("/admin/fankui")
@Controller
public class FankuiController {
	@Autowired
	FankuiService fankuiService;
	
	/**
	 * ����������¼�б����
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("fankui/list");
		return model;
	}	
		
	/**
	 * ��ȡ����������¼����
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name="time",required=false) String time,
			@RequestParam(name="name",required=false) String name
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("time", time);
		queryMap.put("name", name);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", fankuiService.findList(queryMap));
		ret.put("total",fankuiService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * ��Ӿ���������¼
	 * @param fankui
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Fankui fankui){
		Map<String, String> ret = new HashMap<String, String>();
		if(fankui == null){
			ret.put("type", "error");
			ret.put("msg", "����д����������¼��");
			return ret;
		}
		if(StringUtils.isEmpty(fankui.getName())){
			ret.put("type", "error");
			ret.put("msg", "����д�����ˣ�");
			return ret;
		}
		if(StringUtils.isEmpty(fankui.getTime())){
			ret.put("type", "error");
			ret.put("msg", "����дʱ�䣡");
			return ret;
		}
		if(StringUtils.isEmpty(fankui.getXinxi())){
			ret.put("type", "error");
			ret.put("msg", "����д�ѷ�������Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(fankui.getFname())){
			ret.put("type", "error");
			ret.put("msg", "����д�����ˣ�");
			return ret;
		}
		
		if(fankuiService.add(fankui) <= 0){
			ret.put("type", "error");
			ret.put("msg", "����������¼���ʧ�ܣ�");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "����������¼��ӳɹ���");
		return ret;
	}
	
	/**
	 * �޸ľ�������
	 */
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Fankui fankui){
		Map<String, String> ret = new HashMap<String, String>();
		if(fankui == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ�ľ���������Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(fankui.getTime())){
			ret.put("type", "error");
			ret.put("msg", "����дʱ�䣡");
			return ret;
		}
		if(StringUtils.isEmpty(fankui.getName())){
			ret.put("type", "error");
			ret.put("msg", "����д�����ˣ�");
			return ret;
		}
		if(fankuiService.edit(fankui) <= 0){
			ret.put("type", "error");
			ret.put("msg", "���������޸�ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "���������޸ĳɹ���");
		return ret;
	}
	
	/**
	 * ɾ����������
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String name){
		Map<String, String> ret = new HashMap<String, String>();
		if(name==null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ��Ҫɾ���ľ���������¼��");
			return ret;
		}
		if(fankuiService.delete(name) <= 0){
				ret.put("type", "error");
				ret.put("msg", "ɾ��ʧ�ܣ�����ϵ����Ա��");
				return ret;
		}	
		ret.put("type", "success");
		ret.put("msg", "��������ɾ���ɹ���");
		return ret;
	}
}
