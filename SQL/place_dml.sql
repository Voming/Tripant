commit;

select * from place;

desc place_move_time;

TRUNCATE TABLE place_move_time;

select type, contentid, mapx, mapy,areacode from place where areacode=1 order by contentid;

select count(*) from place where areacode=1;