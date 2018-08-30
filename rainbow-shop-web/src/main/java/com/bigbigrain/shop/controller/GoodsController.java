package com.bigbigrain.shop.controller;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bigbigrain.core.controller.BaseController;
import com.bigbigrain.core.domain.Result;
import com.bigbigrain.entity.Goods;
import com.bigbigrain.entity.ItemCat;
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
	
	@RequestMapping(value="/findAll")
	public List<Goods> findAll(){
		return iGoodsService.selectList(null);
	}
	
	@RequestMapping(value="/search")
	public Page<Goods> search(@RequestBody Goods goods, int currentPage, int size){
		Page<Goods> page = new Page<Goods>(currentPage, size);
		String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
		//根据Goods的相关属性 去构造查询条件
		Wrapper<Goods> wrapper = new EntityWrapper<Goods>();
		if(StringUtils.isNotBlank(goods.getGoodsName())) {
			wrapper.like("goods_name", goods.getGoodsName());
		}
		
		if(StringUtils.isNotBlank(goods.getAuditStatus())) {
			wrapper.like("audit_status", goods.getAuditStatus());
		}
		wrapper.eq("seller_id", sellerId);
		Page<Goods> pages = iGoodsService.selectPage(page, wrapper);
		return pages;
	}
	
	@RequestMapping(value="/save")
	public Result save(@RequestBody Goods goods) {
		goods.setAuditStatus("0");//设置未申请状态:如果是经过修改的商品，需要重新设置状态
		//当前商家ID
		String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//当是修改操作的情况
		if( goods.getId() != null ) {
			//首先判断商品是否是该商家的商品
			Goods savedGoods = iGoodsService.selectById(goods.getId());
			if(!savedGoods.getSellerId().equals(sellerId) || !goods.getSellerId().equals(sellerId) ){
				return this.fail("非法操作");
			}
		}else {
			goods.setSellerId(sellerId);
		}
		try {
			iGoodsService.saveCascade(goods);
			return this.ok("保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return this.fail("保存失败");
		}
	}
	
	@RequestMapping(value="/find")
	public Goods find(Long id){
		Goods goods = iGoodsService.findCascade(id);
		return goods;
	}
}

