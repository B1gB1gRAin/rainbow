package com.bigbigrain.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bigbigrain.entity.Specification;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
public interface SpecificationMapper extends BaseMapper<Specification> {
	
	/**根据id查询 规格及规格选项*/
	Specification selectCascade(Long spId);
	
	List<Map<String, String>> selectOptionList();
	
}
