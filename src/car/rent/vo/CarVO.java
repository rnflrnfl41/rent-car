package car.rent.vo;

//차량정보들을 가지는 객체
public class CarVO {

	private String carId;
	private String carName;
	private String carCompany;
	private int carYear;
	private String carEngine;
	private String displacement;
	private int carPrice;
	private int carCount;
	private String status;
	
	
	public int getCarPrice() {
		return carPrice;
	}
	public void setCarPrice(int carPrice) {
		this.carPrice = carPrice;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getDisplacement() {
		return displacement;
	}
	public void setDisplacement(String displacement) {
		this.displacement = displacement;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public String getCarCompany() {
		return carCompany;
	}
	public void setCarCompany(String carCompany) {
		this.carCompany = carCompany;
	}
	public int getCarYear() {
		return carYear;
	}
	public void setCarYear(int carYear) {
		this.carYear = carYear;
	}
	public String getCarEngine() {
		return carEngine;
	}
	public void setCarEngine(String carEngine) {
		this.carEngine = carEngine;
	}
	public int getCarCount() {
		return carCount;
	}
	public void setCarCount(int carCount) {
		this.carCount = carCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("차량번호: "+carId+"   제조사: "+carCompany+"   차종: "+carName+"   연식: "+carYear+"   엔진: "+carEngine+
				  "   배기량: "+displacement+"   1일렌트가격: "+carPrice+"   상태:"+status);
		
		return sb.toString();
	}
	
	
	
	
}
