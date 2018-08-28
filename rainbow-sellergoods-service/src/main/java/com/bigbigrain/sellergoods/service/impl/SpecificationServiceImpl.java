package com.bigbigrain.sellergoods.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bigbigrain.entity.Specification;
import com.bigbigrain.entity.SpecificationOption;
import com.bigbigrain.mapper.SpecificationMapper;
import com.bigbigrain.mapper.SpecificationOptionMapper;
import com.bigbigrain.sellergoods.service.ISpecificationService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
@Service
public class SpecificationServiceImpl extends ServiceImpl<SpecificationMapper, Specification> implements ISpecificationService {
	
	@Autowired
	private SpecificationOptionMapper optionMapper;
	
	@Override
	public Specification selectCascade(Long id) {
		// TODO Auto-generated method stub
		return baseMapper.selectCascade(id);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean saveCascade(Specification specification) {
		List<Long> optionList = null;
		//判断规格id是否为空 来执行相应的操作
		if( specification.getId() != null ) {
			baseMapper.updateById(specification);
			optionList = optionMapper.selectIds(specification.getId());
		}else {
			baseMapper.insert(specification);
		}
		
		//保存规格选项相关信息
		List<SpecificationOption> options = specification.getOptions();
		for (SpecificationOption specificationOption : options) {
			specificationOption.setSpecId(specification.getId());
			//根据规格选项ID是否为空动态执行相应的方法
			if( specificationOption.getId() != null ) {
				optionMapper.updateById(specificationOption);
				optionList.remove(specificationOption.getId());
			}else {
				optionMapper.insert(specificationOption);
			}
		}
		
		//如果optionList中有值  即从前台删除了某个选项值，从后台执行删除
		if( optionList.size() > 0 ) {
			optionMapper.deleteBatchIds(optionList);
		}
		return true;
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean deleteCascade(Long[] ids) throws Exception{
		if( ids.length == 0 ) {
			throw new Exception("Error: 删除的ids不能为空");
		}
		List<Long> idList = Arrays.asList(ids);
		optionMapper.deleteBatchIds( idList );
		baseMapper.deleteBatchIds(idList);
		return true;
	}

	@Override
	public List<Map<String, String>> selectOptionList() {
		// TODO Auto-generated method stub
		return baseMapper.selectOptionList();
	}

}
