package com.bigbigrain.shop.controller;


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
import com.bigbigrain.core.controller.BaseController;
import com.bigbigrain.core.domain.Result;
import com.bigbigrain.entity.TypeTemplate;
import com.bigbigrain.sellergoods.service.ITypeTemplateService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController extends BaseController{
	
	@Reference
	private ITypeTemplateService iTypeTemplateService;
	
	@RequestMapping(value="/search")
	public Page<TypeTemplate> search(@RequestBody TypeTemplate typeTemplate, int currentPage, int size){
		Page<TypeTemplate> page = new Page<TypeTemplate>(currentPage, size);
		//根据brand的相关属性 去构造查询条件
		Wrapper<TypeTemplate> wrapper = new EntityWrapper<TypeTemplate>();
		if(StringUtils.isNotBlank(typeTemplate.getName())) {
			wrapper.like("name", typeTemplate.getName());
		}
		Page<TypeTemplate> pages = iTypeTemplateService.selectPage(page, wrapper);
		return pages;
	}
	
	@RequestMapping(value="/save")
	public Result save(@RequestBody TypeTemplate typeTemplate) {
		try {
			iTypeTemplateService.insertOrUpdate(typeTemplate);
			return this.ok("保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return this.fail("保存失败");
		}
	}
	
	@RequestMapping(value="find")
	public TypeTemplate find(Long id) {
		return iTypeTemplateService.selectById(id);
	}
	
	@RequestMapping(value="delete")
	public Result delete(Long[] ids) {
		if(ids.length == 0) {
			return this.fail("要删除的ids不能为空");
		}
		
		try {
			iTypeTemplateService.deleteBatchIds( Arrays.asList(ids) );
			return this.ok("删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return this.fail("删除失败");
		}
	}
	
	/**
	 * 规格选项列表
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/findSpecList")
	public List<Map> findSpecList(Long id) {
		return iTypeTemplateService.findSpecList(id);
	}
}

