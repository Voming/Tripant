SELECT * FROM diary;
SELECT * FROM diary_likes;
SELECT * FROM diary_reports;

-- planId, memberemail -> 닉네임
SELECT * FROM plan;
SELECT * FROM member;
-- 지역 코드, 지역명 join해서 쿼리문 짜기
select*from area;

DESC diary;
--DIARY_ID 컬럼을 자동으로 증가 시퀀스
CREATE SEQUENCE diary_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;
delete from diary;
-- 데모 버전 글  
INSERT INTO DIARY (DIARY_ID, DIARY_MEM_EMAIL, DIARY_PLAN_ID, DIARY_TITLE, DIARY_CONTENT, DIARY_DATE, DIARY_OPEN, DIARY_VIEWS, DIARY_THEME)
VALUES (DIARY_SEQ.NEXTVAL, 'qothwls5@naver.com', 10, '첫 번째 여행기', '첫 번째 여행기의 내용입니다.', TO_DATE('2024-05-01', 'YYYY-MM-DD'), '1', 100, '');

INSERT INTO DIARY (DIARY_ID, DIARY_MEM_EMAIL, DIARY_PLAN_ID, DIARY_TITLE, DIARY_CONTENT, DIARY_DATE, DIARY_OPEN, DIARY_VIEWS, DIARY_THEME)
VALUES (DIARY_SEQ.NEXTVAL, 'qothwls5@naver.com', 11, '두 번째 여행기', '두 번째 여행기의 내용입니다.', TO_DATE('2024-05-02', 'YYYY-MM-DD'), '0', 200, '');

INSERT INTO DIARY (DIARY_ID, DIARY_MEM_EMAIL, DIARY_PLAN_ID, DIARY_TITLE, DIARY_CONTENT, DIARY_DATE, DIARY_OPEN, DIARY_VIEWS, DIARY_THEME)
VALUES (DIARY_SEQ.NEXTVAL, 'qothwls5@naver.com',12, '세 번째 여행기', '세 번째 여행기의 내용입니다.', TO_DATE('2024-05-03', 'YYYY-MM-DD'), '1', 300, '');

INSERT INTO DIARY (DIARY_ID, DIARY_MEM_EMAIL, DIARY_PLAN_ID, DIARY_TITLE, DIARY_CONTENT, DIARY_DATE, DIARY_OPEN, DIARY_VIEWS, DIARY_THEME)
VALUES (DIARY_SEQ.NEXTVAL, 'qothwls5@naver.com', 13, '네 번째 여행기', '네 번째 여행기의 내용입니다.', TO_DATE('2024-05-04', 'YYYY-MM-DD'), '1', 150, '');

INSERT INTO DIARY (DIARY_ID, DIARY_MEM_EMAIL, DIARY_PLAN_ID, DIARY_TITLE, DIARY_CONTENT, DIARY_DATE, DIARY_OPEN, DIARY_VIEWS, DIARY_THEME)
VALUES (DIARY_SEQ.NEXTVAL, 'qothwls5@naver.com',  14, '다섯 번째 여행기', '다섯 번째 여행기의 내용입니다.', TO_DATE('2024-05-05', 'YYYY-MM-DD'), '0', 250, '');
INSERT INTO DIARY (DIARY_ID, DIARY_MEM_EMAIL, DIARY_PLAN_ID, DIARY_TITLE, DIARY_CONTENT, DIARY_DATE, DIARY_OPEN, DIARY_VIEWS, DIARY_THEME)
VALUES (DIARY_SEQ.NEXTVAL, 'qothwls5@naver.com',  15, '여섯 번째 여행기', '여섯 번째 여행기의 내용입니다.', TO_DATE('2024-05-05', 'YYYY-MM-DD'), '0', 250, '');
INSERT INTO DIARY (DIARY_ID, DIARY_MEM_EMAIL, DIARY_PLAN_ID, DIARY_TITLE, DIARY_CONTENT, DIARY_DATE, DIARY_OPEN, DIARY_VIEWS, DIARY_THEME)
VALUES (DIARY_SEQ.NEXTVAL, 'qothwls5@naver.com',  16, '일곱 번째 여행기', '일곱 번째 여행기의 내용입니다.', TO_DATE('2024-05-05', 'YYYY-MM-DD'), '0', 250, '');


-- DIARY_LIKES 테이블에 데이터 삽입
INSERT INTO DIARY_LIKES (DIARY_ID, MEM_EMAIL)
VALUES (73, 'qothwls5@naver.com');

INSERT INTO DIARY_LIKES (DIARY_ID, MEM_EMAIL)
VALUES (74, 'qothwls5@naver.com');
-- DIARY_REPORTS 테이블에 데이터 삽입
INSERT INTO DIARY_REPORTS (DIARY_ID, MEM_EMAIL, REPORT_STATE)
VALUES (73, 'qothwls5@naver.com', 1);

INSERT INTO DIARY_REPORTS (DIARY_ID, MEM_EMAIL, REPORT_STATE)
VALUES (74, 'qothwls5@naver.com', 1);

desc diary;

-- 닉네임 조회하기
select * from diary;

select DIARY_ID ,DIARY_MEM_EMAIL,DIARY_PLAN_ID,DIARY_TITLE,DIARY_CONTENT,DIARY_DATE ,DIARY_OPEN,DIARY_VIEWS 
from diary
join member on diary_mem_email = member.mem_email;

--planid 조회하기 
SELECT p.plan_id AS planId, p.plan_title AS planTitle
FROM diary d
JOIN plan p ON d.diary_plan_id = p.plan_id
WHERE d.diary_mem_email = 'qothwls5@naver.com';


----view 생성 하고 read only 사용
create or replace view view_diary_member
as 
(
select d.*, m.mem_nick 
-- add column
from diary d
left outer join member m on (d.diary_mem_email = m.mem_email)
)
with read only
;
create or replace view view_diary_member_plan
as 
(
select d.*, m.mem_nick, p.plan_area_code
-- add column
from diary d
left outer join member m on (d.diary_mem_email = m.mem_email)
left outer join plan p on (d.diary_plan_id = p.plan_id)
)
with read only
;
--지역 코드 연결하기
select d.diary_id, d.diary_title, d.diary_content, d.diary_date, d.mem_nick, d.diary_views from view_diary_member D
join plan P on(D.diary_plan_id = P.plan_id)
where P.plan_area_code ='1'
;

-- 나의 글 더보기 4개씩 뿌리기

select * from (
    select t1.*, ROWNUM rn from (
    SELECT 
            DIARY_ID,
            DIARY_MEM_EMAIL, 
            MEM_NICK, 
            DIARY_PLAN_ID, 
            DIARY_TITLE, 
            DIARY_CONTENT,
            DIARY_DATE,
            DIARY_OPEN, 
            DIARY_VIEWS, 
            DIARY_THEME,
            PLAN_AREA_CODE
        FROM 
            VIEW_DIARY_MEMBER_PLAN D
        WHERE 
            DIARY_MEM_EMAIL = 'qothwls5@naver.com'
        ORDER BY 
            DIARY_DATE DESC
    ) t1
)
where 8-4 < rn and rn <= 8;

commit;