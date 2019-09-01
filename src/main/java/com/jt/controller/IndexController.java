package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jt.service.FileService;
import com.jt.vo.ImageVO;

import io.swagger.annotations.Api;

@Controller
public class IndexController {
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping("/page/{moduleName}")
	public String module(@PathVariable String moduleName) {
		
		return moduleName;
	}
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public ImageVO uploadFile(MultipartFile uploadFile) {
		
		
		return fileService.updateFile(uploadFile);
	}
}
