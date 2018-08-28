package com.bigbigrain.sellergoods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bigbigrain.entity.Goods;
import com.bigbigrain.entity.GoodsDesc;
import com.bigbigrain.mapper.GoodsDescMapper;
import com.bigbigrain.mapper.GoodsMapper;
import com.bigbigrain.sellergoods.service.IGoodsService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

	@Autowired
	private GoodsDescMapper goodsDescMapper;
	
	@Override
	public Boolean saveCascade(Goods goods) {
		this.insertOrUpdate(goods);
		GoodsDesc goodsDesc = goods.getGoodsDesc();
		if( goodsDesc.getGoodsId() == null ) {
			goodsDesc.setGoodsId(goods.getId());
			goodsDescMapper.insert(goodsDesc);
		}else {
			goodsDescMapper.updateById(goodsDesc);
		}
		return true;
	}

}
