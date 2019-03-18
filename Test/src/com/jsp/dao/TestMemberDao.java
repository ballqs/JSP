package com.jsp.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jsp.dto.TestMemberDto;

public class TestMemberDao {
	private static TestMemberDao mDao;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private int result = 0;
	private ResultSet rs = null;
	
	private TestMemberDao() {
	}
	public static synchronized TestMemberDao getInstance() {
		if(mDao == null) {
			mDao = new TestMemberDao();
		}
		return mDao;
	}
	public Connection getConnect() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "BIG";
		String pw = "1234";
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("드라이버 로드 성공");
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("데이터 베이스 연결 성공");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public int join(TestMemberDto mDto) {
		conn = this.getConnect();
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO MEMBER VALUES(?,?,?,?)");
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, mDto.getName());
			pstmt.setString(2, mDto.getPassword());
			pstmt.setString(3, mDto.getUsername());
			pstmt.setString(4, mDto.getEmail());
			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.close(conn,pstmt,null);
		}
		return result;
	}
	private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
