package java_day27;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUpdate {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;

		// 1. 드라이버 로딩 (프로그램에서 처음 한번만 로딩해주면 된다)
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 등록 실패");
			System.exit(0); // 프로그램 종료
		}

		try {
			// 2. 데이터베이스 서버와 연결(Connection)한다.
			// ip주소 (ifconfig 주소[진], localhost = 127.0.0.1[내컴퓨터])
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String id = "jdbc";
			String pw = "jdbc";
			conn = DriverManager.getConnection(url, id, pw);

			// 3. 쿼리문 객체를 생성한다.
			stmt = conn.createStatement();
			
			StringBuffer query = new StringBuffer();
			query.append("update  	 		       ");
			query.append("		students	       ");
			query.append("set stu_name = '은지'	   ");
			query.append("where   1 = 1		       ");
			query.append("	 and stu_id = 3	       ");
			
			int cnt = stmt.executeUpdate(query.toString());
			
			if (cnt > 0) {
				System.out.println(cnt + " 행 삽입 되었습니다.");
			} else {
				System.out.println("업데이트가 잘 안되었습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) try {stmt.close();} catch(SQLException e) {}
			if (conn != null) try {conn.close();} catch(SQLException e) {}
		}
	}
}
