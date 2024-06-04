--trip ant 기본 세팅
-- 지역 정보 넣기
INSERT INTO area SELECT * FROM area_temp;
SELECT * FROM area order by area_name;

commit;

INSERT INTO plan VALUES (
    seq_plan_id.NEXTVAL,
    1,
    '멋진 여행1', DEFAULT,
    sysdate + 4, DEFAULT,
    NULL
);
INSERT INTO plan VALUES (
    seq_plan_id.NEXTVAL,
    1,
    '멋진 여행2', DEFAULT,
    sysdate + 4, DEFAULT,
    NULL
);

SELECT * FROM plan;

-- 시간 길게 보이게
alter session set nls_date_format='YYYY/MM/DD HH24:MI:SS';