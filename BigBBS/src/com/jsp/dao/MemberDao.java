package com.jsp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jsp.dto.MemberDto;

public class MemberDao {
	private static MemberDao mDao;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private int joinresult,loginresult;
	private ResultSet rs = null;

	private MemberDao() {//생성자 특징 : 클래스이름과 같은 이름을 가진 메소드
	}
	public static synchronized MemberDao getInstance() {//Instance가 존재하는지 없는지 파악한후 리턴
		if(mDao == null) {
			mDao = new MemberDao();
		}
		return mDao;//공용(static)으로 사용하기 위해서
	}
	public Connection getConnect() {
		// 오라클DB 접속 정보
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "BIG";
		String pw = "1234";

		try {
			// 드라이버 찾는 과정
			// 강제로 특정 클래스를 메모리에 올리기
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 오라클 드라이버
			System.out.println("드라이버 로드 성공");
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("데이터 베이스 연결 성공");
		} catch (ClassNotFoundException e) { // 드라이버
			e.printStackTrace();
		} catch (SQLException e) { // DB연결
			e.printStackTrace();
		}
		return conn;
	}
	public int join(MemberDto mDto) {
		joinresult = 0;
		conn = this.getConnect();
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO MEMBER VALUES(?,?,?,?)");
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, mDto.getName());
			pstmt.setString(2, mDto.getPassword());
			pstmt.setString(3, mDto.getUsername());
			pstmt.setString(4, mDto.getEmail());
			
			joinresult = pstmt.executeUpdate();
			System.out.println("INSERT문 실행 완료!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {//무조건 실행하는 부분
			this.close(conn,pstmt,null);
		}
		return joinresult;
	}
	public void close(Connection conn,PreparedStatement pstmt,ResultSet rs) {
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
	public int login(String id, String pw) {
		loginresult = 0;
		conn = this.getConnect();
		StringBuffer query = new StringBuffer();
		query.append("SELECT PW FROM MEMBER WHERE ID=?");
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			System.out.println("SELETC문 실행 완료!");
			if(rs.next()) {
				if(rs.getString("PW").equals(pw)) {
					loginresult = 1;
				}else {
					loginresult = 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {//무조건 실행하는 부분
			this.close(conn,pstmt,rs);
		}
		return loginresult;
	}
}
