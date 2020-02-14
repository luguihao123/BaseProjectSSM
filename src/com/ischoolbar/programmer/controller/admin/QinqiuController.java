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

import com.ischoolbar.programmer.entity.admin.Qinqiu;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.QinqiuService;

@RequestMapping("/admin/qinqiu")
@Controller
public class QinqiuController {
	@Autowired
	QinqiuService qinqiuService;
	
	/**
	 * ���񱨸��б����
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("qinqiu/list");
		return model;
	}	
		
	/**
	 * ��ȡ���񱨸����
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
		ret.put("rows", qinqiuService.findList(queryMap));
		ret.put("total",qinqiuService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * ��Ӳ��񱨸�
	 * @param qinqiu
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Qinqiu qinqiu){
		Map<String, String> ret = new HashMap<String, String>();
		if(qinqiu == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ�Ĳ�����Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(qinqiu.getName())){
			ret.put("type", "error");
			ret.put("msg", "����д���󷽣�");
			return ret;
		}
		if(StringUtils.isEmpty(qinqiu.getQinqiu())){
			ret.put("type", "error");
			ret.put("msg", "����д�������ݣ�");
			return ret;
		}
		if(qinqiuService.add(qinqiu) <= 0){
			ret.put("type", "error");
			ret.put("msg", "�绰�������ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�绰������ӳɹ���");
		return ret;
	}

	/**
	 * ɾ���绰����
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String name){
		Map<String, String> ret = new HashMap<String, String>();
		if(name == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ��Ҫɾ���ĵ绰����");
			return ret;
		}
		
		if(qinqiuService.delete(name) <= 0){
				ret.put("type", "error");
				ret.put("msg", "�绰����ɾ��ʧ�ܣ�����ϵ����Ա��");
				return ret;
		}	
		ret.put("type", "success");
		ret.put("msg", "�绰����ɾ���ɹ���");
		return ret;
	}		
}
