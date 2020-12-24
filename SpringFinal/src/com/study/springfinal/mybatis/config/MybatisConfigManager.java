package com.study.springfinal.mybatis.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Component;

//사용자 정의 클래스는 @을 Component라고 명명한다.
@Component
public class MybatisConfigManager {
	private static MybatisConfigManager instance;
	String resource = "com/study/springfinal/mybatis/config/config.xml";
	InputStream inputStream;
	SqlSessionFactory sqlSessionFactory;
	
	public MybatisConfigManager() {
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static MybatisConfigManager getInstance() {
		if(instance == null) {
			instance = new MybatisConfigManager();
		}
		
		return instance;
	}
	
	
	public SqlSession getSqlSession() {
		SqlSession sqlSession = null;
		sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	};
	
	public void close(SqlSession sqlSession) {
		if(sqlSession != null) {
			sqlSession.close();
		}
	}
	
}










