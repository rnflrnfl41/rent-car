package car.rent.contrl;

import java.util.List;
import java.util.Scanner;

import car.rent.db.RentSqlDAO;
import car.rent.db.UserInfoSqlDAO;
import car.rent.utils.CartRentConstants;
import car.rent.vo.CarVO;
import car.rent.vo.InfoToRent;
import car.rent.vo.RentUserInfo;
import car.rent.vo.UserInfo;
import car.rent.vo.UserLoginInfo;


public class ManageClassControl {

	  private Scanner scan ;
	  private int inputNum;
	  private RentSqlDAO rootRentDao;
	  private UserInfoSqlDAO rootUserDao;
	  private String rootId = "root";
	  private String rootPasswd = "1234";

	  public ManageClassControl(){
		 scan = new Scanner(System.in);
		 this.rootUserDao = new UserInfoSqlDAO();
		 this.rootRentDao = new RentSqlDAO();
	 }
	  
	  //제일 처음 나오는 메인 창 회원가입 로그인 종료 3가지 기능을 넣음
	  //여기서 예외 던진걸 다 받아줌
	  public void mainDisplay(){
		  try {
			  System.out.println("=====  MAIN MENU  =====");
			  System.out.println("1. 회원가입 2. 로그인 3. 종료");
			  System.out.println("=======================");
			  System.out.print(">> ");
			  int menu = scan.nextInt();
			  if(menu==1) {
				  insertInfo();
				  System.out.println("회원가입완료!");
				  printLogin();
			  }else if(menu==2){
				  printLogin();  
			  }else if(menu==3){
				  System.out.println("프로그램 종료");
				  scan.close();
				  System.exit(0);  
			  }else {
				  System.out.println("잘못눌렀습니다");
			  }
			  
		} catch (Exception e) {
			e.printStackTrace();
		}
	  }
	  private String CurentUserId = ""; //현재 로그인한 사람의 id를 저장해두는 변수
	  
	  //로그인창 불러오는 메서드
	  public void printLogin() throws Exception {
		  List<UserLoginInfo> list = rootUserDao.loginInfo();
		  while(true) {
			  System.out.println("=====  LOGIN  =====");
			  System.out.print("아이디: ");
			  String id = scan.next();
			  System.out.print("비밀번호: ");
			  String passwd = scan.next();
			  //user_info 데이터 베이스에서 유저 아이디와 비밀번호를 읽어와서
			  //맞으면 로그인 해서 유저메뉴 실행 관리자 아이디로 로그인한다면 관리자메뉴 실행 
			  for(int i =0;i<list.size();i++) {
				  UserLoginInfo user = list.get(i);
				  if(id.equals(user.getId()) && passwd.equals(user.getPasswd())) {
					  CurentUserId = user.getId();
					  System.out.println("========환영합니다 "+list.get(i).getName()+"님========");
					  printUserMenu();
				  }else if(id.equals(rootId) && passwd.equals(rootPasswd)) {
					  printRootMenu();
				  }
			  }
			  System.out.println("잘못 입력했습니다.");
		  }
	  }
	  
	  // 관리자 메뉴 실행 메서드
	  public void printRootMenu() throws Exception{
		  System.out.println("============ROOT MENU=============");
		  System.out.println("1.고객관리   2.차량관리   3.로그아웃   4.종료");
		  System.out.println("==================================");
		  System.out.print("원하는 메뉴를 입력하시오   : ");
			 
		  inputNum  = scan.nextInt();
		  switch(inputNum){
		  case CartRentConstants.ROOT_MENU_CUS:
			  UserInfoMenu();
			  break; 
		  case CartRentConstants.ROOT_MENU_CAR:
			  CarInfoMenu();
			  break;
		  case CartRentConstants.ROOT_LOGOUT:
			  printLogin();
			  break;
		  case CartRentConstants.ROOT_EXIT:
			  System.out.println("프로그램 종료.");
			  scan.close();
			  System.exit(0);
	      default:
	    	  errorMessage(inputNum);
		  }
	  }
	  
