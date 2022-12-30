package car.rent.db;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

	private static Connection conn;
	//생성자를 private 으로 바꾼다.
	//생성자를 통한 객체화를 막기 위해서
	private DBConnection() {}
	
	//데이터베이스 커넥션을 전달해주는 메서드
	public static Connection getConnection() throws Exception {
		if(conn == null || conn.isClosed()) {
			connectDB(); // 디비 접속 실행
		}
		
		return conn;
	}
	
	//내부에서만 필요한 메서드라서 private 으로 만든다.
	private  static void connectDB() throws Exception {
		String dbDriver = "org.mariadb.jdbc.Driver";
		String url = "jdbc:mariadb://localhost:13306/rent_car";
		String id = "root";
		String passwd = "1234";
		
		 Class.forName(dbDriver);
		 conn = DriverManager.getConnection(url, id, passwd); 
		 System.out.println("DB접속완료!");
	}
	
	
	//connection 만 닫기 메서드
	public static void closeConnection(Connection conn) {
		try {
			
			if(conn != null) {
				conn.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void closeConnection(PreparedStatement pstmt , ResultSet res) {
		try {
			
			if(res != null) {
				res.close();
			}
			
			if(pstmt != null) {
				pstmt.close();
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void closeConnection(Connection conn, PreparedStatement pstmt , ResultSet res) {
		try {
			
			if(res != null) {
				res.close();
			}
			
			if(pstmt != null) {
				pstmt.close();
			}
			
			if(conn != null) {
				conn.close();
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
