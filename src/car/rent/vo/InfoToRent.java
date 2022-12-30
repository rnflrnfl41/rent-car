package car.rent.vo;


// 차량을 반납할 때 유저가 렌트해간 차량의 간략정보를 담는 객체
public class InfoToRent {
	private String rentCarId;
	private int rentDays;
	private int rentPrice;
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
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("차량번호: "+rentCarId+"   렌트일수: "+rentDays+"   렌트가격: "+rentPrice);
		
		return sb.toString();
	}

}
