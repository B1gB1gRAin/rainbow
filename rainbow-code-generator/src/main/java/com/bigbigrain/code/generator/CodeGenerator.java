package com.bigbigrain.code.generator;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
/**
 * 使用mybatis-plus代码生成器 生成相关代码
 * @author zhangy
 *
 */
public class CodeGenerator {
	public static void main(String[] args) {
		//1. 全局配置
		GlobalConfig config = new GlobalConfig();
		config.setActiveRecord(false) // 是否支持AR模式
			  .setAuthor("bigbigrain") // 作者
			  .setOutputDir("F:\\rain\\rainbow\\src\\main\\java") // 生成路径
			  .setFileOverride(true)  // 文件覆盖
			  .setIdType(IdType.AUTO) // 主键策略
			  .setServiceName("I%sService")  // 设置生成的service接口的名字的首字母是否为I
			  					   // IEmployeeService
 			  .setBaseResultMap(true)
 			  .setBaseColumnList(true);
		
		//2. 数据源配置
		DataSourceConfig  dsConfig  = new DataSourceConfig();
		dsConfig.setDbType(DbType.MYSQL)  // 设置数据库类型
				.setDriverName("com.mysql.jdbc.Driver")
				.setUrl("jdbc:mysql://localhost:3306/rainbow")
				.setUsername("root")
				.setPassword("root");
		 
		//3. 策略配置
		StrategyConfig stConfig = new StrategyConfig();
		stConfig.setCapitalMode(true) //全局大写命名
				.setDbColumnUnderline(true)  // 指定表名 字段名是否使用下划线
				.setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
				.setTablePrefix("tb_")
				.setInclude("tb_address"
						,"tb_areas"
						,"tb_brand"
						,"tb_cities"
						,"tb_content"
						,"tb_content_category"
						,"tb_freight_template"
						,"tb_goods"
						,"tb_goods_desc"
						,"tb_item"
						,"tb_item_cat"
						,"tb_order"
						,"tb_order_item"
						,"tb_pay_log"
						,"tb_provinces"
						,"tb_seckill_goods"
						,"tb_seckill_order"
						,"tb_seller"
						,"tb_specification"
						,"tb_specification_option"
						,"tb_type_template");
		
		//4. 包名策略配置 
		PackageConfig pkConfig = new PackageConfig();
		pkConfig.setParent("com.bigbigrain")
				.setMapper("mapper")
				.setService("service")
				.setController("controller")
				.setEntity("entity")
				.setXml("mapper");
		
		//5. 整合配置
		AutoGenerator  ag = new AutoGenerator();
		
		ag.setGlobalConfig(config)
		  .setDataSource(dsConfig)
		  .setStrategy(stConfig)
		  .setPackageInfo(pkConfig);
		
		//6. 执行
		ag.execute();

	}

}
