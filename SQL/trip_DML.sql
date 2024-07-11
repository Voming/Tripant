select * from area;
select * from plan;
select * from member;
select * from plan_member;
desc plan;
desc plan_member;

--area_code = 1
--plan_area_code = 1
--plan전체 + 지역이름 + 사진명
select p.*, a.area_short_name "AREA_SHORT_NAME", a.area_file_name "AREA_FILE_NAME" from plan p left join area a on (plan_area_code = area_code) where plan_delete_day is null;
--select * from plan_member join 
select * from plan_member where mem_email='gyrua34@gmail.com';

commit;

select p.* from plan_member pm join plan p on(pm.plan_id = p.plan_id) where pm.mem_email='gyrua34@gmail.com';