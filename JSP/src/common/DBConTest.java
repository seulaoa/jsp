package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConTest {

	public static void main(String[] args) {
		Connection con;
		try {
			DBCon db = new DBCon();
			con = db.getCon();
			System.out.println("연결성공");
			String name = "홍길동";
			String sql = "select * from user";//2.입력
			PreparedStatement ps = con.prepareStatement(sql); //1.쿼리창 열어
			ResultSet rs = ps.executeQuery(); //3.ctrl_Shift+f9 실행
			while(rs.next()) { //4.rs.next 다음 줄로 ㅓㅁ겨줌
				System.out.print(rs.getString("id")+",");
				System.out.print(rs.getString("pwd")+",");
				System.out.print(rs.getString("name"));	
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
