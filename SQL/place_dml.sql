commit;

select * from area;

desc place_move_time;

TRUNCATE TABLE place_move_time;

select type, contentid, mapx, mapy,areacode from place where areacode=1 order by contentid;

select count(*) from place where areacode=1;

select * from area where area_name = '서울특별시';

select * from member;