select * from plan;
--plan_schedule DML    plan_id = 13 서울여행2
insert into plan_schedule values((select plan_start_day from plan where plan_id = 13) ,13,'10:00','22:00' );
insert into plan_schedule values((select plan_start_day+1 from plan where plan_id = 13) ,13,'10:00','22:00'  );
insert into plan_schedule values((select plan_start_day+2 from plan where plan_id = 13) ,13,'10:00','22:00'  );

--서울 일식점 리스트 1-10
select * from place where areacode = 1 and CONTENTTYPEID='39' and cat3='A05020300' order by contentid asc;

----plan_spot
--1일차
insert into plan_spot values((select plan_start_day from plan where plan_id = 13),13,4, 133328,1,default,null);
insert into plan_spot values((select plan_start_day from plan where plan_id = 13),13,4, 134686,2,default,null);
insert into plan_spot values((select plan_start_day from plan where plan_id = 13),13,4, 134691,3,default,null);
--2일차
insert into plan_spot values((select plan_start_day+1 from plan where plan_id = 13),13,4, 135423,1,default,null);
insert into plan_spot values((select plan_start_day+1 from plan where plan_id = 13),13,4, 135445,2,default,null);
insert into plan_spot values((select plan_start_day+1 from plan where plan_id = 13),13,4, 398339,3,default,null);
insert into plan_spot values((select plan_start_day+1 from plan where plan_id = 13),13,4, 838949,4,default,null);
--3일차
insert into plan_spot values((select plan_start_day+2 from plan where plan_id = 13),13,4, 1062825,1,default,null);
insert into plan_spot values((select plan_start_day+2 from plan where plan_id = 13),13,4, 1393694,2,default,null);
insert into plan_spot values((select plan_start_day+2 from plan where plan_id = 13),13,4, 1515926,3,default,null);

--서울 숙소(한옥) 리스트
select * from place where areacode = 1 and CONTENTTYPEID='32' and cat3='B02011600' order by contentid asc;

----plan_stay
--1일차
insert into plan_stay values((select plan_start_day from plan where plan_id = 13),13,1868919,5);
--2일차
insert into plan_stay values((select plan_start_day+1 from plan where plan_id = 13),13,1870779,5);


-----------------
--plan_schedule DML    plan_id = 14 서울여행 세번째
insert into plan_schedule values((select plan_start_day from plan where plan_id = 14) ,14,'10:00','22:00' );
insert into plan_schedule values((select plan_start_day+1 from plan where plan_id = 14) ,14,'10:00','22:00'  );
insert into plan_schedule values((select plan_start_day+2 from plan where plan_id = 14) ,14,'10:00','22:00'  );

----plan_spot
--1일차
insert into plan_spot values((select plan_start_day from plan where plan_id = 14),14,4, 133328,1,default,null);
insert into plan_spot values((select plan_start_day from plan where plan_id = 14),14,4, 134686,2,default,null);
insert into plan_spot values((select plan_start_day from plan where plan_id = 14),14,4, 134691,3,default,null);
--2일차
insert into plan_spot values((select plan_start_day+1 from plan where plan_id = 14),14,4, 135423,1,default,null);
insert into plan_spot values((select plan_start_day+1 from plan where plan_id = 14),14,4, 135445,2,default,null);
insert into plan_spot values((select plan_start_day+1 from plan where plan_id = 14),14,4, 398339,3,default,null);
insert into plan_spot values((select plan_start_day+1 from plan where plan_id = 14),14,4, 838949,4,default,null);
--3일차
insert into plan_spot values((select plan_start_day+2 from plan where plan_id = 14),14,4, 1062825,1,default,null);
insert into plan_spot values((select plan_start_day+2 from plan where plan_id = 14),14,4, 1393694,2,default,null);
insert into plan_spot values((select plan_start_day+2 from plan where plan_id = 14),14,4, 1515926,3,default,null);
insert into plan_spot values((select plan_start_day+2 from plan where plan_id = 14),14,4, 133328,4,default,null);
insert into plan_spot values((select plan_start_day+2 from plan where plan_id = 14),14,4, 398339,5,default,null);

----plan_stay
--1일차
insert into plan_stay values((select plan_start_day from plan where plan_id = 14),14,1868919,5);
--2일차
insert into plan_stay values((select plan_start_day+1 from plan where plan_id = 14),14,1870779,5);
-----------------
----etc.
select * from plan_stay;
select * from plan_spot;

--컬럼 데이터타입 변경 DATE -> VARCHAR2
alter table plan_schedule modify SCHEDULE_START VARCHAR2(20);
alter table plan_schedule modify SCHEDULE_END VARCHAR2(20);

commit;

