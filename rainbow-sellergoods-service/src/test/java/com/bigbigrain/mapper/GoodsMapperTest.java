package com.bigbigrain.mapper;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bigbigrain.entity.GoodsDesc;
import com.bigbigrain.entity.Specification;

public class GoodsMapperTest {
private ApplicationContext applicatonContext;
	
	@Before
	public void setUp() throws Exception{
		String configLocation = "classpath:spring/applicationContext-dao.xml";
		applicatonContext = new ClassPathXmlApplicationContext(configLocation);
	}
	
	@Test
	public void testSpecificationMapper() throws Exception{
		GoodsDesc goodsDesc = new GoodsDesc();
		goodsDesc.setPackageList("4444");
		goodsDesc.setSaleService("dddddd");
		System.out.println(goodsDesc.getGoodsId()!=null);
		
	}

}
