select * from area;
select * from plan;
select * from member;
select * from plan_member;
desc plan;
desc plan_member;
insert into plan values(SEQ_PLAN_ID.nextval,1,'개미와 함께하는 서울여행',sysdate+2, sysdate+5,sysdate,null);
insert into plan values(SEQ_PLAN_ID.nextval,39,'개미와 함께하는 제주 한라봉봉여행',sysdate+10, sysdate+12,sysdate,null);
insert into plan values(SEQ_PLAN_ID.nextval,39,'군침루피와 눈뜨고 코베이는 서울여행',sysdate+13, sysdate+20,sysdate,null);
insert into plan values(SEQ_PLAN_ID.nextval,1,'군침루피와 눈뜨고 코베이는 서울여행2',sysdate+20, sysdate+22,sysdate,null);
insert into plan values(SEQ_PLAN_ID.nextval,1,'군침루피와 눈뜨고 코베이는 서울여행 세번째',sysdate+30, sysdate+32,sysdate,null);
insert into plan values(SEQ_PLAN_ID.nextval,1,'군침루피와 눈뜨고 코베이는 서울여행 네번째',sysdate+15, sysdate+24,sysdate,null);
insert into plan values(SEQ_PLAN_ID.nextval,39,'군침루피와 눈뜨고 코베이는 제주여행 다섯',sysdate+15, sysdate+24,sysdate,null);
insert into plan_member values (2,'gyrua34@gmail.com','1');
insert into plan_member values (1,'gyrua34@gmail.com','1');
insert into plan_member values (4,'gyrua34@gmail.com','1');
insert into plan_member values (21,'gyrua34@gmail.com','1');
insert into plan_member values (22,'gyrua34@gmail.com','1');
insert into plan_member values (3,'gyrua34@gmail.com','0');

insert into plan_member values (2,'qothwls5@naver.com','0');
insert into plan_member values (3,'qothwls5@naver.com','1');
insert into plan_member values (23,'qothwls5@naver.com','1');

--area_code = 1
--plan_area_code = 1
--plan전체 + 지역이름 + 사진명
select p.*, a.area_short_name "AREA_SHORT_NAME", a.area_file_name "AREA_FILE_NAME" from plan p left join area a on (plan_area_code = area_code) where plan_delete_day is null;
--select * from plan_member join 
select * from plan_member where mem_email='gyrua34@gmail.com';

commit;

select p.* from plan_member pm join plan p on(pm.plan_id = p.plan_id) where pm.mem_email='gyrua34@gmail.com';