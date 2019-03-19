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

	private TransactionDao() {//������ Ư¡ : Ŭ�����̸��� ���� �̸��� ���� �޼ҵ�
	}
	public static synchronized TransactionDao getInstance() {//Instance�� �����ϴ��� ������ �ľ����� ����
		if(tDao == null) {
			tDao = new TransactionDao();
		}
		return tDao;//����(static)���� ����ϱ� ���ؼ�
	}
	public Connection getConnect() {
		// ����ŬDB ���� ����
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "BIG";
		String pw = "1234";

		try {
			// ����̹� ã�� ����
			// ������ Ư�� Ŭ������ �޸𸮿� �ø���
			Class.forName("oracle.jdbc.driver.OracleDriver"); // ����Ŭ ����̹�
			System.out.println("����̹� �ε� ����");
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("������ ���̽� ���� ����");
		} catch (ClassNotFoundException e) { // ����̹�
			e.printStackTrace();
		} catch (SQLException e) { // DB����
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
		System.out.println("TransactionDao.java - insertAll ���� �Ϸ�!");
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
				//�ȿ� ���빰�� �� ������������ ��� ���� : ���� ����
				StringBuffer query = new StringBuffer();
				query.append("INSERT INTO BBS_FILE");
				query.append(" (FILEID,BBSID,ORGN_FILE_NM,SAVE_FILE_NM)");
				query.append(" VALUES(BBS_FILE_SEQ.NEXTVAL , (SELECT MAX(BBSID)");
				//(SELECT MAX(BBSID)FROM BBS) : BBS ���̺��� ���� ū �۹�ȣ�� �޾ƿͼ� BBS_FILE ���̺��� BBSID �Ӽ��� ����
				query.append("FROM BBS), ?, ?)");
				
				pstmt = conn.prepareStatement(query.toString());
				
				pstmt.setString(1, bbsfDto.getOrgn_file_nm());
				pstmt.setString(2, bbsfDto.getSave_file_nm());
				
				result = pstmt.executeUpdate();
				System.out.println("inserFile executeUpdate �Ϸ�!");
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
			System.out.println("inserBbs executeUpdate �Ϸ�!");
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
				System.out.println("updateAll - commit �Ϸ�");
			}else {
				conn.rollback();
				System.out.println("updateAll - rollback �Ǿ����ϴ�.");
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
			System.out.println("updateBbs - ���� �Ϸ�");
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
				System.out.println("DeleteBBS - ���� �Ϸ�");
				System.out.println("DeleteFileBBS - ���� ���� �Ϸ�");
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
			System.out.println("DeleteFileSelect - ����");
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
			System.out.println("DeleteFileBBS - ���� �Ϸ�");
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
			System.out.println("DeleteBBS - ���� �Ϸ�");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.close(null, pstmt, null);
		}
		return result;
	}
}
