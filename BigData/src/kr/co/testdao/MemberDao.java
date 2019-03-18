package kr.co.testdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.co.testdto.MemberDto;

public class MemberDao {
	Connection con = null;
	PreparedStatement stmt = null;
	int result = 0;
	
	
	public Connection getConnect() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "BIG";
		String pw = "1234";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("데이터 베이스 연결 성공");
			con = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	
	public int insertMemberDao(MemberDto memberDto){
		try {
			con = getConnect();
			String sql = "INSERT INTO jsp_member (id, pw, name)"
					+ "VALUES(?,?,?)";
			stmt.setString(1, memberDto.getID());
			stmt.setString(2, memberDto.getPW());
			stmt.setString(3, memberDto.getNAME());
			stmt = con.prepareStatement(sql);
			
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
