package com.bigbigrain.portal.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bigbigrain.content.service.IContentService;
import com.bigbigrain.entity.Content;

@RestController
@RequestMapping(value="/content")
public class ContentController {

	@Reference
	private IContentService iContentService;
	
	@RequestMapping(value="/findByCategoryId")
	public List<Content> findByCategoryId(Long categoryId){
		return iContentService.findByCategoryId(categoryId);
	}
}
