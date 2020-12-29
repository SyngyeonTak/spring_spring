package com.koreait.mylegacy.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.mylegacy.exception.RegistException;
import com.koreait.mylegacy.model.domain.Emp;

@Repository
public class MybatisEmpDAO {
	
	private SqlSession sqlSession = null;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//목록 가져오기
	public List selectAll() {
		List list = new ArrayList();
		list = sqlSession.selectList("Emp.selectAll");
		return list;
	}
	
	//1건 등록
	public int insert(Emp emp) throws RegistException{
		int result = 0;
		result = sqlSession.insert("Emp.insert", emp);//emp안에 dept가 포함
		//만일, 부서등록이 실패하면 여기서 억지로 예외를 발생시켜버리자!!
		if(result==0) {
			//자바에서는 에러를 억지로 발생시켜주는 기능이 지원
			//throw(예외를 일으킨다.)
			//문법적인 문제가 아니라 result가 0인 경우를 예외로 정의해 버린다.
			//result가 0이면 비정상 종료가 된다.
			//비정상 종료를 방지하기위해 throws를 한다.(try catch를 하면 서비스메서드가 오류를 알 수 없다.)
			
			throw new RegistException("사원 등록에 실패하였습니다.");
		}
		return result;
	}
	
}






