select * from plan;

select * from detailInfo where plan_id=13 
order by travel_date asc
;
select  title from place where contentid = 1870779;
desc place;
select title, contentid,CONTENTTYPEID,type  from place where contentid >= 100000 and areacode = 1 and contenttypeid=32  or cat3 = 'A03021700' order by contentid desc;

SELECT SUBSTR((select  title from place where contentid = 133328), 1, 
              LEAST(
                NVL(NULLIF(INSTR((select  title from place where contentid = 133328), '('), 0), LENGTH((select  title from place where contentid = 133328)) + 1) - 1, 
                NVL(NULLIF(INSTR((select  title from place where contentid = 133328), '['), 0), LENGTH((select  title from place where contentid = 133328)) + 1) - 1
              )
             ) 
FROM place;
------------------------------------------------------------------
--plan DML
insert into plan values(10000,1,'개미와 함께하는 써울탐방기',sysdate+10, sysdate+12,sysdate,null);
insert into plan values(9999,1,'단잠루피와 함께하는 서울나들이',sysdate+20, sysdate+22,sysdate,null);
insert into plan values(8888,39,'군침루피와 눈뜨고 코베이는 제주여행',sysdate+15, sysdate+18,sysdate,null);
--plan_member DML
--plan_member의 첫번째 값 : plan의 plan_id 값

-- 공유 유저 DML
insert into plan_member values (8888,'gyrua34@gmail.com','0');
insert into plan_member values (10000,'gyrua34@gmail.com','1');
insert into plan_member values (9999,'gyrua34@gmail.com','1');

insert into plan_member values (10000,'qothwls5@naver.com','0');
insert into plan_member values (9999,'qothwls5@naver.com','0');

insert into plan_member values (9999,'dpdls898@naver.com','0');
insert into plan_member values (9999,'bomin1107@naver.com','0');

insert into plan_member values (8888,'dpdls898@naver.com','0');
insert into plan_member values (8888,'bomin1107@naver.com','0');
insert into plan_member values (8888,'seojw0730@naver.com','1');
select * from plan where plan_id = 11;

--UPDATE plan_member SET plan_mem_role = 1 where mem_email='gyrua34@gmail.com' and plan_id = 13;
--UPDATE plan_member SET plan_mem_role = 0 where mem_email='qothwls5@naver.com' and plan_id = 13;

--plan_schedule DML    plan_id = 9999 서울여행2
insert into plan_schedule values((select plan_start_day from plan where plan_id = 9999) ,9999,'10:00','22:00' );
insert into plan_schedule values((select plan_start_day+1 from plan where plan_id = 9999) ,9999,'10:00','22:00'  );
insert into plan_schedule values((select plan_start_day+2 from plan where plan_id = 9999) ,9999,'10:00','22:00'  );
--plan_schedule DML    plan_id = 10000 서울
insert into plan_schedule values((select plan_start_day from plan where plan_id = 10000) ,10000,'11:00','23:00' );
insert into plan_schedule values((select plan_start_day+1 from plan where plan_id = 10000) ,10000,'10:00','21:00'  );
insert into plan_schedule values((select plan_start_day+2 from plan where plan_id = 10000) ,10000,'11:00','20:00'  );
--plan_schedule DML    plan_id = 8888 제주여행2
insert into plan_schedule values((select plan_start_day from plan where plan_id = 8888) ,8888,'11:00','22:00' );
insert into plan_schedule values((select plan_start_day+1 from plan where plan_id = 8888) ,8888,'11:00','23:00'  );
insert into plan_schedule values((select plan_start_day+2 from plan where plan_id = 8888) ,8888,'10:00','23:00'  );
insert into plan_schedule values((select plan_start_day+3 from plan where plan_id = 8888) ,8888,'10:00','18:00'  );

--서울 일식점 리스트 1-10
select * from place where areacode = 1 and CONTENTTYPEID='39' and cat3='A05020300' order by contentid asc;

