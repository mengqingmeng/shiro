package com.mqm.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;
/**
 * 图像裁剪
 * @author mqm
 *
 */
@Transactional
@Service
public class ThumbnailService {
	
	public String thumbnail(CommonsMultipartFile file,String uploadPath,String realUploadPath){
		
		try {
			String des = realUploadPath +"/thum_" +file.getOriginalFilename();
			Thumbnails.of(file.getInputStream()).size(100, 100).toFile(des);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return uploadPath +"/thum_" +file.getOriginalFilename();
	}
}
