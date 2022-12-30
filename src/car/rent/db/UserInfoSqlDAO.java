package car.rent.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import car.rent.vo.RentUserInfo;
import car.rent.vo.UserInfo;
import car.rent.vo.UserLoginInfo;


//유저에 쓰이는 모든 sql쿼리 모아둠
public class UserInfoSqlDAO {
	
	
	//고객 정보 입력 메서드
	public void insertUserInfo(String userId,String userName, String passwd,
			                   int userAge,String userPhone,
			                   String userLicenceNumber) throws Exception{
		Connection conn = DBConnection.getConnection();
		
		if(conn != null) {
			
			conn.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("insert user_info(user_id, user_password, user_name, user_age, user_phone, user_licence_number) \n");
			sql.append("values(?,?,?,?,?,?)");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			int valueIndex=1;
			pstmt.setString(valueIndex++, userId);
			pstmt.setString(valueIndex++, passwd);
			pstmt.setString(valueIndex++, userName);
			pstmt.setInt(valueIndex++, userAge);
			pstmt.setString(valueIndex++, userPhone);
			pstmt.setString(valueIndex++, userLicenceNumber);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
				System.out.println("고객정보가 등록되었습니다.");
			}else {
				conn.rollback();
				System.out.println("고객정보 등록이 실패했습니다.");
			}
			
			
		}
	}
	
	//고객 정보 출력 메서드
	public List<UserInfo> getAllInfo() throws Exception{
		Connection conn = DBConnection.getConnection();
		List<UserInfo> list = new ArrayList<>();
		
		if(conn != null) {
			StringBuilder sql = new StringBuilder();
			sql.append("select *");
			sql.append("from user_info");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				UserInfo user = new UserInfo();
				user.setUserId(res.getString("user_id"));
				user.setPasswd(res.getString("user_password"));
				user.setUserName(res.getString("user_name"));
				user.setUserAge(res.getInt("user_age"));
				user.setUserPhone(res.getString("user_phone"));
				user.setUserLicenceNumber(res.getString("user_licence_number"));
				list.add(user);
			}
			
		}		
		
		return list;
	}
	
	
	//고객 정보 검색 메서드
	public List<UserInfo> searchUserInfo(String name) throws Exception{
		Connection conn = DBConnection.getConnection();
		List<UserInfo> list = new ArrayList<>();
		
		if(conn != null) {
			StringBuilder sql = new StringBuilder();
			sql.append("select * \n");
			sql.append("from user_info ui \n");
			sql.append("where ui.user_name like '%"+name+"%'");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet res = pstmt.executeQuery();
			
			if(res!=null) {
				while(res.next()) {
					UserInfo user = new UserInfo();
					user.setUserId(res.getString("user_id"));
					user.setPasswd(res.getString("user_password"));
					user.setUserName(res.getString("user_name"));
					user.setUserAge(res.getInt("user_age"));
					user.setUserPhone(res.getString("user_phone"));
					user.setUserLicenceNumber(res.getString("user_licence_number"));
					list.add(user);
					
				}
			}else {
				System.out.println("잘못입력했습니다.");
			}
		}
		return list;
	}
	
	//관리자가 쓰는 고객정보 수정 메서드
	public void UpdateInfo(String userName,String passwd,String userId,int userAge,String userPhone,
                           String userLicenceNumber) throws Exception{
		Connection conn = DBConnection.getConnection();
		
		if(conn!=null) {
			conn.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("update user_info  \n");
			sql.append("set user_id = ?, user_password = ?, user_age = ?, user_phone = ?, user_licence_number= ? \n");
			sql.append("where user_name like ? ");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			int indexVal = 1;
			pstmt.setString(indexVal++, userId);
			pstmt.setString(indexVal++, passwd);
			pstmt.setInt(indexVal++, userAge);
			pstmt.setString(indexVal++, userPhone);
			pstmt.setString(indexVal++, userLicenceNumber);
			pstmt.setString(indexVal++, userName);
			
			int result = pstmt.executeUpdate();
			
			if(result>0) {
				conn.commit();
				System.out.println("고객정보 수정 완료!");
			}else {
				conn.rollback();
				System.out.println("고객정보 수정 실패.");
			}		
		}
	}
	
	//유저가 쓰는 개인정보 수정 메서드
	public void UpdateUserInfo(String userName,String passwd,String userId,int userAge,String userPhone,
            String userLicenceNumber) throws Exception{
		Connection conn = DBConnection.getConnection();
		if(conn!=null) {
			conn.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("update user_info  \n");
			sql.append("set user_name = ?, user_password = ?, user_age = ?, user_phone = ?, user_licence_number= ? \n");
			sql.append("where user_id like ? ");

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			int indexVal = 1;
			pstmt.setString(indexVal++, userName);
			pstmt.setString(indexVal++, passwd);
			pstmt.setInt(indexVal++, userAge);
			pstmt.setString(indexVal++, userPhone);
			pstmt.setString(indexVal++, userLicenceNumber);
			pstmt.setString(indexVal++, userId);

			int result = pstmt.executeUpdate();

			if(result>0) {
				conn.commit();
				System.out.println("개인정보 수정 완료!");
			}else {
				conn.rollback();
				System.out.println("개인정보 수정 실패.");
				}		
			}
			}
	
	//관리자 고객정보 삭제 메서드
	public void deleteInfo(String name) throws Exception{
		Connection conn = DBConnection.getConnection();
		
		if(conn != null) {
			StringBuilder sql = new StringBuilder();
			conn.setAutoCommit(false);
			
			sql.append("delete from user_info \n");
			sql.append("where user_name like '"+name+"'");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
				System.out.println("고객정보가 정상적으로 삭제되었습니다!");
				
			}else {
				conn.rollback();
				System.out.println("고객정보 삭제실패.");
			}
		}
	}
	
	//로그인 정보 받아오는 메서드
	public List<UserLoginInfo> loginInfo() throws Exception{
		Connection conn = DBConnection.getConnection();
		List<UserLoginInfo> list = new ArrayList<>();
		
		if(conn != null) {
			StringBuilder sql = new StringBuilder();
			sql.append("select user_id, user_password, user_name from user_info");
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet res = pstmt.executeQuery();
			
			if(res != null) {
				while(res.next()) {
					UserLoginInfo uli = new UserLoginInfo();
					uli.setId(res.getString("user_id"));
					uli.setPasswd(res.getString("user_password"));
					uli.setName(res.getString("user_name"));
					list.add(uli);
				}
			}
		}
		return list;
	}
	
	//관리자 고객 정보 출력 메서드
		public List<RentUserInfo> rootGetAllInfo() throws Exception{
			Connection conn = DBConnection.getConnection();
			List<RentUserInfo> list = new ArrayList<>();
			
			if(conn != null) {
				StringBuilder sql = new StringBuilder();
				sql.append("select * \n");
				sql.append("from user_info ui \n");
				sql.append("left outer join rent_user_info rui on ui.user_id =rui.rent_user_id");
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				ResultSet res = pstmt.executeQuery();
				
				while(res.next()) {
					RentUserInfo user = new RentUserInfo();
					user.setUserId(res.getString("user_id"));
					user.setUserName(res.getString("user_name"));
					user.setUserAge(res.getInt("user_age"));
					user.setUserPhone(res.getString("user_phone"));
					user.setUserLicenceNumber(res.getString("user_licence_number"));
					user.setRentCarId(res.getString("rent_car_id"));
					user.setRentPrice(res.getInt("rent_price"));
					user.setRentDays(res.getInt("rent_days"));
					user.setRenting(res.getString("rent_status"));
					list.add(user);
				}
				
			}		
			
			return list;
		}
	
	
	
	
}
