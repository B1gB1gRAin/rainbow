package com.bigbigrain.mapper;

import com.bigbigrain.entity.SpecificationOption;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
public interface SpecificationOptionMapper extends BaseMapper<SpecificationOption> {
	/**根据规格id获取选项值idList*/
	List<Long> selectIds(Long specId);
}