----plan_spot
--1일차
insert into plan_spot values((select plan_start_day from plan where plan_id = 9999),9999,4, 133328,1,default,null);
insert into plan_spot values((select plan_start_day from plan where plan_id = 9999),9999,4, 134686,2,default,null);
insert into plan_spot values((select plan_start_day from plan where plan_id = 9999),9999,4, 134691,3,default,null);
insert into plan_spot values((select plan_start_day from plan where plan_id = 9999),9999,5, 1870779,4,3600,null);
--2일차
insert into plan_spot values((select plan_start_day+1 from plan where plan_id = 9999),9999,4, 135423,1,default,null);
insert into plan_spot values((select plan_start_day+1 from plan where plan_id = 9999),9999,4, 135445,2,default,null);
insert into plan_spot values((select plan_start_day+1 from plan where plan_id = 9999),9999,4, 398339,3,default,null);
insert into plan_spot values((select plan_start_day+1 from plan where plan_id = 9999),9999,4, 838949,4,default,null);
insert into plan_spot values((select plan_start_day+1  from plan where plan_id = 9999),9999,5, 1870779,5,10800,null);

--3일차
insert into plan_spot values((select plan_start_day+2 from plan where plan_id = 9999),9999,4, 1062825,1,default,null);
insert into plan_spot values((select plan_start_day+2 from plan where plan_id = 9999),9999,4, 1393694,2,default,null);
insert into plan_spot values((select plan_start_day+2 from plan where plan_id = 9999),9999,4, 1515926,3,default,null);

--서울 숙소(한옥) 리스트
--select * from place where areacode = 1 and CONTENTTYPEID='32' and cat3='B02011600' order by contentid asc;

----plan_stay
--1일차
--insert into plan_stay values((select plan_start_day from plan where plan_id = 13),13,1868919,5);
--2일차
--insert into plan_stay values((select plan_start_day+1 from plan where plan_id = 13),13,1870779,5);

--memo DML 추가하기
update plan_spot set SPOT_MEMO = '모든 국민은 법률이 정하는 바에 의하여 국방의 의무를 진다. 법관은 헌법과 법률에 의하여 그 양심에 따라 독립하여 심판한다.' where spot_contentid=134686;
update plan_spot set SPOT_MEMO = '법관은 탄핵 또는 금고 이상의 형의 선고에 의하지 아니하고는 파면되지 아니하며, 징계처분에 의하지 아니하고는 정직·감봉 기타 불리한 처분을 받지 아니한다.' where spot_contentid=134691;
update plan_spot set SPOT_MEMO = '대통령은 헌법과 법률이 정하는 바에 의하여 국군을 통수한다.' where spot_contentid=398339;

------------------------------------------------------------------
--plan_schedule DML    plan_id = 10000 서울여행 세번째
insert into plan_schedule values((select plan_start_day from plan where plan_id = 10000) ,10000,'10:00','22:00' );
insert into plan_schedule values((select plan_start_day+1 from plan where plan_id = 10000) ,10000,'10:00','22:00'  );
insert into plan_schedule values((select plan_start_day+2 from plan where plan_id = 10000) ,10000,'10:00','22:00'  );

----plan_spot
--1일차
insert into plan_spot values((select plan_start_day from plan where plan_id = 10000),10000,4, 133328,1,default,null);
insert into plan_spot values((select plan_start_day from plan where plan_id = 10000),10000,4, 134686,2,default,null);
insert into plan_spot values((select plan_start_day from plan where plan_id = 10000),10000,4, 134691,3,default,null);
insert into plan_spot values((select plan_start_day from plan where plan_id = 10000),10000,5, 1870779,4,default,null);

