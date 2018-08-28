package com.bigbigrain.shop.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.bigbigrain.core.controller.BaseController;
import com.bigbigrain.core.domain.Result;
import com.bigbigrain.entity.ItemCat;
import com.bigbigrain.sellergoods.service.IItemCatService;

/**
 * <p>
 * 商品类目 前端控制器
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
@RestController
@RequestMapping(value="/itemCat")
public class ItemCatController  extends BaseController{
	@Reference
	private IItemCatService iItemCatService;
	
	@RequestMapping(value="findByParentId")
	public List<ItemCat> findByParentId(Long parentId){
		Wrapper<ItemCat> wrapper = new EntityWrapper<ItemCat>();
		wrapper.eq("parent_id", parentId);
		return iItemCatService.selectList(wrapper);
	}
	
	@RequestMapping(value="/find")
	public ItemCat find(Long id){
		return iItemCatService.selectById(id);
	}
	
	@RequestMapping(value="/save")
	public Result save(@RequestBody ItemCat itemCat) {
		try {
			iItemCatService.insertOrUpdate(itemCat);
			return this.ok("保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return this.fail(e.getMessage());
		}
	}
	
	@RequestMapping(value="/delete")
	public Result delete(Long[] ids){
		try {
			List<Long> idList = Arrays.asList(ids);
			iItemCatService.deleteBatchIds(idList);
			return this.ok("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return this.fail("删除失败");
		}
	}
}

