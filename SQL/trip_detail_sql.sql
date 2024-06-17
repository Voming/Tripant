
-- plan_id 에 따른 세부일정 (spot + stay) - plan_id X
select stay_day travel_date,stay_type type,null travel_order,null stay_time,null spot_memo,p.* from plan_stay join (select contentid,title , add1 address,firstimage,mapx lng,mapy lat from place) p on stay_contentid= p.contentid where stay_plan_id  = 13
union
select s.spot_day travel_date,s.spot_type,s.spot_order ,s.spot_stay_time,spot_memo,p.* from plan_spot s join (select contentid,title , add1 address,firstimage,mapx lng,mapy lat from place) p on s.spot_contentid = p.contentid where s.spot_plan_id = 13
order by travel_date asc ,travel_order asc
;

-- plan_id 에 따른 세부일정 (spot + stay) - plan_id O
select stay_day travel_date,stay_plan_id plan_id,stay_type type,null travel_order,null stay_time,null spot_memo,p.* from plan_stay join (select contentid,title , add1 address,firstimage,mapx lng,mapy lat from place) p on stay_contentid= p.contentid where stay_plan_id  = 13
union
select s.spot_day travel_date, s.spot_plan_id,s.spot_type,s.spot_order ,s.spot_stay_time,spot_memo,p.* from plan_spot s join (select contentid,title , add1 address,firstimage,mapx lng,mapy lat from place) p on s.spot_contentid = p.contentid where s.spot_plan_id = 13
order by travel_date asc ,travel_order asc 
;



