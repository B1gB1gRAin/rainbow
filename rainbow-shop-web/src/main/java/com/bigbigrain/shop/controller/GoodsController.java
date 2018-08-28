package com.bigbigrain.shop.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bigbigrain.core.controller.BaseController;
import com.bigbigrain.core.domain.Result;
import com.bigbigrain.entity.Goods;
import com.bigbigrain.sellergoods.service.IGoodsService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
@RestController
@RequestMapping(value="/goods")
public class GoodsController extends BaseController{

	@Reference
	private IGoodsService iGoodsService;
	
	@RequestMapping(value="/save")
	public Result save(@RequestBody Goods goods) {
		try {
			goods.setAuditStatus("0");
			String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
			goods.setSellerId(sellerId);
			iGoodsService.saveCascade(goods);
			return this.ok("保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return this.fail("保存失败");
		}
	}
}

