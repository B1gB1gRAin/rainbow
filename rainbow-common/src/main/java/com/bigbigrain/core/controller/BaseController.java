package com.bigbigrain.core.controller;

import com.bigbigrain.core.domain.Result;

public class BaseController {
	
	private int SUCCESS_CODE = 200;
	
	private int ERROR_CODE = 400;
	
	protected Result ok(String message) {
		return new Result(SUCCESS_CODE, message);
	}
	
	protected Result ok(String message, Object data) {
		return new Result(SUCCESS_CODE, message, data);
	}
	
	protected Result fail(String message) {
		return new Result(ERROR_CODE, message);
	}
}
