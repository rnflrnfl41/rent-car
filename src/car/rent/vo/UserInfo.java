package car.rent.vo;


// 유저정보 저장할 객체
public class UserInfo {
	
	private String userId;
	private String passwd;
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
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
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
		sb.append("ID: "+userId+"   비밀번호: "+passwd+"   이름: "+userName+"   나이: "+userAge+"   핸드폰번호: "+userPhone+
				  "   운전면허번호: "+userLicenceNumber);
		
		
		return sb.toString();
	}

	

}
