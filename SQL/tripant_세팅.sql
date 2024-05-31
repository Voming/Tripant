--trip ant 기본 세팅
-- 지역 정보 넣기
INSERT INTO area SELECT * FROM area_temp;
SELECT
    *
FROM area;

commit;

-- 시간 길게 보이게
alter session set nls_date_format='YYYY/MM/DD HH24:MI:SS';