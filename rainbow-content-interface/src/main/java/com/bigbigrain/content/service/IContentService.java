package com.bigbigrain.content.service;

import com.bigbigrain.entity.Content;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
public interface IContentService extends IService<Content> {
	/**根据分类ID查询内容信息*/
	List<Content> findByCategoryId(Long categoryId);
}
