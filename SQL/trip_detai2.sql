--DB에 값 넣기
insert into plan_spot values((select schedule_day from plan_schedule where schedule_plan_id = 13 and TO_CHAR(schedule_day,'yyyy-MM-dd') = '2024-07-18'),13,4, 133328,4,default,null);

-- plan_schedule에서 특정요일만 뽑아오기
select schedule_day from plan_schedule where schedule_plan_id = 13 and TO_CHAR(schedule_day,'yyyy-MM-dd') = SUBSTR('2024-07-16(화)', 1, 10);
SELECT SCHEDULE_DAY FROM PLAN_SCHEDULE WHERE SCHEDULE_PLAN_ID = 13 AND TO_CHAR(SCHEDULE_DAY,'yyyy-MM-dd') = SUBSTR('2024-07-16(화)', 1, 10);

select * from plan_spot where spot_plan_id=13 order by spot_day asc ,spot_order ;

update plan_spot set  spot_order = 5 where  TO_CHAR(spot_day,'yyyy-MM-dd') = '2024-07-16'and spot_contentid = 1870779;

commit;
rollback;
