package com.bigbigrain.sellergoods.service;

import com.bigbigrain.entity.Seller;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
public interface ISellerService extends IService<Seller> {
	Seller selectBySellerId(String sellerId);
}