	  //관리자메뉴중 고객관리 메뉴
	  public void UserInfoMenu() throws Exception{
		  System.out.println("=====================  USERINFO MENU  ========================");
		  System.out.println("1.고객등록   2.고객목록   3.고객검색   4.고객정보수정   5.고객정보삭제   6.메인메뉴");
		  System.out.println("==============================================================");
		  System.out.print("원하는 메뉴를 입력하시오   : ");
		  inputNum  = scan.nextInt();  	 
			
		  switch(inputNum){
		  case CartRentConstants.ROOT_CUS_INSERT:
			  this.insertInfo();
			  break;
		  case CartRentConstants.ROOT_CUS_LIST:
			  this.getRootUserInfo();
			  break; 
		  case CartRentConstants.ROOT_CUS_SEARCH:
			  this.SearchUserInfo();
			  break;
		  case CartRentConstants.ROOT_CUS_UPDATE:
			  this.UpdateInfo();
			  break;
		  case CartRentConstants.ROOT_CUS_DELETE:
			  this.DeleteInfo();
			  break;
		  case CartRentConstants.ROOT_RETURN_MAIN:
			  this.printRootMenu();
			  break;
		  default:
			  errorMessage(inputNum);	  
		  }			
		  printRootMenu();
	  }
	  
	  //관리자모드 차량관리모드 메서드
	  public void CarInfoMenu() throws Exception{
		  System.out.println("=====================  CAR INFO MENU  =========================");
		  System.out.println("1.차량등록   2.차량목록   3.차량검색   4.차량정보수정   5.차량정보삭제   6.메인메뉴");
		  System.out.println("===============================================================");
		  System.out.print("원하는 메뉴를 입력하시오   : ");
		  inputNum  = scan.nextInt();  
		  
		  switch(inputNum){
		  case CartRentConstants.ROOT_CAR_INSERT:
			  this.insertCarInfo();
			  break; 
		  case CartRentConstants.ROOT_CAR_LIST:
			  this.getCarInfo();
			  break; 
		  case CartRentConstants.ROOT_CAR_SEARCH:
			  this.SearchCarInfo();
			  break;
		  case CartRentConstants.ROOT_CAR_UPDATE:
			  this.UpdateCarInfo();
			  break;
		  case CartRentConstants.ROOT_CAR_DELETE:
			  this.DeleteCarInfo();
			  break;
		  case CartRentConstants.ROOT_RETURN_MAIN:
			  this.printRootMenu();
			  break;
		  default:
			  errorMessage(inputNum);	  
		  }		
		  printRootMenu();  
	  }
	  
	  //유저 메뉴 출력 메서드
	  public void printUserMenu() throws Exception{
		  while(true) {
			  System.out.println("==============  USER MENU  ===================");
			  System.out.println("1.차량대여   2.차량반납   3.개인정보수정   4.로그아웃   5.종료");
			  System.out.println("==============================================");
			  System.out.print("원하는 메뉴를 입력하시오   : ");
			  inputNum  = scan.nextInt();  // 메뉴 번호 입력
			  switch(inputNum){
			  case CartRentConstants.CUS_RENT_CAR:
				  this.rentCar();
				  break; 
			  case CartRentConstants.CUS_RETURN_CAR:
				  this.returnCar();
				  break;
			  case CartRentConstants.CUS_UPDATE_INFO:
				  UpdateUserInfo();
				  break;
			  case CartRentConstants.CUS_LOGOUT:
				  printLogin();
				  break;
			  case CartRentConstants.CUS_EXIT:
				  System.out.println("프로그램 종료.");
				  scan.close();
				  System.exit(0);
			  default:
				  errorMessage(inputNum);	  
			  }
		  }
	  }
	  
	  ////////////에러 메세지 ///////////////////////////////////
	  private  void  errorMessage(int errorMenu){			
		  String errorLine = errorMenu + " 번은 메뉴에 없습니다.다시한번 정확한 입력 바랍니다!";
		  System.out.println();
		  System.out.println(errorLine);
		  System.out.println();
	  }
	  
