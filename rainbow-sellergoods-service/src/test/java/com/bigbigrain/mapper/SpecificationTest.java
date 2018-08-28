package com.bigbigrain.mapper;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bigbigrain.entity.Specification;

public class SpecificationTest {
	private ApplicationContext applicatonContext;
	
	@Before
	public void setUp() throws Exception{
		String configLocation = "classpath:spring/applicationContext-dao.xml";
		applicatonContext = new ClassPathXmlApplicationContext(configLocation);
	}
	
	@Test
	public void testSpecificationMapper() throws Exception{
		SpecificationMapper specificationMapper = (SpecificationMapper) applicatonContext.getBean("specificationMapper");
		Specification vo = specificationMapper.selectCascade(26L);	
		System.out.println(vo);
	}
}
