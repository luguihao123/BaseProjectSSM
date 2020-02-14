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
	 * 财务报告列表界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("caiwu/list");
		return model;
	}	
		
	/**
	 * 获取财务报告界面
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
	 * 添加财务报告
	 * @param caiwu
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Caiwu caiwu){
		Map<String, String> ret = new HashMap<String, String>();
		if(caiwu == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的财务信息！");
			return ret;
		}
		if(StringUtils.isEmpty(caiwu.getName())){
			ret.put("type", "error");
			ret.put("msg", "请填写财务报告名称！");
			return ret;
		}
		if(StringUtils.isEmpty(caiwu.getPeople())){
			ret.put("type", "error");
			ret.put("msg", "请填写撰写人！");
			return ret;
		}
		if(StringUtils.isEmpty(caiwu.getFujian())){
			ret.put("type", "error");
			ret.put("msg", "请填写附件！");
			return ret;
		}
		
		if(caiwuService.add(caiwu) <= 0){
			ret.put("type", "error");
			ret.put("msg", "财务报告添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "财务报告添加成功！");
		return ret;
	}
	
	/**
	 * 修改财务报告
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Caiwu caiwu){
		Map<String, String> ret = new HashMap<String, String>();
		if(caiwu == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的财务报告信息！");
			return ret;
		}
		if(StringUtils.isEmpty(caiwu.getPeople())){
			ret.put("type", "error");
			ret.put("msg", "请填写撰写人！");
			return ret;
		}
		if(StringUtils.isEmpty(caiwu.getFujian())){
			ret.put("type", "error");
			ret.put("msg", "请填写附件！");
			return ret;
		}
		
		if(caiwuService.edit(caiwu) <= 0){
			ret.put("type", "error");
			ret.put("msg", "财务报告修改失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "财务报告修改成功！");
		return ret;
	}
	
	/**
	 * 删除财务报告
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String name){
		Map<String, String> ret = new HashMap<String, String>();
		if(name==null){
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的财务报告！");
			return ret;
		}
		if(caiwuService.delete(name) <= 0){
				ret.put("type", "error");
				ret.put("msg", "删除失败，请联系管理员！");
				return ret;
		}	
		ret.put("type", "success");
		ret.put("msg", "财务报告删除成功！");
		return ret;
	}
	
	/**
	 * 上传附件
	 */
	@RequestMapping(value="/upload_fujian",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> uploadFujian(MultipartFile fujian,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(fujian == null){
			ret.put("type", "error");
			ret.put("msg", "请选择上传的文件！");
			return ret;
		}
		if(fujian.getSize() > 1024*1024*1024) {
			ret.put("type", "error");
			ret.put("msg", "文件大小不能超过10M");
			return ret;
		}
		//获取文件后缀
		 String suffix=fujian.getOriginalFilename().substring(fujian.getOriginalFilename().lastIndexOf(".")+1,fujian.getOriginalFilename().length());
		if(!"txt,doc,docx,wps,pdf".toUpperCase().contains(suffix.toUpperCase())){
				ret.put("type", "error");
				ret.put("msg", "请选择txt,doc,docx,wps,pdf格式的文档！");
				return ret;
		}
		//拿到绝对地址
		String savePath=request.getServletContext().getRealPath("/")+"/resources/upload/";
		File savePathFile=new File(savePath);
		if(!savePathFile.exists()) {
			//若不存在该目录，则创建目录
			savePathFile.mkdir();
		}
		String filename = new Date().getTime()+"."+suffix;
		try {
			//将文件保存至指定目录
			fujian.transferTo(new File(savePath+filename));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ret.put("type", "error");
			ret.put("msg", "保存文件异常");
			e.printStackTrace();
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "文档上传成功！");
		ret.put("filepath",request.getServletContext().getContextPath() + "/resources/upload/" + filename);
		return ret;
	}
		
}
