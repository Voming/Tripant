commit;
select * from plan;
select * from plan_member;
select * from member;
--(삭제기록 없애기)
--UPDATE plan SET plan_delete_day = null  where plan_delete_day is not null;


--여행 삭제
UPDATE plan SET plan_delete_day=sysdate where plan_id = 21;


--여행 목록 
select a.area_short_name area_short_name, a.area_file_name area_file_name ,t1.*
from area a join (select p.* from plan_member pm join plan p on(pm.plan_id = p.plan_id) where pm.mem_email='gyrua34@gmail.com' and plan_delete_day is null) t1
on a.area_code = t1.plan_area_code order by plan_id desc;

--여행 목록  + 로그인 유저가 일정 생정자인지 공유자인지 구분
select a.area_short_name area_short_name, a.area_file_name area_file_name ,t1.*
from area a join (select p.*,pm.plan_mem_role from plan_member pm join plan p on(pm.plan_id = p.plan_id) where pm.mem_email='gyrua34@gmail.com' and plan_delete_day is null) t1
on a.area_code = t1.plan_area_code order by plan_id desc
;


--유저 검색
select mem_nick, plan_id, plan_mem_role from plan_member join member using (mem_email) where plan_id = 11 and plan_mem_role = 0 and mem_nick like '%원%' and not mem_email = 'gyrua34@gmail.com'
union
select mem_nick,  null, null from member where mem_role in ('ROLE_MEM', 'ROLE_VIP') and mem_nick like '%원%'
minus
select mem_nick,  null, null from member join plan_member using (mem_email) where plan_id = 11
;
----x 공유자 더미
select * from plan_member join (select mem_nick, mem_email from member where mem_nick like '%겸%' and mem_role in ('ROLE_MEM','ROLE_VIP')) using (mem_email) where plan_mem_role = 0 order by mem_nick desc;
select m.mem_nick, m.mem_email, p.* from member m join plan_member p on p.mem_email =m.mem_email where mem_nick like '%원%' and mem_role in ('ROLE_MEM','ROLE_VIP') order by mem_nick desc;
desc plan_member;


--여행공유자
select mem_nick, plan_id, plan_mem_role from plan_member join member using (mem_email) where plan_id = 13 and plan_mem_role = 0 and not mem_email = 'gyrua34@gmail.com';

--공유자 추가
select mem_email from member where mem_nick='재원';
insert into plan_member values (16,(select mem_email from member where mem_nick='재원'),'0');

--공유자 삭제
delete from plan_member where plan_id = 11 and mem_email=(select mem_email from member where mem_nick='김보민')
;
commit;
ROLLBACK;