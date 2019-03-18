package com.jsp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.jsp.dto.BBSDto;

public class BBSDao {
	private static BBSDao bDao;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private int result;
	private List<BBSDto> bbsList;
	private BBSDao() {
	}
	public static synchronized BBSDao getInstance() {
		if(bDao == null) {
			bDao = new BBSDao();
		}
		return bDao;
	}
	public Connection getConnect() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "BIG";
		String pw = "1234";
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//Class.forName("core.log.jdbc.driver.OracleDriver");
		//에러가 날시 Query문까지 다 보여주는 방식
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
	public List<BBSDto> selectAll(int startNum, int perpage){
		bbsList = new ArrayList<>();
		conn = this.getConnect();
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT T2.*");
		query.append("FROM (SELECT ROWNUM R2, T.*");
		query.append("FROM (SELECT BBSID,ID,substr(BBSTITLE,1,20)BBSTITLE,");
		query.append("BBSCATEGORY,substr(BBSCONTENT,1,50) BBSCONTENT,BBSDATE,BBSHIT ");
		query.append("FROM BBS ORDER BY BBSID DESC) T) T2 ");
		query.append("WHERE T2.R2 BETWEEN ? AND ?");
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			System.out.println("pstmt 등록 완료");
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, perpage);
			
			rs = pstmt.executeQuery();
			System.out.println("pstmt값을 rs에 담고 쿼리 실행");
			while(rs.next()) {
				BBSDto bbsDto = new BBSDto();
				
				bbsDto.setId(rs.getString("ID"));
				bbsDto.setBbsId(rs.getString("BBSID"));
				bbsDto.setBbsTitle(rs.getString("BBSTITLE"));
				bbsDto.setBbsDate(rs.getString("BBSDATE"));
				bbsDto.setBbsCategory(rs.getString("BBSCATEGORY"));
				bbsDto.setBbsContent(rs.getString("BBSCONTENT"));
				bbsDto.setBbsHit(rs.getString("BBSHIT"));
				
				bbsList.add(bbsDto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.close(conn,pstmt,null);
		}
		return bbsList;
	}
	public int insert(BBSDto bbsDto) {
		result = 0;
		conn = this.getConnect();
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO BBS(BBSID,ID,BBSTITLE,BBSCATEGORY,BBSCONTENT,BBSDATE,BBSHIT)");
		query.append(" values (bbs_seq.nextval,?,?,?,?,sysdate,0)");
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, bbsDto.getId());
			pstmt.setString(2, bbsDto.getBbsTitle());
			pstmt.setString(3, bbsDto.getBbsCategory());
			pstmt.setString(4, bbsDto.getBbsContent());
			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.close(conn,pstmt,null);
		}
		return result;
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
	public int hitUpdate(String id) {
		result = 0;
		conn = this.getConnect();
		StringBuffer query = new StringBuffer();
		query.append("UPDATE BBS SET BBSHIT = BBSHIT + 1 WHERE BBSID=?");
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.close(conn,pstmt,null);
		}
		return result;
	}
	public BBSDto selectById(String id) {

		BBSDto bbsDto = new BBSDto();
		conn = this.getConnect();
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT *");
		query.append("  FROM BBS");
		query.append("  WHERE BBSID=? ");
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				bbsDto.setId(rs.getString("ID"));
				bbsDto.setBbsId(rs.getString("BBSID"));
				bbsDto.setBbsTitle(rs.getString("BBSTITLE"));
				bbsDto.setBbsDate(rs.getString("BBSDATE"));
				bbsDto.setBbsCategory(rs.getString("BBSCATEGORY"));
				bbsDto.setBbsContent(rs.getString("BBSCONTENT"));
				bbsDto.setBbsHit(rs.getString("BBSHIT"));

			}
		} catch (SQLException e) {

		} finally {

			this.close(conn, pstmt, null);

		}

		return bbsDto;
	}
}
