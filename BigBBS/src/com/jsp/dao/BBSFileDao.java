package com.jsp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jsp.dto.BBSFileDto;

public class BBSFileDao {
	private static BBSFileDao bbsfDao;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private List<BBSFileDto> bbsfList;
	private ArrayList fileIdList;

	private BBSFileDao() {//생성자 특징 : 클래스이름과 같은 이름을 가진 메소드
	}
	public static synchronized BBSFileDao getInstance() {//Instance가 존재하는지 없는지 파악한후 리턴
		if(bbsfDao == null) {
			bbsfDao = new BBSFileDao();
		}
		return bbsfDao;//공용(static)으로 사용하기 위해서
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
	public List<BBSFileDto> selectByBbsId(String bbsId) {
		conn = this.getConnect();
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM BBS_FILE ");
		query.append("WHERE BBSID=? ");
		query.append("ORDER BY FILEID");
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, bbsId);
			rs = pstmt.executeQuery();
			bbsfList = new ArrayList<>();
			System.out.println("selectById - 실행");
			while(rs.next()) {
				BBSFileDto bbsfDto = new BBSFileDto();
				bbsfDto.setFileId(rs.getString("FILEID"));
				bbsfDto.setOrgn_file_nm(rs.getString("Orgn_file_nm"));
				bbsfList.add(bbsfDto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.close(conn,pstmt,rs);
		}
		return bbsfList;
	}
	public void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}

	}
	
	public String selectById(String fileId) {
		String bbsFile = "";
		conn = this.getConnect();
		StringBuffer query = new StringBuffer();
		query.append("SELECT save_file_nm ");
		query.append(" from bbs_file");
		query.append(" where fileid=?");
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, fileId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bbsFile = rs.getString("save_file_nm");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.close(conn, pstmt, rs);
		}
		return bbsFile;
	}
}
