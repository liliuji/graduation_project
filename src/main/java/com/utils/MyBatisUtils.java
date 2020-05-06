package com.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * 包：com.qf.utils 创建时间：2017年10月16日下午3:00:39 创建：xing 版本：1.0
 */
public class MyBatisUtils {

	@Test
	public void test1() throws Exception{
		List<String> warnList=new ArrayList<>();
		//1、创建配置文件对象的File对象
		File file=new File("src\\main\\resources\\generatorConfig.xml");
		//2、创建配置对象
		Configuration configuration=new ConfigurationParser(warnList).parseConfiguration(file);
		//3、创建回调接口的实现类
		DefaultShellCallback callback=new DefaultShellCallback(true);
		//4、创建核心对象
		MyBatisGenerator generator=new MyBatisGenerator(configuration, callback, warnList);
		//5、自动生成
		generator.generate(null);
		System.out.println("逆向工程结束");
	}
}
