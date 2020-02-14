package com.ischoolbar.programmer.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.admin.Jihua;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.JihuaService;

@RequestMapping("/admin/jihua")
@Controller
public class JihuaController {
	@Autowired
	JihuaService jihuaService;
	
	/**
	 * �����ƻ��б����
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("jihua/list");
		return model;
	}	
		
	/**
	 * ��ȡ�����ƻ�����
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
		ret.put("rows", jihuaService.findList(queryMap));
		ret.put("total",jihuaService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * ��ӹ����ƻ�
	 * @param jihua
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Jihua jihua){
		Map<String, String> ret = new HashMap<String, String>();
		if(jihua == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ����Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(jihua.getName())){
			ret.put("type", "error");
			ret.put("msg", "����д�����ƻ������ƣ�");
			return ret;
		}
		if(StringUtils.isEmpty(jihua.getPeople())){
			ret.put("type", "error");
			ret.put("msg", "����д׫д�ˣ�");
			return ret;
		}
		if(StringUtils.isEmpty(jihua.getFujian())){
			ret.put("type", "error");
			ret.put("msg", "����Ӹ�����");
			return ret;
		}
		
		if(jihuaService.add(jihua) <= 0){
			ret.put("type", "error");
			ret.put("msg", "�����ƻ����ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�����ƻ���ӳɹ���");
		return ret;
	}
	
	/**
	 * �޸Ĺ����ƻ�
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Jihua jihua){
		Map<String, String> ret = new HashMap<String, String>();
		if(jihua == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ����Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(jihua.getName())){
			ret.put("type", "error");
			ret.put("msg", "����д�����ƻ������ƣ�");
			return ret;
		}
		if(StringUtils.isEmpty(jihua.getPeople())){
			ret.put("type", "error");
			ret.put("msg", "����д׫д�ˣ�");
			return ret;
		}
		if(StringUtils.isEmpty(jihua.getFujian())){
			ret.put("type", "error");
			ret.put("msg", "����д������");
			return ret;
		}
		
		if(jihuaService.edit(jihua) <= 0){
			ret.put("type", "error");
			ret.put("msg", "�����ƻ��޸�ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�����ƻ��޸ĳɹ���");
		return ret;
	}
	
	/**
	 * ɾ�������ƻ�
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String name){
		Map<String, String> ret = new HashMap<String, String>();
		if(name==null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ��Ҫɾ���Ĺ����ƻ���");
			return ret;
		}
		if(jihuaService.delete(name) <= 0){
				ret.put("type", "error");
				ret.put("msg", "ɾ��ʧ�ܣ�����ϵ����Ա��");
				return ret;
		}	
		ret.put("type", "success");
		ret.put("msg", "�ù����ƻ�ɾ���ɹ���");
		return ret;
	}
	
	/**
	 * ��˹����ƻ�
	 */
	
	@RequestMapping(value="/review",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> review(String state){
		Map<String, String> ret = new HashMap<String, String>();
	    jihuaService.review(state);
		ret.put("msg", "״̬�޸ĳɹ���");
		return ret;
		/**if(jihuaService.review(state) <= 0){
			ret.put("type", "error");
			ret.put("msg", "����˲���ʧ�ܣ�����ϵ����Ա");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "״̬�޸ĳɹ���");
		return ret;*/
	   }
}
   
