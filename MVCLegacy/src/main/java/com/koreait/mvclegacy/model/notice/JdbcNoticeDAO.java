package com.koreait.mvclegacy.model.notice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.koreait.mvclegacy.exception.DMLException;
import com.koreait.mvclegacy.model.domain.Notice;

/*
 * 스프링이 지원하는 DB연동 기술에는 여러가지가 있다.
 * [ Mybatis ]
 * SqlMapper: 쿼리문과 자바객체간 매핑
 * 
 * [ JDBC 자체 ]
 * 
 * [ Hibernate ]
 * ORM (Object Relation Mapping): 관계형DB와 자바객체와의 매핑
 * 
 *  [ JPA ]
 *  
 * */
@Repository
public class JdbcNoticeDAO implements NoticeDAO{
	private static final Logger logger = LoggerFactory.getLogger(JdbcNoticeDAO.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;//쿼리 수행객체
	
	//목록가져오기
	public List selectAll() {
		List list =null;
		String sql = "select * from notice order by notice_id desc";
		//매개변수 순서: 쿼리문, 바인드변수를 배열로 처리, 매핑객체(ResultSet의 데이터를 담는 객체)
		list = jdbcTemplate.query(sql, new Object[] {}, new RowMapper<Notice>() {
			public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
				logger.debug("mapRow메서드 호출: rowNum="+rowNum);
				
				Notice notice = new Notice();//empty
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setTitle(rs.getString("title"));
				notice.setWriter(rs.getString("writer"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setContent(rs.getString("content"));
				notice.setHit(rs.getInt("hit"));
				
				return notice;
			}
		});
		
		return list;
	}

	@Override
	public Notice select(int notice_id) {
		Notice notice = null;
		String sql = "select * from notice where notice_id =?";
		notice = jdbcTemplate.queryForObject(sql, new RowMapper<Notice>() {

			public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
				Notice notice = new Notice();//empty
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setTitle(rs.getString("title"));
				notice.setWriter(rs.getString("writer"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setContent(rs.getString("content"));
				notice.setHit(rs.getInt("hit"));
				
				return notice;
			}
		}, notice_id);
		return notice;
	}

	//jdbcTemplate에서 dml은 update메서드로 처리
	@Override
	public void insert(Notice notice) throws DMLException{
		String sql = "insert into notice(notice_id, title, writer, content) values(seq_notice.nextval, ?, ?, ?)";
		int result = jdbcTemplate.update(sql, notice.getTitle(), notice.getWriter(), notice.getContent());
		//result=0;//일부러 등록 실패로 간주
		if(result == 0) {
			throw new DMLException("등록 실패");
		}
		
	}

	@Override
	public void update(Notice notice) throws DMLException{
		String sql = "update notice set title=?, writer=?, content=? where notice_id=?";
		int result = 0;
		result = jdbcTemplate.update(sql, notice.getTitle(), notice.getWriter(), notice.getContent(), notice.getNotice_id());
		if(result == 0) {
			throw new DMLException("수정 실패입니다.");
		}
	}

	@Override
	public void delete(int notice_id) throws DMLException{
		String sql = "delete from notice where notice_id = ?";
		int result = 0;
		result =jdbcTemplate.update(sql, notice_id);
		if(result == 0) {
			throw new DMLException("수정 실패입니다.");
		}
	}
	
	
	
}










