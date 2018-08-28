package com.bigbigrain.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bigbigrain.entity.Seller;
import com.bigbigrain.mapper.SellerMapper;
import com.bigbigrain.sellergoods.service.ISellerService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
@Service
public class SellerServiceImpl extends ServiceImpl<SellerMapper, Seller> implements ISellerService {

}