--2일차
insert into plan_spot values((select plan_start_day+1 from plan where plan_id = 10000),10000,4, 135423,1,default,null);
insert into plan_spot values((select plan_start_day+1 from plan where plan_id = 10000),10000,4, 135445,2,default,null);
insert into plan_spot values((select plan_start_day+1 from plan where plan_id = 10000),10000,4, 398339,3,default,null);
insert into plan_spot values((select plan_start_day+1 from plan where plan_id = 10000),10000,4, 838949,4,default,null);
insert into plan_spot values((select plan_start_day+1 from plan where plan_id = 10000),10000,5, 1870779,5,default,null);
--3일차
insert into plan_spot values((select plan_start_day+2 from plan where plan_id = 10000),10000,4, 1062825,1,default,null);
insert into plan_spot values((select plan_start_day+2 from plan where plan_id = 10000),10000,4, 1393694,2,default,null);
insert into plan_spot values((select plan_start_day+2 from plan where plan_id = 10000),10000,4, 1515926,3,default,null);
insert into plan_spot values((select plan_start_day+2 from plan where plan_id = 10000),10000,4, 133328,4,default,null);
insert into plan_spot values((select plan_start_day+2 from plan where plan_id = 10000),10000,4, 398339,5,default,null);

------plan_stay
----1일차
--insert into plan_stay values((select plan_start_day from plan where plan_id = 14),14,1868919,5);
----2일차
--insert into plan_stay values((select plan_start_day+1 from plan where plan_id = 14),14,1870779,5);
------------------------------------------------------------------
----etc.

select * from plan_spot;

--컬럼 데이터타입 변경 DATE -> VARCHAR2
--alter table plan_schedule modify SCHEDULE_START VARCHAR2(20);
--alter table plan_schedule modify SCHEDULE_END VARCHAR2(20);
select mem_email from member;
commit;
insert into plan_member values (8888,'admin9@tripant.store','1');
--plan_schedule DML    plan_id = 14 서울여행 세번째

insert into plan_schedule values((select plan_start_day from plan where plan_id = 8888) ,8888,'10:00','22:00' );
insert into plan_schedule values((select plan_start_day+1 from plan where plan_id = 8888) ,8888,'10:00','22:00'  );
insert into plan_schedule values((select plan_start_day+2 from plan where plan_id = 8888) ,8888,'10:00','22:00'  );

select* from place where type =3 and areacode = 39;
----plan_spot3085304
--1일차
insert into plan_spot values((select plan_start_day from plan where plan_id = 8888),8888,4, 2758900,1,default,null);
insert into plan_spot values((select plan_start_day from plan where plan_id = 8888),8888,4, 1986683,2,default,null);
insert into plan_spot values((select plan_start_day from plan where plan_id = 8888),8888,4, 2654138,3,default,null);
insert into plan_spot values((select plan_start_day from plan where plan_id = 8888),8888,5, 905600,4,default,null);
--2일차
insert into plan_spot values((select plan_start_day+1 from plan where plan_id = 8888),8888,4, 133990,1,default,null);
insert into plan_spot values((select plan_start_day+1 from plan where plan_id = 8888),8888,4, 2905771,2,default,null);
insert into plan_spot values((select plan_start_day+1 from plan where plan_id = 8888),8888,4, 3037623,3,default,null);
insert into plan_spot values((select plan_start_day+1 from plan where plan_id = 8888),8888,4, 129895,4,default,null);
insert into plan_spot values((select plan_start_day+1 from plan where plan_id = 8888),8888,5, 137826,5,3600,null);
--3일차
insert into plan_spot values((select plan_start_day+2 from plan where plan_id = 8888),8888,4, 3066924,1,default,null);
insert into plan_spot values((select plan_start_day+2 from plan where plan_id = 8888),8888,4, 130180,2,default,null);
insert into plan_spot values((select plan_start_day+2 from plan where plan_id = 8888),8888,4, 1986683,3,default,null);
insert into plan_spot values((select plan_start_day+2 from plan where plan_id = 8888),8888,4, 2758900,4,default,null);
insert into plan_spot values((select plan_start_day+2 from plan where plan_id = 8888),8888,4, 2910167,5,default,null);
insert into plan_spot values((select plan_start_day+1 from plan where plan_id = 8888),8888,5, 138680,6,default,null);
--4일차
insert into plan_spot values((select plan_start_day from plan where plan_id = 8888),8888,4, 2708336,1,default,null);
insert into plan_spot values((select plan_start_day+1 from plan where plan_id = 8888),8888,4, 2859034,2,default,null);
insert into plan_spot values((select plan_start_day+2 from plan where plan_id = 8888),8888,4, 2848391,3,default,null);

commit;