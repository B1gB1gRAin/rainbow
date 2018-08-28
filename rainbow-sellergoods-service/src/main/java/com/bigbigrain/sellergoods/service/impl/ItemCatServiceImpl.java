package com.bigbigrain.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bigbigrain.entity.ItemCat;
import com.bigbigrain.mapper.ItemCatMapper;
import com.bigbigrain.sellergoods.service.IItemCatService;

/**
 * <p>
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
@Service
public class ItemCatServiceImpl extends ServiceImpl<ItemCatMapper, ItemCat> implements IItemCatService {

}
