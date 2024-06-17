<<<<<<< Updated upstream
select stay_day travel_date,stay_plan_id plan_id,stay_type type,null travel_order,null stay_time,null spot_memo,p.* from plan_stay join (select contentid,title , add1 address,firstimage,mapx lng,mapy lat from place) p on stay_contentid= p.contentid where stay_plan_id  = 13
union
select s.spot_day travel_date, s.spot_plan_id,s.spot_type,s.spot_order ,s.spot_stay_time,spot_memo,p.* from plan_spot s join (select contentid,title , add1 address,firstimage,mapx lng,mapy lat from place) p on s.spot_contentid = p.contentid where s.spot_plan_id = 13
order by travel_date asc ,travel_order asc;
desc plan_spot;
=======

------view 생성 --CREATE OR REPLACE VIEW 수정할 때 
CREATE VIEW DETAILINFO AS (
select SPOT_PLAN_ID PLAN_ID, schedule_day TRAVEL_DATE, SCHEDULE_START, SCHEDULE_END,SPOT_TYPE TYPE,SPOT_CONTENTID CONTENTID, SPOT_ORDER TRAVEL_ORDER,SPOT_STAY_TIME STAY_TIME, SPOT_MEMO MEMO, TITLE,ADDRESS, FIRSTIMAGE, LNG,LAT from plan_schedule 
left join (select * from plan_spot s left join (select contentid,title , add1 address,firstimage,mapx lng,mapy lat from place) p on s.spot_contentid = p.contentid ) on schedule_plan_id = spot_plan_id 
where schedule_day = spot_day --order by travel_date asc ,spot_order asc 
union
select SCHEDULE_PLAN_ID PLAN_ID, SCHEDULE_DAY TRAVEL_DATE, SCHEDULE_START,SCHEDULE_END,STAY_TYPE TYPE, STAY_CONTENTID CONTENTID, null TRAVEL_ORDER, null STAY_TIME, NULL MEMO, TITLE,ADDRESS, FIRSTIMAGE, LNG, LAT from plan_schedule
left join (select * from plan_stay join (select contentid,title , add1 address,firstimage,mapx lng,mapy lat from place)  on stay_contentid= contentid ) on schedule_plan_id = stay_plan_id 
where schedule_day = stay_day
);




select * from detailinfo where plan_id = 13 order by travel_date asc ,travel_order asc;



----------------------------------------------------------------------더미
-- plan_id 에 따른 세부일정 (spot + stay) - plan_id O
select stay_day travel_date,stay_plan_id plan_id,stay_type type,null travel_order,null stay_time,null spot_memo,p.* from plan_stay join (select contentid,title , add1 address,firstimage,mapx lng,mapy lat from place) p on stay_contentid= p.contentid where stay_plan_id  = 13
union
select s.spot_day travel_date, s.spot_plan_id,s.spot_type,s.spot_order ,s.spot_stay_time,spot_memo,p.* from plan_spot s join (select contentid,title , add1 address,firstimage,mapx lng,mapy lat from place) p on s.spot_contentid = p.contentid where s.spot_plan_id = 13
order by travel_date asc ,travel_order asc 
;
desc plan_spot;
desc place;
desc plan_schedule;

select SCHEDULE_PLAN_ID PLAN_ID, SCHEDULE_DAY TRAVEL_DATE, SCHEDULE_START,SCHEDULE_END,STAY_TYPE TYPE, STAY_CONTENTID CONTENTID, null TRAVEL_ORDER, null STAY_TIME, NULL MEMO, TITLE,ADDRESS, FIRSTIMAGE, LNG, LAT from plan_schedule
left join (select * from plan_stay join (select contentid,title , add1 address,firstimage,mapx lng,mapy lat from place)  on stay_contentid= contentid ) on schedule_plan_id = stay_plan_id 
where schedule_day = stay_day
;

commit;
>>>>>>> Stashed changes
