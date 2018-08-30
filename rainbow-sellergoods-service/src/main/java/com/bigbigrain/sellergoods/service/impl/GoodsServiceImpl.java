package com.bigbigrain.sellergoods.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bigbigrain.entity.Brand;
import com.bigbigrain.entity.Goods;
import com.bigbigrain.entity.GoodsDesc;
import com.bigbigrain.entity.Item;
import com.bigbigrain.entity.ItemCat;
import com.bigbigrain.entity.Seller;
import com.bigbigrain.mapper.BrandMapper;
import com.bigbigrain.mapper.GoodsDescMapper;
import com.bigbigrain.mapper.GoodsMapper;
import com.bigbigrain.mapper.ItemCatMapper;
import com.bigbigrain.mapper.ItemMapper;
import com.bigbigrain.mapper.SellerMapper;
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
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private ItemCatMapper itemCatMapper;
	
	@Autowired
	private SellerMapper sellerMapper;
	
	@Autowired
	private BrandMapper brandMapper;
	
	@Override
	public Boolean saveCascade(Goods goods) throws Exception {
		this.insertOrUpdate(goods);
		GoodsDesc goodsDesc = goods.getGoodsDesc();
		if( goodsDesc.getGoodsId() == null ) {
			goodsDesc.setGoodsId(goods.getId());
			goodsDescMapper.insert(goodsDesc);
		}else {
			goodsDescMapper.updateById(goodsDesc);
			
			//更新前先将对应的item删除掉
			Wrapper<Item> wrapper = new EntityWrapper<Item>();
			wrapper.eq("goods_id", goods.getId());
			itemMapper.delete(wrapper);
		}
		
		this.saveItemList(goods);
		return true;
	}
	
	@SuppressWarnings("rawtypes")
	private void setItemValues(Item item, Goods goods) throws Exception{
		//商品分类 
		item.setCategoryId(goods.getCategory3Id());//三级分类ID
		item.setCreateTime(new Date());//创建日期
		item.setUpdateTime(new Date());//更新日期 
		
		item.setGoodsId(goods.getId());//商品ID
		item.setSellerId(goods.getSellerId());//商家ID
		
		//分类名称			
		ItemCat itemCat = itemCatMapper.selectById(goods.getCategory3Id());
		item.setCategory(itemCat.getName());
		//品牌名称
		Brand brand = brandMapper.selectById(goods.getBrandId());
		item.setBrand(brand.getName());
		//商家名称(店铺名称)
		Wrapper<Seller> wrapper = new EntityWrapper<Seller>();
		wrapper.eq("seller_id", goods.getSellerId());
		List<Seller> sellers = sellerMapper.selectList(wrapper);
		if( sellers.size() > 0 ) {
			item.setSeller(sellers.get(0).getNickName());
		}else {
			//此种情况为异常情况
			throw new Exception("当前登录账号为空，异常数据");
		}
		
		//图片
		List<Map> imageList = JSON.parseArray( goods.getGoodsDesc().getItemImages(), Map.class) ;
		if(imageList.size()>0){
			item.setImage( (String)imageList.get(0).get("url"));
		}
		
	}
	
	//插入sku列表数据
	private void saveItemList(Goods goods) throws Exception{
		
		if("1".equals(goods.getIsEnableSpec())){
			for(Item item:   goods.getItemList()){
				//构建标题  SPU名称+ 规格选项值
				String title=goods.getGoodsName();//SPU名称
				Map<String,Object> map=  JSON.parseObject(item.getSpec());
				for(String key:map.keySet()) {
					title+=" "+map.get(key);
				}
				item.setTitle(title);
				
				setItemValues(item,goods);
				
				itemMapper.insert(item);
			}
		}else{//没有启用规格			
			
			Item item=new Item();
			item.setTitle(goods.getGoodsName());//标题
			item.setPrice(goods.getPrice());//价格
			item.setNum(99999);//库存数量
			item.setStatus("1");//状态
			item.setIsDefault("1");//默认
			item.setSpec("{}");//规格
			
			setItemValues(item,goods);
			
			itemMapper.insert(item);
		}
		
	}

	/**
	 * 考虑到效率问题 此处不使用连接查询
	 */
	@Override
	public Goods findCascade(Long id) {
		Goods goods = baseMapper.selectById(id);
		GoodsDesc goodsDesc = goodsDescMapper.selectById(id);
		goods.setGoodsDesc(goodsDesc);
		Wrapper<Item> wrapper = new EntityWrapper<Item>();
		wrapper.eq("goods_id", id);
		List<Item> items = itemMapper.selectList(wrapper);
		goods.setItemList(items);
		return goods;
	}

}
