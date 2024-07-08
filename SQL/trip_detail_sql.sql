--view 삭제 view 표시를 위해 이름 변경
--drop view detailinfo;

------view 생성 --CREATE OR REPLACE VIEW 수정할 때 
----with read only : 읽기 전용 view 생성
CREATE OR REPLACE VIEW V_DETAILINFO AS (
select SPOT_PLAN_ID PLAN_ID, to_char(schedule_day,'YYYY-MM-DD(DY)') TRAVEL_DATE, SCHEDULE_START, SCHEDULE_END,SPOT_TYPE PLACE_TYPE,SPOT_CONTENTID CONTENTID, SPOT_ORDER TRAVEL_ORDER,SPOT_STAY_TIME STAY_TIME, SPOT_MEMO MEMO, TITLE,ADDRESS, FIRSTIMAGE, LNG,LAT from plan_schedule 
left join (select * from plan_spot s left join (select contentid,title , add1 address,firstimage,mapx lng,mapy lat from place) p on s.spot_contentid = p.contentid ) on schedule_plan_id = spot_plan_id 
where schedule_day = spot_day --order by PLAN_ID asc ,TRAVEL_DATE asc ,TRAVEL_ORDER ASC
)with read only;

--여행 세부일정 목록 불러오기 
SELECT TRAVEL_DATE , SCHEDULE_START ,SCHEDULE_END ,CONTENTID ,PLACE_TYPE , STAY_TIME ,
TRAVEL_ORDER ,MEMO ,TITLE ,ADDRESS ,FIRSTIMAGE ,LNG ,LAT  FROM V_DETAILINFO  ORDER BY TRAVEL_DATE ASC ,TRAVEL_ORDER ASC
;

--delete from plan_spot where spot_plan_id = 100;
--편집된 일정 저장하기

--trigger 생성
create or replace NONEDITIONABLE TRIGGER trg_plan_change
    BEFORE insert ON plan_spot
    REFERENCING OLD AS OLD
    FOR EACH ROW
DECLARE
BEGIN
    delete from plan_spot where spot_plan_id = :OLD.spot_plan_id;
END;

------------------


--세부 일정에서 띄울 여행일정의 기본 정보값 <여행 기본 정보 불러오기>
SELECT TO_CHAR(PLAN_AREA_CODE) CODE,PLAN_TITLE TITLE,TO_CHAR(PLAN_START_DAY,'YYYY.MM.DD') STARTDAY,PLAN_AREA_CODE AREACODE,TO_CHAR(PLAN_END_DAY,'YYYY.MM.DD') ENDDAY, AREA_SHORT_NAME AREANAME, AREA_X LNG, AREA_Y LAT 
FROM PLAN JOIN AREA ON AREA_CODE = PLAN_AREA_CODE WHERE PLAN_ID = 13
;


--더미--------------------------------------------------------------------
--date -> char 형태로 변경
select to_char(schedule_day,'YYYY-MM-DD(DY)') from plan_schedule;
------------------
desc plan;
desc V_DETAILINFO;
desc plan_spot;
--plan : plan_id,plan_area_code, plan_title, start,end
--area : area_code, area_short_name,area_x, area_y
desc area; --AREA_CODE
desc plan;--PLAN_AREA_CODE
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
