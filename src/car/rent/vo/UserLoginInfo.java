package car.rent.vo;

//로그인할때 데이터베이스에서 불러온 유저의 정보를 저장하는 객체
public class UserLoginInfo {
	private String id;
	private String name;
	private String passwd;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	

}
