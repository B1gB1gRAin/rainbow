package com.bigbigrain.sellergoods.service;

import com.bigbigrain.entity.Goods;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
public interface IGoodsService extends IService<Goods> {
	Boolean saveCascade(Goods goods) throws Exception;
	
	Goods findCascade(Long id);
}
