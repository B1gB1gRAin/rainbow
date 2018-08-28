package com.bigbigrain.shop.controller;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bigbigrain.core.controller.BaseController;
import com.bigbigrain.core.domain.Result;
import com.bigbigrain.entity.Seller;
import com.bigbigrain.sellergoods.service.ISellerService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
@RestController
@RequestMapping(value="/seller")
public class SellerController extends BaseController {
	
	@Reference
	private ISellerService iSellerService;
	
	@RequestMapping(value="/search")
	public Page<Seller> search(@RequestBody Seller seller, int currentPage, int size){
		Page<Seller> page = new Page<Seller>(currentPage, size);
		//根据brand的相关属性 去构造查询条件
		Wrapper<Seller> wrapper = new EntityWrapper<Seller>();
		if(StringUtils.isNotBlank(seller.getName())) {
			wrapper.like("name", seller.getName());
		}
		Page<Seller> pageds = iSellerService.selectPage(page, wrapper);
		return pageds;
	}
	
	@RequestMapping(value="/find")
	public Seller find(Long id){
		return iSellerService.selectById(id);
	}
	
	@RequestMapping(value="/save")
	public Result save(@RequestBody Seller seller) {
		try {
			seller.setStatus("0");
			seller.setCreateTime(new Date());
			
			//密码加密
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String password = passwordEncoder.encode(seller.getPassword());
			seller.setPassword(password);
			
			iSellerService.insertOrUpdate(seller);
			return this.ok("保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return this.fail(e.getMessage());
		}
	}
	
	@RequestMapping("/delete")
	public Result delete(Long[] ids){
		try {
			List<Long> idList = Arrays.asList(ids);
			iSellerService.deleteBatchIds(idList);
			return this.ok("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return this.fail("删除失败");
		}
	}
	
}

