package car.rent.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import car.rent.vo.CarVO;
import car.rent.vo.InfoToRent;


//차량에 관련된 sql쿼리 메서드 모아둠
public class RentSqlDAO {
	
	
	//차량 정보 입력 메서드
	public void insertCarInfo(String carId,String carCompany, String carName,
			                   int carYear,String carEngine,
			                   String displacement,int price) throws Exception{
		Connection conn = DBConnection.getConnection();
		
		if(conn != null) {
			
			conn.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("insert car_info(car_id, car_company, car_name, car_year, car_engine, displacement, car_price) \n");
			sql.append("values(?,?,?,?,?,?,?)");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			int valueIndex=1;
			pstmt.setString(valueIndex++, carId);
			pstmt.setString(valueIndex++, carCompany);
			pstmt.setString(valueIndex++, carName);
			pstmt.setInt(valueIndex++, carYear);
			pstmt.setString(valueIndex++, carEngine);
			pstmt.setString(valueIndex++, displacement);
			pstmt.setInt(valueIndex++, price);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
				System.out.println("차량정보가 등록되었습니다.");
			}else {
				conn.rollback();
				System.out.println("차량정보 등록이 실패했습니다.");
			}
			
			
		}
	}
	
	//차량 정보 출력 메서드
	public List<CarVO> getAllInfo() throws Exception{
		Connection conn = DBConnection.getConnection();
		List<CarVO> list = new ArrayList<>();
		
		if(conn != null) {
			StringBuilder sql = new StringBuilder();
			sql.append("select *");
			sql.append("from car_info");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				CarVO car = new CarVO();
				car.setCarId(res.getString("car_id"));
				car.setCarCompany(res.getString("car_company"));
				car.setCarName(res.getString("car_name"));
				car.setCarYear(res.getInt("car_year"));
				car.setCarEngine(res.getString("car_engine"));
				car.setDisplacement(res.getString("displacement"));
				car.setCarPrice(res.getInt("car_price"));
				car.setStatus(res.getString("car_status"));
				list.add(car);
			}
			
		}		
		
		return list;
	}
	
	
	//차량 정보 검색 메서드
	public List<CarVO> searchCarInfo(String name) throws Exception{
		Connection conn = DBConnection.getConnection();
		List<CarVO> list = new ArrayList<>();
		
		if(conn != null) {
			StringBuilder sql = new StringBuilder();
			sql.append("select * \n");
			sql.append("from car_info ci \n");
			sql.append("where ci.car_name like '%"+name+"%'");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet res = pstmt.executeQuery();
			
			if(res!=null) {
				while(res.next()) {
					CarVO car = new CarVO();
					car.setCarId(res.getString("car_id"));
					car.setCarName(res.getString("car_name"));
					car.setCarCompany(res.getString("car_company"));
					car.setCarYear(res.getInt("car_year"));
					car.setCarEngine(res.getString("car_engine"));
					car.setDisplacement(res.getString("displacement"));
					car.setCarPrice(res.getInt("car_price"));
					list.add(car);
					
				}
			}else {
				System.out.println("잘못입력했습니다.");
			}
		}
		return list;
	}
	
	//차량정보 수정 메서드
	public void UpdateInfo(String carId,String carCompany, String carName,
                           int carYear,String carEngine,
                           String displacement, int carPrice) throws Exception{
		Connection conn = DBConnection.getConnection();
		
		if(conn!=null) {
			conn.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("update car_info  \n");
			sql.append("set car_name = ?, car_company = ?, car_year = ?, car_Engine = ?, displacement= ?, car_price= ? \n");
			sql.append("where car_id like ? ");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			int indexVal = 1;
			pstmt.setString(indexVal++, carName);
			pstmt.setString(indexVal++, carCompany);
			pstmt.setInt(indexVal++, carYear);
			pstmt.setString(indexVal++, carEngine);
			pstmt.setString(indexVal++, displacement);
			pstmt.setInt(indexVal++, carPrice);
			pstmt.setString(indexVal++, carId);
			
			int result = pstmt.executeUpdate();
			
			if(result>0) {
				conn.commit();
				System.out.println("차량정보 수정 완료!");
			}else {
				conn.rollback();
				System.out.println("차량정보 수정 실패.");
			}		
		}
	}
	
	//차량정보 삭제 메서드
	public void deleteInfo(String id) throws Exception{
		Connection conn = DBConnection.getConnection();
		
		if(conn != null) {
			StringBuilder sql = new StringBuilder();
			conn.setAutoCommit(false);
			
			sql.append("delete from car_info \n");
			sql.append("where car_id like '"+id+"'");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
				System.out.println("차량정보가 정상적으로 삭제되었습니다!");
				
			}else {
				conn.rollback();
				System.out.println("차량정보 삭제실패.");
			}
		}
	}
	
	//차의 가격을 불러오는 메서드(유저가 차를 렌트할때 기간을 입력받고 총렌트비용이 얼마드는지 구하기위해 쓰임 )
	public int getCarPrice(String id) throws Exception{
		Connection conn = DBConnection.getConnection();
		int price = 0;
		if(conn != null) {
			StringBuilder sql = new StringBuilder();
			sql.append("select car_price \n");
			sql.append("from car_info ci \n");
			sql.append("where ci.car_id like '%"+id+"%'");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet res = pstmt.executeQuery();

			while(res.next()) {
				price = res.getInt("car_price");
			}
		}
		return price;
		
	}
	
	//차량이 대여중이라면 car_info에 car_status 대여불가로 바꿔주는 메서드(대여중인 차는 빌리지 못하게 만들때 사용)
	public void SetCarRentStatus(String carId) throws Exception {
		Connection conn = DBConnection.getConnection();
		
		if(conn!=null) {
			StringBuilder sql = new StringBuilder();
			sql.append("update car_info \n");
			sql.append("set car_status = '대여불가' \n");
			sql.append("where car_id like '"+carId+"'");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
				
			}else {
				conn.rollback();
			}
		}
		
	}
	
	//차량을 빌려간 유저가 반납을 완료했다면 car_info 데이터베이스에 car_staus를 대여가능으로 바꿔줌  
	public void SetCarDoneStatus(String carId) throws Exception {
		Connection conn = DBConnection.getConnection();
		
		if(conn!=null) {
			StringBuilder sql = new StringBuilder();
			sql.append("update car_info \n");
			sql.append("set car_status = '대여가능' \n");
			sql.append("where car_id like '"+carId+"'");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
				
			}else {
				conn.rollback();
			}
		}
		
	}
	
	// 유저가 차를 빌리는 메서드(차를 빌려가면 rent_user_info에 저장 rent_status 대여중으로 전환)
	public void UserRentCar(String userId, String carId, int days, int price) throws Exception{
		Connection conn = DBConnection.getConnection();
		String status = "대여중";
		if(conn != null) {
			StringBuilder sql = new StringBuilder();
			sql.append("insert rent_user_info(rent_user_id, rent_car_id, rent_days, rent_price, rent_status) \n");
			sql.append("values(?,?,?,?,?) \n");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			int valueIndex=1;
			pstmt.setString(valueIndex++, userId);
			pstmt.setString(valueIndex++, carId);
			pstmt.setInt(valueIndex++, days);
			pstmt.setInt(valueIndex++, price);
			pstmt.setString(valueIndex++, status);
			
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
				System.out.println("차량대여 성공!");
			}else {
				conn.rollback();
				System.out.println("차량대여 실패.");
			}
		}
		
	}
	
	//차량이 대여중인 상태라면 true를 반환하는 메서드(차량이 대여중인 상태라면 다른사람이 빌리지 못하게 만드는 메서드를 만들기위해 쓰임)
	public boolean isCarRenting(String id) throws Exception{
		Connection conn = DBConnection.getConnection();
		boolean exist= false;
		if(conn != null) {
			StringBuilder sql = new StringBuilder();
			sql.append("select * \n");
			sql.append("from rent_user_info \n");
			sql.append("where rent_car_id like '"+id+"'");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet res = pstmt.executeQuery();
			
			if(res!=null) {
				exist = true;
			}
		}
		return exist;
	}
	
	// 차량을 반납할 때 유저가 렌트해간 차량의 간략정보를 가져오는 메서드
	public List<InfoToRent> infoRentUser(String id) throws Exception{
		Connection conn = DBConnection.getConnection();
		List<InfoToRent> list = new ArrayList<>();
		if(conn != null) {
			StringBuilder sql = new StringBuilder();
			sql.append("select rent_car_id, rent_days, rent_price \n");
			sql.append("from rent_user_info rui \n");
			sql.append("where rui.rent_user_id like '"+id+"'");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet res = pstmt.executeQuery();
			
			if(res!=null) {
				while(res.next()) {
					InfoToRent ir = new InfoToRent();
					ir.setRentCarId(res.getString("rent_car_id"));
					ir.setRentDays(res.getInt("rent_days"));
					ir.setRentPrice(res.getInt("rent_price"));
					list.add(ir);
				}
			}else {
				System.out.println("ERROR");
			}
			
		}
		return list;
	}
	
	//유저 차 반납 메서드
	public void returnCar(String id) throws Exception{
		Connection conn = DBConnection.getConnection();
		
		if(conn != null) {
			conn.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("delete from rent_user_info \n");
			sql.append("where rent_car_id like '"+id+"'");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
				System.out.println("차량 반납 완료!");
				
			}else {
				conn.rollback();
				System.out.println("차량 반납 실패.");
			}
		}
		
	}
	
	
	
	
}
