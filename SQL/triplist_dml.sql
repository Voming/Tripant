select * from plan;

--삭제기록 없애기
UPDATE plan SET plan_delete_day = null  where plan_delete_day is not null;


--delete
UPDATE plan SET plan_delete_day=sysdate where plan_id = 21;


--select
select a.area_short_name area_short_name, a.area_file_name area_file_name ,t1.*
from area a join (select p.* from plan_member pm join plan p on(pm.plan_id = p.plan_id) where pm.mem_email='gyrua34@gmail.com' and plan_delete_day is null) t1
on a.area_code = t1.plan_area_code order by plan_id desc;

commit;