package com.jt.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.vo.ImageVO;

@Service
public class FileServiceimpl implements FileService{

	@Override
	public ImageVO updateFile(MultipartFile uploadFile) {
		
		//定义本地磁盘路径
		String localDirPath = "D:/1-jt/image/";
		return null;
		
		
	}

}
