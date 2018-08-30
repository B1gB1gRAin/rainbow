package com.bigbigrain.content.service.impl;

import com.bigbigrain.content.service.IContentService;
import com.bigbigrain.entity.Content;
import com.bigbigrain.mapper.ContentMapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
@SuppressWarnings(value= {"unchecked","rawtypes"})
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements IContentService {

	@Autowired
	private RedisTemplate redisTemplate;
	
	@Override
	public List<Content> findByCategoryId(Long categoryId) {
		List<Content> contents = (List<Content>) redisTemplate.boundHashOps("content").get(categoryId);
		if( contents == null ) {
			System.out.println("从数据库读取数据放入缓存");
			Wrapper<Content> wrapper = new EntityWrapper<Content>();
			wrapper.eq("category_id", categoryId);
			contents = baseMapper.selectList(wrapper);
			redisTemplate.boundHashOps("content").put(categoryId, contents);//存入缓存 
		}else {
			System.out.println("从缓存中读取数据");
		}
		return contents;
		
	}

	@Override
	public boolean insertOrUpdate(Content content) {
		Long categoryId = null;
		if(content.getId()!=null) {
			categoryId = baseMapper.selectById(content.getId()).getCategoryId();
			//清除缓存
			redisTemplate.boundHashOps("content").delete(categoryId);
		}
		//修改的情况 如果广告类型变了的情况 要把变更后的类型的缓存也要删除掉
		if( categoryId != null && categoryId != content.getCategoryId()  ) {
			redisTemplate.boundHashOps("content").delete(content.getCategoryId());
		}
		
		return super.insertOrUpdate(content);
	}

	@Override
	public boolean deleteBatchIds(Collection<? extends Serializable> idList) {
		for (Serializable id : idList) {
			Long categoryId = baseMapper.selectById(id).getCategoryId();
			//清除缓存
			redisTemplate.boundHashOps("content").delete(categoryId);
			baseMapper.deleteById(id);
		}
		return true;
	}


}
