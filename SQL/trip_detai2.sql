--DB에 값 넣기
insert into plan_spot values((select schedule_day from plan_schedule where schedule_plan_id = 13 and TO_CHAR(schedule_day,'yyyy-MM-dd') = '2024-07-18'),13,4, 133328,4,default,null);

-- plan_schedule에서 특정요일만 뽑아오기
select schedule_day from plan_schedule where schedule_plan_id = 13 and TO_CHAR(schedule_day,'yyyy-MM-dd') = SUBSTR('2024-07-16(화)', 1, 10);
SELECT SCHEDULE_DAY FROM PLAN_SCHEDULE WHERE SCHEDULE_PLAN_ID = 13 AND TO_CHAR(SCHEDULE_DAY,'yyyy-MM-dd') = SUBSTR('2024-07-16(화)', 1, 10);

select * from plan_spot where spot_plan_id=13 order by spot_day asc ,spot_order ;

update plan_spot set  spot_order = 5 where  TO_CHAR(spot_day,'yyyy-MM-dd') = '2024-07-16'and spot_contentid = 1870779;

commit;
rollback;
desc plan_member;
select * from plan_member;
delete from plan_member where plan_id = 13 and mem_email=(select mem_email from member where mem_nick='오오')
;
insert into plan_member values (13,(select mem_email from member where mem_nick='오오'),'0')
;

select * from place where type = 100;

--전남 무안군 무안읍 무안로 530

select * from plan_spot where spot_plan_id =13 and to_char(spot_day,'dd')=16;

insert into plan_spot values((select plan_start_day from plan where plan_id = 13),13,100, 1,1,default,null);
update plan_spot set spot_order = 7 where spot_contentid =1870779 and to_char(spot_day,'dd')=16;
delete from plan_spot where spot_type =100 and spot_plan_id =13;

rollback;
commit;