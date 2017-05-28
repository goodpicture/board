package com.dgit.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dgit.jdbc.JdbcUtil;

public class MemberDao {
	private static MemberDao dao = new MemberDao();

	private MemberDao() {
		// TODO Auto-generated constructor stub
	}

	public static MemberDao getInstance() {
		return dao;
	}
	public List<Member> selectList(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			String query = "select * from member";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			List<Member> memberList = new ArrayList<>();
			while(rs.next()){
				Member mb = new Member();
				mb.setId(rs.getString("memberid"));
				mb.setName(rs.getString("name"));
				mb.setPassword(rs.getString("password"));
				mb.setRegDate(rs.getTimestamp("regdate"));
				memberList.add(mb);
			}
			return memberList;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	public void insert(Connection conn, Member mem) throws SQLException{
		String query = "insert into member values (?,?,?,?)";
		PreparedStatement pstmt = null;
		
		try{
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, mem.getId());
		pstmt.setString(2, mem.getName());
		pstmt.setString(3, mem.getPassword());
		pstmt.setTimestamp(4, new Timestamp(mem.getRegDate().getTime()));
		pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
	}
	public Member selectById(Connection conn, String id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from member where memberid = ?";
		try{
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				Member mem = new Member();
				mem.setId(rs.getString("memberid"));
				mem.setName(rs.getString("name"));
				mem.setPassword(rs.getString("password"));
				mem.setRegDate(new Date(rs.getTimestamp("regdate").getTime()));
				return mem;
			}
			
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return null;
	}
	public List<Member> selectByAll(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from member";
		try {
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			List<Member> memberList = new ArrayList<>();
			while(rs.next()){
				Member mem =  new Member();
				mem.setId(rs.getString("memberid"));
				mem.setName(rs.getString("name"));
				mem.setPassword(rs.getString("password"));
				mem.setRegDate(rs.getDate("regdate"));
				memberList.add(mem);
				
			}	
				return memberList;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			
		}
	}
}
