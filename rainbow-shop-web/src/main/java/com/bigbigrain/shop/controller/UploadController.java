package com.bigbigrain.shop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bigbigrain.core.controller.BaseController;
import com.bigbigrain.core.domain.Result;
import com.bigbigrain.core.utils.FastDFSClient;

@RestController
public class UploadController extends BaseController{
	
	@Value("${FILE_SERVER_URL}")//注入配置文件中的访问地址
	private String file_server_url;
	
	@RequestMapping(value="/upload")
	public Result upload(MultipartFile file) {
		String fileName = file.getOriginalFilename();//获取文件名
		String extName = fileName.substring(fileName.lastIndexOf(".")+1);//获取文件扩展名
		try {
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fdfs_client.conf");
			String fileId = fastDFSClient.uploadFile(file.getBytes(), extName);
			//file_server_url+fileId 为完整图片访问地址
			return this.ok("上传成功", file_server_url+fileId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return this.fail("上传文件失败");
		}
	}
}
