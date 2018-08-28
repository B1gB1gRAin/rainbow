package com.bigbigrain.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bigbigrain.entity.Brand;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
public interface BrandMapper extends BaseMapper<Brand> {
	List<Map<String, String>> selectOptionList();
}