	  //고객정보 입력 메서드
	  public void insertInfo() throws Exception{
			System.out.print("ID >> ");
			String userId = scan.next();
			System.out.print("비밀번호 >> ");
			String passwd = scan.next();
			System.out.print("이름 >> ");
			String userName = scan.next();
			System.out.print("나이 >> ");
			int userAge = scan.nextInt();
			System.out.print("핸드폰번호 >> ");
			String userPhone = scan.next();
			System.out.print("운전면허 번호 >> ");
			String userLicenceNumber = scan.next();
			rootUserDao.insertUserInfo(userId, userName, passwd, userAge, userPhone, userLicenceNumber);
			//String userId,String userName,int userAge,String userPhone,String userLicenceNumber
		}
	  
	  
	  //고객정보 출력 메서드
	  public void getUserInfo() throws Exception{
		  List<UserInfo> list = rootUserDao.getAllInfo();
		  
		  if(list.isEmpty() || list==null) {
			  System.out.println("데이터가 없습니다.");
		  }else {
			  System.out.println("=============================  고객정보출력  =============================");
			  for(UserInfo ui : list) {
				  System.out.println(ui);
			  }
			  System.out.println("=====================================================================");
		  }
	  }
	  //관리자 고객정보 출력 메서드
	  public void getRootUserInfo() throws Exception{
		  List<RentUserInfo> list = rootUserDao.rootGetAllInfo();
		  
		  if(list.isEmpty() || list==null) {
			  System.out.println("데이터가 없습니다.");
		  }else {
			  System.out.println("=============================  고객정보출력  =============================");
			  for(RentUserInfo ui : list) {
				  System.out.println(ui);
			  }
			  System.out.println("=====================================================================");
		  }
	  }
	  
	  //고객정보 검색 메서드
	  public void SearchUserInfo() throws Exception{
		  System.out.print("검색할 고객이름을 입력하세요 >> ");
		  String name = scan.next();
		  List<UserInfo> list = rootUserDao.searchUserInfo(name);
		  if(list==null || list.isEmpty()) {
			  System.out.println("데이터가 없습니다.");
		  }else {
			  System.out.println("=============================  고객정보출력  =============================");
			  for(UserInfo ui: list) {
				  System.out.println(ui);
			  }
			  System.out.println("=====================================================================");
		  }
		  
	  }
	  
	  //고객정보 수정 메서드
	  public void UpdateInfo() throws Exception{
		  System.out.println("수정할 고객 이름을 입력하세요 >> ");
		  String name = scan.next();
		  System.out.print("ID >> ");
		  String userId = scan.next();
		  System.out.print("비밀번호 >> ");
		  String passwd = scan.next();
		  System.out.print("나이 >> ");
		  int userAge = scan.nextInt();
		  System.out.print("핸드폰번호 >> ");
		  String userPhone = scan.next();
		  System.out.print("운전면허 번호 >> ");
		  String userLicenceNumber = scan.next();
		  rootUserDao.UpdateInfo(name, passwd, userId, userAge, userPhone, userLicenceNumber);
		  
	  } 
	  
	//고객 개인정보 수정 메서드
	  public void UpdateUserInfo() throws Exception{
		  System.out.println("이름 >> ");
		  String name = scan.next();
		  System.out.print("비밀번호 >> ");
		  String passwd = scan.next();
		  System.out.print("나이 >> ");
		  int userAge = scan.nextInt();
		  System.out.print("핸드폰번호 >> ");
		  String userPhone = scan.next();
		  System.out.print("운전면허 번호 >> ");
		  String userLicenceNumber = scan.next();
		  rootUserDao.UpdateUserInfo(name, passwd, CurentUserId, userAge, userPhone, userLicenceNumber);
		  
	  }
	  
	  //고객정보 삭제 메서드
	  public void DeleteInfo() throws Exception {
		  System.out.println("삭제할 고객 이름을 입력하세요 >> ");
		  String name = scan.next();
		  rootUserDao.deleteInfo(name);
	  }
	  
	//차량정보 입력 메서드
	  public void insertCarInfo() throws Exception{
			System.out.print("차량번호 >> ");
			String carId = scan.next();
			System.out.print("제조사 >> ");
			String carCompany = scan.next();
			System.out.print("차종 >> ");
			String carName = scan.next();
			System.out.print("연식 >> ");
			int carYear = scan.nextInt();
			System.out.print("엔진 >> ");
			String carEngine = scan.next();
			System.out.print("배기량 >> ");
			String displacement = scan.next();
			System.out.print("1일 렌트가격 >> ");
			int carPrice = scan.nextInt();
			rootRentDao.insertCarInfo(carId, carCompany, carName, carYear, carEngine, displacement,carPrice);
			//String userId,String userName,int userAge,String userPhone,String userLicenceNumber
		}
	  
