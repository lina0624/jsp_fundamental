package kr.or.kpc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import kr.or.kpc.dto.MemberDto;
import kr.or.kpc.util.ConnLocator;


public class MemberDao {
	
	private static MemberDao single;

	private MemberDao() {
	}

	public static MemberDao getInstance() {
		if (single == null) {
			single = new MemberDao();
		}
		return single;
	}
	
	public ArrayList<MemberDto> select(int start, int len) {
		ArrayList<MemberDto> list = new ArrayList<MemberDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ConnLocator.getConnect();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT num, name, addr ");
			sql.append("FROM member ");
			sql.append("LIMIT ?, ?");	
			pstmt = con.prepareStatement(sql.toString());
			
			int index = 1;
			pstmt.setInt(index++, start);
			pstmt.setInt(index, len);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				index = 1;
				int num = rs.getInt(index++); // or rs.getInt("empno");
				String name = rs.getString(index++); // rs.getString("ename");
				String addr = rs.getString(index++); // rs.getrString("job");
				//MemberDto dto = new MemberDto(num, name, addr);
				list.add(new MemberDto(num, name, addr));

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(con!=null) con.close(); //Connection 자원을 반납
				if(pstmt!=null) pstmt.close();
				if(rs!=null) rs.close();
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return list;

	}

}
