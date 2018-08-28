package com.bigbigrain.manager.controller;


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
import com.bigbigrain.entity.Specification;
import com.bigbigrain.sellergoods.service.ISpecificationOptionService;
import com.bigbigrain.sellergoods.service.ISpecificationService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
@RestController
@RequestMapping(value="/specification")
public class SpecificationController {
	
	@Reference
	private ISpecificationService iSpecificationService;
	
	@Reference
	private ISpecificationOptionService iSpecificationOptionService;
	
	/**
	 * 查询
	 * @param specification
	 * @param currentPage
	 * @param size
	 * @return
	 */
	@RequestMapping(value="/search")
	public Page<Specification> search(@RequestBody Specification specification, int currentPage, int size){
		Page<Specification> page = new Page<Specification>(currentPage, size);
		//根据brand的相关属性 去构造查询条件
		Wrapper<Specification> wrapper = new EntityWrapper<Specification>();
		if(StringUtils.isNotBlank(specification.getSpecName())) {
			wrapper.like("spec_name", specification.getSpecName());
		}
		Page<Specification> pages = iSpecificationService.selectPage(page, wrapper);
		return pages;
	}
	
	@RequestMapping(value="/find")
	public Specification find(Long id){
		/*Specification specification = iSpecificationService.selectById(id);
		Wrapper<SpecificationOption> wrapper = new EntityWrapper<SpecificationOption>();
		wrapper.eq("spec_id", id);
		iSpecificationOptionService.selectList(wrapper);*/
		
		return iSpecificationService.selectCascade(id);
	}

	@RequestMapping(value="/save")
	public Result save(@RequestBody Specification specification) {
		try {
			iSpecificationService.saveCascade(specification);
			return new Result(200, "保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Result(400, "保存失败");
		}
	}
	
	@RequestMapping(value="/delete")
	public Result delete(Long[] ids) {
		try {
			iSpecificationService.deleteCascade(ids);
			return new Result(200, "保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Result(400,e.getMessage());
		}
	}
	
	@RequestMapping(value="selectOptionList")
	public List<Map<String, String>> selectOptionList(){
		return iSpecificationService.selectOptionList();
	}
}

