package com.koreait.mylegacy.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.koreait.mylegacy.exception.RegistException;
import com.koreait.mylegacy.model.domain.Dept;
import com.koreait.mylegacy.model.domain.Emp;

@Repository
public class MybatisDeptDAO {
	SqlSession sqlSession = null;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	
	//1건 등록
	public int insert(Dept dept) throws RegistException{
		int result = 0;
		result = sqlSession.insert("Dept.insert", dept);//emp안에 dept가 포함
		if(result==0) {
			
			throw new RegistException("부서 등록에 실패하였습니다.");
		}
		return result;
	}
	
}







