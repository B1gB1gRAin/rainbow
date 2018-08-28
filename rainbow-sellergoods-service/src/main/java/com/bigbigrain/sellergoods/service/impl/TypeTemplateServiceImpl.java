package com.bigbigrain.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bigbigrain.entity.SpecificationOption;
import com.bigbigrain.entity.TypeTemplate;
import com.bigbigrain.mapper.SpecificationOptionMapper;
import com.bigbigrain.mapper.TypeTemplateMapper;
import com.bigbigrain.sellergoods.service.ITypeTemplateService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
@Service
public class TypeTemplateServiceImpl extends ServiceImpl<TypeTemplateMapper, TypeTemplate> implements ITypeTemplateService {

	@Autowired
	private SpecificationOptionMapper specificationOptionMapper;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Map> findSpecList(Long id) {
		TypeTemplate template = baseMapper.selectById(id);
		String specIds = template.getSpecIds();
		List<Map> specList = JSON.parseArray(specIds, Map.class);
		
		for (Map map : specList) {
			Wrapper<SpecificationOption> wrapper = new EntityWrapper<SpecificationOption>();
			wrapper.eq("spec_id", new Long( (Integer)(map.get("id")) ));
			List<SpecificationOption> options = specificationOptionMapper.selectList(wrapper);
			map.put("options", options);
		}
		return specList;
	}

}
