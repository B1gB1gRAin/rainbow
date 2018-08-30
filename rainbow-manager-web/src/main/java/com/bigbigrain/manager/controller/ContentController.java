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
import com.bigbigrain.content.service.IContentService;
import com.bigbigrain.core.domain.Result;
import com.bigbigrain.entity.Content;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
@RestController
@RequestMapping(value="/content")
public class ContentController {
	@Reference
	private IContentService iContentService;
	
	/**
	 * 	获取到全部content
	 * @return
	 */
	@RequestMapping(value="/getAll")
	public List<Content> getAll() {
		return iContentService.selectList(null);
	}
	
	@RequestMapping(value="/search")
	public Page<Content> search(@RequestBody Content content, int currentPage, int size){
		Page<Content> page = new Page<Content>(currentPage, size);
		//根据content的相关属性 去构造查询条件
		Wrapper<Content> wrapper = new EntityWrapper<Content>();
	
		Page<Content> pagedContents = iContentService.selectPage(page, wrapper);
		return pagedContents;
	}
	
	@RequestMapping(value="/save")
	public Result save(@RequestBody Content content) {
		Result result;
		try {
			if(iContentService.insertOrUpdate(content)) {
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
	public Content find(Long id){
		return iContentService.selectById(id);
	}
	
	@RequestMapping("/delete")
	public Result delete(Long[] ids){
		Result result;
		try {
			List<Long> idList = Arrays.asList(ids);
			if(iContentService.deleteBatchIds(idList)) {
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