	//차량정보 출력 메서드
	  public void getCarInfo() throws Exception{
		  List<CarVO> list = rootRentDao.getAllInfo();
		  
		  if(list.isEmpty() || list==null) {
			  System.out.println("데이터가 없습니다.");
		  }else {
			  System.out.println("=========================================  차량정보출력  =========================================");
			  for(CarVO ci : list) {
				  System.out.println(ci);
			  }
			  System.out.println("=============================================================================================");
		  }
	  }
	  
	//차량정보 검색 메서드
	  public void SearchCarInfo() throws Exception{
		  System.out.print("검색할 차종을 입력하세요 >> ");
		  String id = scan.next();
		  List<CarVO> list = rootRentDao.searchCarInfo(id);
		  if(list==null || list.isEmpty()) {
			  System.out.println("데이터가 없습니다.");
		  }else {
			  System.out.println("=============================  차량정보출력  =============================");
			  for(CarVO ci: list) {
				  System.out.println(ci);
			  }
			  System.out.println("=====================================================================");
		  }
		  
	  }
	  
	//차량정보 수정 메서드
	  public void UpdateCarInfo() throws Exception{
		  System.out.println("수정할 차량번호를 입력하세요 >> ");
		  String carId = scan.next();
		  System.out.print("제조사 >> ");
		  String carCompany = scan.next();
		  System.out.print("차종 >> ");
		  String carName = scan.next();
		  System.out.print("연식 >> ");
		  int carYear = scan.nextInt();
		  System.out.print("엔진형식 >> ");
		  String carEngine = scan.next();
		  System.out.print("배기량 >> ");
		  String displacement = scan.next();
		  System.out.print("1일 렌트가격 >> ");
		  int carPrice = scan.nextInt();
		  rootRentDao.UpdateInfo(carId, carCompany, carName, carYear, carEngine, displacement, carPrice);
		  
	  }
	  
	//차량정보 삭제 메서드
	  public void DeleteCarInfo() throws Exception {
		  System.out.println("삭제할 차량번호를 입력하세요 >> ");
		  String id = scan.next();
		  rootRentDao.deleteInfo(id);
	  }
	  
	  //유저가 차 빌리는 메서드
	  public void rentCar() throws Exception {
		  List<CarVO> list = rootRentDao.getAllInfo();
		  
		  if(list.isEmpty() || list==null) {
			  System.out.println("데이터가 없습니다.");
		  }else {
			  System.out.println("====================================  차량정보출력  ====================================");
			  for(CarVO ci : list) {
				  System.out.println(ci);
			  }
			  System.out.println("===================================================================================");
		  }
		  System.out.print("대여할 차량의 차량번호를 입력하세요: ");
		  String carId = scan.next();
		  if(rootRentDao.isCarRenting(carId)) { //이미 대여중인 차량을 입력했을 시
			  System.out.println("대여불가. 이미 대여중인 차량입니다.");
		  }else {
			  System.out.print("대여 일수를 입력하세요: ");
			  int days = scan.nextInt();
			  int price = (rootRentDao.getCarPrice(carId)*days);
			  rootRentDao.UserRentCar(CurentUserId, carId, days, price);
			  rootRentDao.SetCarRentStatus(carId);
		  }
	  }
	  
	  //유저 차반납 메서드
	  public void returnCar() throws Exception {
		      List<InfoToRent> list = rootRentDao.infoRentUser(CurentUserId);
		      if(list.isEmpty()) {
		    	  System.out.println("대여한 차량이 없습니다.");
		    	  this.printUserMenu();
		      }
			  System.out.println("==========================  대여 정보 출력  ==========================");
			  for(InfoToRent itr : list) {
				  System.out.println(itr);
			  }
			  System.out.println("=================================================================");
			  System.out.print("반납할 차량번호를 입력하세요>> ");
			  String carId = scan.next();
			  rootRentDao.returnCar(carId);
			  rootRentDao.SetCarDoneStatus(carId);
	  }
	  
	  
	 
}
