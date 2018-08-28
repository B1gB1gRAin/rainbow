package com.bigbigrain.sellergoods.service;

import com.bigbigrain.entity.TypeTemplate;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
public interface ITypeTemplateService extends IService<TypeTemplate> {
	@SuppressWarnings("rawtypes")
	List<Map> findSpecList(Long id);
}
