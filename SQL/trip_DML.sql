select * from area;
select * from plan;
select * from member;
select * from plan_member;
desc plan;
desc plan_member;
insert into plan values(SEQ_PLAN_ID.nextval,1,'개미와 함께하는 서울여행',sysdate, sysdate+2,sysdate,null);
insert into plan_member values (22,'gyrua34@gmail.com','1');
insert into plan_member values (1,'gyrua34@gmail.com','1');
insert into plan_member values (2,'seojw0730@naver.com','1');
insert into plan_member values (23,'seojw0730@naver.com','1');

--area_code = 1
--plan_area_code = 1
--plan전체 + 지역이름 + 사진명
select p.*, a.area_short_name "AREA_SHORT_NAME", a.area_file_name "AREA_FILE_NAME" from plan p left join area a on (plan_area_code = area_code) where plan_delete_day is null;
--select * from plan_member join 
select * from plan_member where mem_email='gyrua34@gmail.com';

select a.area_short_name area_short_name, a.area_file_name area_file_name ,t1.*
from area a join (select p.* from plan_member pm join plan p on(pm.plan_id = p.plan_id) where pm.mem_email='gyrua34@gmail.com') t1
on a.area_code = t1.plan_area_code;
commit;

select p.* from plan_member pm join plan p on(pm.plan_id = p.plan_id) where pm.mem_email='gyrua34@gmail.com';