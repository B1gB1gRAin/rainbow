package com.bigbigrain.manager.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bigbigrain.core.controller.BaseController;

@RestController
@RequestMapping(value="/login")
public class LoginController extends BaseController{
	
	/**
	 * 获取当前登录用户信息
	 * @return
	 */
	@RequestMapping(value="/getCurrentUser")
	public Map<String, String> getCurrentUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.getCredentials());
		System.out.println(authentication.getName());
		System.out.println(authentication.getPrincipal());
		Map<String, String> currentUserMap= new HashMap<String, String>();
		currentUserMap.put("username", authentication.getName());
		return currentUserMap;
	}
}
