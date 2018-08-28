package com.bigbigrain.sellergoods.service;

import com.bigbigrain.entity.Specification;

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
public interface ISpecificationService extends IService<Specification> {
	
	/**级联查询*/
	Specification selectCascade(Long id);
	
	/**保存*/
	boolean saveCascade(Specification specification);
	
	/**删除
	 * @throws Exception */
	boolean deleteCascade(Long[] ids) throws Exception;
	
	List<Map<String, String>> selectOptionList();
	
}
