package com.jsp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jsp.dto.BBSDto;
import com.jsp.dto.BBSFileDto;

public class TransactionDao {
	private static TransactionDao tDao;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private int result;
	private ResultSet rs = null;
	private List<BBSFileDto> bbsfList;

	private TransactionDao() {//생성자 특징 : 클래스이름과 같은 이름을 가진 메소드
	}
	public static synchronized TransactionDao getInstance() {//Instance가 존재하는지 없는지 파악한후 리턴
		if(tDao == null) {
			tDao = new TransactionDao();
		}
		return tDao;//공용(static)으로 사용하기 위해서
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
	public int insertAll(BBSDto bbsDto, List<BBSFileDto> bbsfDtoList) {
		System.out.println("TransactionDao.java - insertAll 접속 완료!");
		result = 0;
		conn = this.getConnect();
		int result2 = 1;
		try {
			conn.setAutoCommit(false);
			
			int result1 = this.inserBbs(conn,bbsDto);
			if(bbsfDtoList != null) {
				result2 = this.inserFile(conn,bbsfDtoList);
			}
			if(result1>0 && result2>0) {
				conn.commit();
				result = 1;
			}else {
				conn.rollback();
				result = 0;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.close(conn, pstmt, null);
		}
		return result;
	}
	private int inserFile(Connection conn, List<BBSFileDto> bbsfDtoList) {
		result = 0;
		
		try {
			for(BBSFileDto bbsfDto : bbsfDtoList) {
				//안에 내용물이 다 꺼내질때까지 계속 돌림 : 향상된 포문
				StringBuffer query = new StringBuffer();
				query.append("INSERT INTO BBS_FILE");
				query.append(" (FILEID,BBSID,ORGN_FILE_NM,SAVE_FILE_NM)");
				query.append(" VALUES(BBS_FILE_SEQ.NEXTVAL , (SELECT MAX(BBSID)");
				//(SELECT MAX(BBSID)FROM BBS) : BBS 테이블에서 가장 큰 글번호를 받아와서 BBS_FILE 테이블의 BBSID 속성에 저장
				query.append("FROM BBS), ?, ?)");
				
				pstmt = conn.prepareStatement(query.toString());
				
				pstmt.setString(1, bbsfDto.getOrgn_file_nm());
				pstmt.setString(2, bbsfDto.getSave_file_nm());
				
				result = pstmt.executeUpdate();
				System.out.println("inserFile executeUpdate 완료!");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.close(null, pstmt, null);
		}
		
		return result;
	}
	private int inserBbs(Connection conn, BBSDto bbsDto) {
		result = 0;
		
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO BBS(BBSID,ID,BBSTITLE,BBSDATE,BBSCATEGORY,BBSCONTENT,BBSHIT,IMG)");
		query.append(" VALUES(BBS_SEQ.NEXTVAL,?,?,SYSDATE,?,?,0,?)");
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, bbsDto.getId());
			pstmt.setString(2, bbsDto.getBbsTitle());
			pstmt.setString(3, bbsDto.getBbsCategory());
			pstmt.setString(4, bbsDto.getBbsContent());
			pstmt.setString(5, bbsDto.getImg());
			result = pstmt.executeUpdate();
			System.out.println("inserBbs executeUpdate 완료!");
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.close(null, pstmt, null);
		}
		return result;
	}
	public int updateAll(BBSDto bbsDto) {
		result = 0;
		conn = this.getConnect();
		try {
			conn.setAutoCommit(false);
			result = this.updateBbs(conn,bbsDto);
			
			if(result > 0) {
				conn.commit();
				System.out.println("updateAll - commit 완료");
			}else {
				conn.rollback();
				System.out.println("updateAll - rollback 되었습니다.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.close(conn, null, null);
		}
		return result;
	}
	private int updateBbs(Connection conn, BBSDto bbsDto) {
		result = 0;
		StringBuffer query = new StringBuffer();
		query.append("UPDATE BBS SET ");
		query.append("BBSTITLE=?,BBSCATEGORY=?,BBSCONTENT=? ");
		query.append("WHERE BBSID=?");
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, bbsDto.getBbsTitle());
			pstmt.setString(2, bbsDto.getBbsCategory());
			pstmt.setString(3, bbsDto.getBbsContent());
			pstmt.setString(4, bbsDto.getBbsId());
			
			result = pstmt.executeUpdate();
			System.out.println("updateBbs - 실행 완료");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.close(null, pstmt, null);
		}
		return result;
	}
	public int Delete(BBSDto bbsDto, BBSFileDto bbsfDto) {
		result = 0;
		int result2 = 1;
		conn = this.getConnect();
		try {
			conn.setAutoCommit(false);
			int result1 = this.DeleteBBS(bbsDto.getBbsId());
			bbsfList = this.DeleteFileSelect(bbsfDto.getBbsId());
			if(bbsfList != null) {
				result2 = this.DeleteFileBBS(bbsfDto.getBbsId());				
			}
			if(result1 > 0 && result2 > 0) {
				result = 1;
				conn.commit();
				System.out.println("DeleteBBS - 삭제 완료");
				System.out.println("DeleteFileBBS - 파일 삭제 완료");
			}else {
				result = 0;
				conn.rollback();
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.close(conn, null, null);
		}
		return result;
	}
	private List<BBSFileDto> DeleteFileSelect(String str) {
		bbsfList = new ArrayList<>();
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM BBS_FILE WHERE BBSID=?");
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, Integer.parseInt(str));
			
			rs = pstmt.executeQuery();
			System.out.println("DeleteFileSelect - 실행");
			while(rs.next()) {
				BBSFileDto bbsfDto = new BBSFileDto();
				
				bbsfDto.setFileId(rs.getString(1));
				bbsfDto.setBbsId(rs.getString(2));
				bbsfDto.setOrgn_file_nm(rs.getString(3));
				bbsfDto.setSave_file_nm(rs.getString(4));
				
				bbsfList.add(bbsfDto);
			}
		}catch(SQLException e) {
			bbsfList = null;
			e.printStackTrace();
		}finally {
			this.close(null, pstmt, rs);
		}
		return bbsfList;
	}
	private int DeleteFileBBS(String str) {
		result = 0;
		StringBuffer query = new StringBuffer();
		query.append("delete from bbs_file where bbsid=?");
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, Integer.parseInt(str));
			
			result = pstmt.executeUpdate();
			System.out.println("DeleteFileBBS - 실행 완료");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.close(null, pstmt, null);
		}
		return result;
	}
	private int DeleteBBS(String str) {
		result = 0;
		StringBuffer query = new StringBuffer();
		query.append("delete from bbs where bbsid=?");
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, Integer.parseInt(str));
			
			result = pstmt.executeUpdate();
			System.out.println("DeleteBBS - 실행 완료");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.close(null, pstmt, null);
		}
		return result;
	}
}
