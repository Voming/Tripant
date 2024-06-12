TRUNCATE table place;

TRUNCATE table place_move_time;


SELECT TYPE, CONTENTID, MAPX, MAPY FROM PLACE WHERE AREACODE=1 AND TYPE != 10 ORDER BY CONTENTID;
select * from area;
select distinct type from place;
select count(*) from place;
select areacode, count(areacode) from place group by areacode;
select type, count(*) from place where areacode=8 group by type ;


select contentid from place where areacode=8 and type=6 ;
select count(*) from place where areacode=38 and type!=10 ;
--1951633
--2672031
--2734266
--2749716

select count(*) from place_move_time;
select * from place_move_time;
select count(*) from (select distinct contentid_start, contentid_end from place_move_time) ta;

commit;