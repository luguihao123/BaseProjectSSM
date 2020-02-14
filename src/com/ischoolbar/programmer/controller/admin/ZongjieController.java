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

import com.ischoolbar.programmer.entity.admin.Zongjie;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.ZongjieService;

@RequestMapping("/admin/zongjie")
@Controller
public class ZongjieController {
	@Autowired
	ZongjieService zongjieService;
	
	/**
	 * ��Ŀ�ܽᱨ���б����
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("zongjie/list");
		return model;
	}	
		
	/**
	 * ��ȡ��Ŀ�ܽᱨ�����
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name="name",required=false) String name,
			@RequestParam(name="people",required=false) String people,
			@RequestParam(name="beizhu",required=false) String beizhu,
			@RequestParam(name="fujian",required=false) String fujian
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", name);
		queryMap.put("people", people);
		queryMap.put("beizhu", beizhu);
		queryMap.put("fujian", fujian);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", zongjieService.findList(queryMap));
		ret.put("total",zongjieService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * ��Ӳ��񱨸�
	 * @param zongjie
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Zongjie zongjie){
		Map<String, String> ret = new HashMap<String, String>();
		if(zongjie == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ����Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(zongjie.getName())){
			ret.put("type", "error");
			ret.put("msg", "����д��Ŀ�ܽᱨ�����ƣ�");
			return ret;
		}
		if(StringUtils.isEmpty(zongjie.getPeople())){
			ret.put("type", "error");
			ret.put("msg", "����д׫д�ˣ�");
			return ret;
		}
		if(StringUtils.isEmpty(zongjie.getFujian())){
			ret.put("type", "error");
			ret.put("msg", "����д������");
			return ret;
		}
		
		if(zongjieService.add(zongjie) <= 0){
			ret.put("type", "error");
			ret.put("msg", "��Ŀ��������ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "��Ŀ�������ӳɹ���");
		return ret;
	}
	
	/**
	 * �޸���Ŀ�����
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Zongjie zongjie){
		Map<String, String> ret = new HashMap<String, String>();
		if(zongjie == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ����Ŀ�������Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(zongjie.getPeople())){
			ret.put("type", "error");
			ret.put("msg", "����д׫д�ˣ�");
			return ret;
		}
		if(StringUtils.isEmpty(zongjie.getFujian())){
			ret.put("type", "error");
			ret.put("msg", "����д������");
			return ret;
		}
		
		if(zongjieService.edit(zongjie) <= 0){
			ret.put("type", "error");
			ret.put("msg", "��Ŀ������޸�ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "��Ŀ������޸ĳɹ���");
		return ret;
	}
	
	/**
	 * ɾ����Ŀ�����
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String name){
		Map<String, String> ret = new HashMap<String, String>();
		if(name == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ��Ҫɾ������Ŀ����棡");
			return ret;
		}
		
		if(zongjieService.delete(name) <= 0){
				ret.put("type", "error");
				ret.put("msg", "��Ŀ����ɾ��ʧ�ܣ�����ϵ����Ա��");
				return ret;
		}	
		ret.put("type", "success");
		ret.put("msg", "��Ŀ����ɾ���ɹ���");
		return ret;
	}
	
	
		
}
