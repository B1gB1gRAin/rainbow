package com.bigbigrain.sellergoods.service.impl;

import com.bigbigrain.entity.Brand;
import com.bigbigrain.mapper.BrandMapper;
import com.bigbigrain.sellergoods.service.IBrandService;

import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

	@Override
	public List<Map<String, String>> selectOptionList() {
		// TODO Auto-generated method stub
		return baseMapper.selectOptionList();
	}

}
