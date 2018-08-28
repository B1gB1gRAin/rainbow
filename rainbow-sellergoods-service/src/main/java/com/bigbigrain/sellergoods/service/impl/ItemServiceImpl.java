package com.bigbigrain.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bigbigrain.entity.Item;
import com.bigbigrain.mapper.ItemMapper;
import com.bigbigrain.sellergoods.service.IItemService;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

}
