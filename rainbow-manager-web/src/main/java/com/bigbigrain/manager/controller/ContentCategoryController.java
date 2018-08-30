package com.bigbigrain.manager.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bigbigrain.content.service.IContentCategoryService;
import com.bigbigrain.core.domain.Result;
import com.bigbigrain.entity.ContentCategory;

/**
 * <p>
 * 内容分类 前端控制器
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
@RestController
@RequestMapping(value="/contentCategory")
public class ContentCategoryController {
	@Reference
	private IContentCategoryService iContentCategoryService;
	
	/**
	 * 	获取到全部contentCategory
	 * @return
	 */
	@RequestMapping(value="/getAll")
	public List<ContentCategory> getAll() {
		return iContentCategoryService.selectList(null);
	}
	
	@RequestMapping(value="/search")
	public Page<ContentCategory> search(@RequestBody ContentCategory contentCategory, int currentPage, int size){
		Page<ContentCategory> page = new Page<ContentCategory>(currentPage, size);
		//根据contentCategory的相关属性 去构造查询条件
		Wrapper<ContentCategory> wrapper = new EntityWrapper<ContentCategory>();
	
		Page<ContentCategory> pagedContentCategorys = iContentCategoryService.selectPage(page, wrapper);
		return pagedContentCategorys;
	}
	
	@RequestMapping(value="/save")
	public Result save(@RequestBody ContentCategory contentCategory) {
		Result result;
		try {
			if(iContentCategoryService.insertOrUpdate(contentCategory)) {
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
	public ContentCategory find(Long id){
		return iContentCategoryService.selectById(id);
	}
	
	@RequestMapping("/delete")
	public Result delete(Long[] ids){
		Result result;
		try {
			List<Long> idList = Arrays.asList(ids);
			if(iContentCategoryService.deleteBatchIds(idList)) {
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
}

