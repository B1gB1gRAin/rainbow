package com.bigbigrain.sellergoods.service;

import com.bigbigrain.entity.Brand;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类:品牌接口
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
public interface IBrandService extends IService<Brand> {
	List<Map<String, String>> selectOptionList();
}
