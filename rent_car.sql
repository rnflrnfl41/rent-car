create table user_info(

user_id varchar(100) not null comment '아이디',
user_name varchar(100) not null comment '이름',
user_age int not null comment '나이',
user_phone varchar(100) not null comment '전화번호',
user_licence_number varchar(100) not null comment '운전면허 번호',
user_password varchar(100) not null comment '비밀번호'
primary key (user_id)
);
,
create table rent_user_info (

rent_seq int auto_increment not null,
rent_user_id varchar(100) not null ,
rent_car_id varchar(100) not null ,
rent_days int not null,
rent_price int not null comment '총렌트비용',
rent_status varchar(50) comment '랜트상태, 대여중/반납완료',
primary key (rent_seq),
foreign key (rent_user_id) references user_info(user_id),
foreign key (rent_car_id) references car_info(car_id)

);

create table car_info(

car_id varchar(100) not null comment '차량번호',
car_company varchar(100) not null comment '제조사',
car_name varchar(100) not null comment '차종',
car_year int not null comment '연식',
car_engine varchar(100) not null comment '가솔린/디젤/하이브리드/LPG/전기/수소',
displacement varchar(100) not null comment '배기량',
car_price int comment not null '1일 렌트비용',
car_status varchar(50) default '대여가능' comment '대여가능/대여불가' 
primary key (car_id)

)