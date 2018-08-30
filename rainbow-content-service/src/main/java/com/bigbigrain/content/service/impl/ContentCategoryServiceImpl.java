package com.bigbigrain.content.service.impl;

import com.bigbigrain.content.service.IContentCategoryService;
import com.bigbigrain.entity.ContentCategory;
import com.bigbigrain.mapper.ContentCategoryMapper;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 内容分类 服务实现类
 * </p>
 *
 * @author bigbigrain
 * @since 2018-08-04
 */
@Service
public class ContentCategoryServiceImpl extends ServiceImpl<ContentCategoryMapper, ContentCategory> implements IContentCategoryService {

}
