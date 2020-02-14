package com.ischoolbar.programmer.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.admin.Caiwu;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.CaiwuService;

@RequestMapping("/admin/caiwu")
@Controller
public class CaiwuController {
	@Autowired
	CaiwuService caiwuService;
	
	/**
	 * ���񱨸��б����
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("caiwu/list");
		return model;
	}	
		
	/**
	 * ��ȡ���񱨸����
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
		ret.put("rows", caiwuService.findList(queryMap));
		ret.put("total",caiwuService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * ��Ӳ��񱨸�
	 * @param caiwu
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Caiwu caiwu){
		Map<String, String> ret = new HashMap<String, String>();
		if(caiwu == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ�Ĳ�����Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(caiwu.getName())){
			ret.put("type", "error");
			ret.put("msg", "����д���񱨸����ƣ�");
			return ret;
		}
		if(StringUtils.isEmpty(caiwu.getPeople())){
			ret.put("type", "error");
			ret.put("msg", "����д׫д�ˣ�");
			return ret;
		}
		if(StringUtils.isEmpty(caiwu.getFujian())){
			ret.put("type", "error");
			ret.put("msg", "����д������");
			return ret;
		}
		
		if(caiwuService.add(caiwu) <= 0){
			ret.put("type", "error");
			ret.put("msg", "���񱨸����ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "���񱨸���ӳɹ���");
		return ret;
	}
	
	/**
	 * �޸Ĳ��񱨸�
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Caiwu caiwu){
		Map<String, String> ret = new HashMap<String, String>();
		if(caiwu == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ�Ĳ��񱨸���Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(caiwu.getPeople())){
			ret.put("type", "error");
			ret.put("msg", "����д׫д�ˣ�");
			return ret;
		}
		if(StringUtils.isEmpty(caiwu.getFujian())){
			ret.put("type", "error");
			ret.put("msg", "����д������");
			return ret;
		}
		
		if(caiwuService.edit(caiwu) <= 0){
			ret.put("type", "error");
			ret.put("msg", "���񱨸��޸�ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "���񱨸��޸ĳɹ���");
		return ret;
	}
	
	/**
	 * ɾ�����񱨸�
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String name){
		Map<String, String> ret = new HashMap<String, String>();
		if(name==null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ��Ҫɾ���Ĳ��񱨸棡");
			return ret;
		}
		if(caiwuService.delete(name) <= 0){
				ret.put("type", "error");
				ret.put("msg", "ɾ��ʧ�ܣ�����ϵ����Ա��");
				return ret;
		}	
		ret.put("type", "success");
		ret.put("msg", "���񱨸�ɾ���ɹ���");
		return ret;
	}
	
	/**
	 * �ϴ�����
	 */
	@RequestMapping(value="/upload_fujian",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> uploadFujian(MultipartFile fujian,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(fujian == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ���ϴ����ļ���");
			return ret;
		}
		if(fujian.getSize() > 1024*1024*1024) {
			ret.put("type", "error");
			ret.put("msg", "�ļ���С���ܳ���10M");
			return ret;
		}
		//��ȡ�ļ���׺
		 String suffix=fujian.getOriginalFilename().substring(fujian.getOriginalFilename().lastIndexOf(".")+1,fujian.getOriginalFilename().length());
		if(!"txt,doc,docx,wps,pdf".toUpperCase().contains(suffix.toUpperCase())){
				ret.put("type", "error");
				ret.put("msg", "��ѡ��txt,doc,docx,wps,pdf��ʽ���ĵ���");
				return ret;
		}
		//�õ����Ե�ַ
		String savePath=request.getServletContext().getRealPath("/")+"/resources/upload/";
		File savePathFile=new File(savePath);
		if(!savePathFile.exists()) {
			//�������ڸ�Ŀ¼���򴴽�Ŀ¼
			savePathFile.mkdir();
		}
		String filename = new Date().getTime()+"."+suffix;
		try {
			//���ļ�������ָ��Ŀ¼
			fujian.transferTo(new File(savePath+filename));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ret.put("type", "error");
			ret.put("msg", "�����ļ��쳣");
			e.printStackTrace();
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�ĵ��ϴ��ɹ���");
		ret.put("filepath",request.getServletContext().getContextPath() + "/resources/upload/" + filename);
		return ret;
	}
		
}
