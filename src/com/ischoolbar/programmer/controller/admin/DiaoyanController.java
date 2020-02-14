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

import com.ischoolbar.programmer.entity.admin.Diaoyan;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.DiaoyanService;

@RequestMapping("/admin/diaoyan")
@Controller
public class DiaoyanController {
	@Autowired
	DiaoyanService diaoyanService;
	
	/**
	 * ������б����б����
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("diaoyan/list");
		return model;
	}	
		
	/**
	 * ��ȡ������б������
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name="name",required=false) String name,
			@RequestParam(name="people",required=false) String people
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", name);
		queryMap.put("people", people);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", diaoyanService.findList(queryMap));
		ret.put("total",diaoyanService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * ���������б���
	 * @param diaoyan
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Diaoyan diaoyan){
		Map<String, String> ret = new HashMap<String, String>();
		if(diaoyan == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ��������б�����Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(diaoyan.getName())){
			ret.put("type", "error");
			ret.put("msg", "����д������б������ƣ�");
			return ret;
		}
		if(StringUtils.isEmpty(diaoyan.getPeople())){
			ret.put("type", "error");
			ret.put("msg", "����д׫д�ˣ�");
			return ret;
		}
		if(StringUtils.isEmpty(diaoyan.getFujian())){
			ret.put("type", "error");
			ret.put("msg", "����д������");
			return ret;
		}
		
		if(diaoyanService.add(diaoyan) <= 0){
			ret.put("type", "error");
			ret.put("msg", "������б������ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "������б�����ӳɹ���");
		return ret;
	}
	
	/**
	 * �޸�������б���
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Diaoyan diaoyan){
		Map<String, String> ret = new HashMap<String, String>();
		if(diaoyan == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ��������б�����Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(diaoyan.getName())){
			ret.put("type", "error");
			ret.put("msg", "����д������б������ƣ�");
			return ret;
		}
		if(StringUtils.isEmpty(diaoyan.getPeople())){
			ret.put("type", "error");
			ret.put("msg", "����д׫д�ˣ�");
			return ret;
		}
		if(StringUtils.isEmpty(diaoyan.getFujian())){
			ret.put("type", "error");
			ret.put("msg", "����д������");
			return ret;
		}
		
		if(diaoyanService.edit(diaoyan) <= 0){
			ret.put("type", "error");
			ret.put("msg", "���񱨸��޸�ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "���񱨸��޸ĳɹ���");
		return ret;
	}
	
	/**
	 * ɾ��������б���
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String name){
		Map<String, String> ret = new HashMap<String, String>();
		if(name==null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ��Ҫɾ����������б��棡");
			return ret;
		}
		if(diaoyanService.delete(name) <= 0){
				ret.put("type", "error");
				ret.put("msg", "ɾ��ʧ�ܣ�����ϵ����Ա��");
				return ret;
		}	
		ret.put("type", "success");
		ret.put("msg", "������б���ɾ���ɹ���");
		return ret;
	}
}
