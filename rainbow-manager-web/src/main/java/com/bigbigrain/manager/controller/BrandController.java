package com.bigbigrain.manager.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bigbigrain.core.domain.Result;
import com.bigbigrain.entity.Brand;
import com.bigbigrain.sellergoods.service.IBrandService;

@RestController
@RequestMapping(value="/brand")
public class BrandController {

	@Reference
	private IBrandService iBrandService;
	
	/**
	 * 	获取到全部brand
	 * @return
	 */
	@RequestMapping(value="/getAll")
	public List<Brand> getAll() {
		return iBrandService.selectList(null);
	}
	
	@RequestMapping(value="/search")
	public Page<Brand> search(@RequestBody Brand brand, int currentPage, int size){
		Page<Brand> page = new Page<Brand>(currentPage, size);
		//根据brand的相关属性 去构造查询条件
		Wrapper<Brand> wrapper = new EntityWrapper<Brand>();
		if(StringUtils.isNotBlank(brand.getName())) {
			wrapper.like("name", brand.getName());
		}
		if(StringUtils.isNotBlank(brand.getFirstChar())) {
			wrapper.like("first_char", brand.getFirstChar());
		}
		Page<Brand> pagedBrands = iBrandService.selectPage(page, wrapper);
		return pagedBrands;
	}
	
	@RequestMapping(value="/save")
	public Result save(@RequestBody Brand brand) {
		Result result;
		try {
			if(iBrandService.insertOrUpdate(brand)) {
				result = new Result(200, "保存成功");
			}else {
				result = new Result(400, "保存失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = new Result(400, "保存失败");
		}
		return result;
	}
	
	@RequestMapping(value="/find")
	public Brand find(Long id){
		return iBrandService.selectById(id);
	}
	
	@RequestMapping("/delete")
	public Result delete(Long[] ids){
		Result result;
		try {
			List<Long> idList = Arrays.asList(ids);
			if(iBrandService.deleteBatchIds(idList)) {
				result = new Result(200, "删除成功");
			}else {
				result = new Result(400, "删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = new Result(400, "删除失败");
		}
		return result;
	}
	
	@RequestMapping(value="selectOptionList")
	public List<Map<String, String>> selectOptionList() {
		return iBrandService.selectOptionList();
	}
}
