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

	private MemberDao() {//������ Ư¡ : Ŭ�����̸��� ���� �̸��� ���� �޼ҵ�
	}
	public static synchronized MemberDao getInstance() {//Instance�� �����ϴ��� ������ �ľ����� ����
		if(mDao == null) {
			mDao = new MemberDao();
		}
		return mDao;//����(static)���� ����ϱ� ���ؼ�
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
			System.out.println("INSERT�� ���� �Ϸ�!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {//������ �����ϴ� �κ�
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
			System.out.println("SELETC�� ���� �Ϸ�!");
			if(rs.next()) {
				if(rs.getString("PW").equals(pw)) {
					loginresult = 1;
				}else {
					loginresult = 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {//������ �����ϴ� �κ�
			this.close(conn,pstmt,rs);
		}
		return loginresult;
	}
}
