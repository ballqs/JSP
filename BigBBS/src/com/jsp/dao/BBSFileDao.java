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

	private BBSFileDao() {//������ Ư¡ : Ŭ�����̸��� ���� �̸��� ���� �޼ҵ�
	}
	public static synchronized BBSFileDao getInstance() {//Instance�� �����ϴ��� ������ �ľ����� ����
		if(bbsfDao == null) {
			bbsfDao = new BBSFileDao();
		}
		return bbsfDao;//����(static)���� ����ϱ� ���ؼ�
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
			System.out.println("selectById - ����");
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
