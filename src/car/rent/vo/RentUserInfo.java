package car.rent.vo;

//rent_user_info를 바탕으로 렌트를 했을때 데이터베이스에 저장하고, 
//관리자가 유저목록을 조회했을때 차를 빌려가있는 상태일시 사용
public class RentUserInfo {
	private String userId;
	private String rentCarId;
	private int rentDays;
	private int rentPrice;
	private String renting;
	private String userName;
	private int userAge;
	private String userPhone;
	private String userLicenceNumber;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRentCarId() {
		return rentCarId;
	}
	public void setRentCarId(String rentCarId) {
		this.rentCarId = rentCarId;
	}
	public int getRentDays() {
		return rentDays;
	}
	public void setRentDays(int rentDays) {
		this.rentDays = rentDays;
	}
	public int getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(int rentPrice) {
		this.rentPrice = rentPrice;
	}
	public String getRenting() {
		return renting;
	}
	public void setRenting(String renting) {
		this.renting = renting;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserLicenceNumber() {
		return userLicenceNumber;
	}
	public void setUserLicenceNumber(String userLicenceNumber) {
		this.userLicenceNumber = userLicenceNumber;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ID: "+userId+"   이름: "+userName+"   나이: "+userAge+"   핸드폰번호: "+userPhone+
				  "   운전면허번호: "+userLicenceNumber+"\n");
		if(renting!=null) {
			sb.append("렌트여부: "+renting+"   차량번호: "+rentCarId+"   렌트일수: "+rentDays+"   렌트가격: "+rentPrice+ "\n");
		}else {
			renting = "X";
			sb.append("렌트여부: "+renting+"\n");
		}
		sb.append("=====================================================================");
		
		return sb.toString();
	}

}
