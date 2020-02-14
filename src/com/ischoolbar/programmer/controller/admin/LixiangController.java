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

import com.ischoolbar.programmer.entity.admin.Lixiang;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.LixiangService;

@RequestMapping("/admin/lixiang")
@Controller
public class LixiangController {
	@Autowired
	LixiangService lixiangService;
	
	/**
	 * ������б����
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("lixiang/list");
		return model;
	}	
		
	/**
	 * ��ȡ��������
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name="name",required=false,defaultValue="") String name,
			@RequestParam(name="people",required=false) String people
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", name);
		queryMap.put("people", people);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", lixiangService.findList(queryMap));
		ret.put("total",lixiangService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * ��������
	 * @param lixiang
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Lixiang lixiang){
		Map<String, String> ret = new HashMap<String, String>();
		if(lixiang == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ����Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(lixiang.getName())){
			ret.put("type", "error");
			ret.put("msg", "����д���������ƣ�");
			return ret;
		}
		if(StringUtils.isEmpty(lixiang.getPeople())){
			ret.put("type", "error");
			ret.put("msg", "����д׫д�ˣ�");
			return ret;
		}
		if(StringUtils.isEmpty(lixiang.getFujian())){
			ret.put("type", "error");
			ret.put("msg", "����Ӹ�����");
			return ret;
		}
		
		if(lixiangService.add(lixiang) <= 0){
			ret.put("type", "error");
			ret.put("msg", "��������ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�������ӳɹ���");
		return ret;
	}
	
	/**
	 * �޸�
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Lixiang lixiang){
		Map<String, String> ret = new HashMap<String, String>();
		if(lixiang == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ����Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(lixiang.getName())){
			ret.put("type", "error");
			ret.put("msg", "����д���������ƣ�");
			return ret;
		}
		if(StringUtils.isEmpty(lixiang.getPeople())){
			ret.put("type", "error");
			ret.put("msg", "����д׫д�ˣ�");
			return ret;
		}
		if(StringUtils.isEmpty(lixiang.getFujian())){
			ret.put("type", "error");
			ret.put("msg", "����д������");
			return ret;
		}
		
		if(lixiangService.edit(lixiang) <= 0){
			ret.put("type", "error");
			ret.put("msg", "������޸�ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "������޸ĳɹ���");
		return ret;
	}
	
	/**
	 * ɾ��
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String name){
		Map<String, String> ret = new HashMap<String, String>();
		if(name==null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ��Ҫɾ������Ŀ����棡");
			return ret;
		}
		if(lixiangService.delete(name) <= 0){
				ret.put("type", "error");
				ret.put("msg", "ɾ��ʧ�ܣ�����ϵ����Ա��");
				return ret;
		}	
		ret.put("type", "success");
		ret.put("msg", "��Ŀ�����ɾ���ɹ���");
		return ret;
	}
		
}
