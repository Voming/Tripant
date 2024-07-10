SELECT * FROM diary;
SELECT * FROM diary_likes;
SELECT * FROM diary_reports;
SELECT  * FROM diary_save;
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

--여행기 조회하기
select * from 
	(   select t1.*, rownum rn from 
	        (SELECT  DIARY_ID,   MEM_NICK, DIARY_TITLE,
	                to_char(DIARY_DATE,'yyyy-MM-dd') DIARY_DATE,
	                DIARY_VIEWS,   DIARY_THEME,   PLAN_AREA_CODE,
	                DBMS_LOB.SUBSTR(DIARY_CONTENT, 1000) DIARY_CONTENT
	                , DIARY_OPEN        , DIARY_PLAN_ID        , DIARY_MEM_EMAIL
	                , diary_date diary_date_real
	                FROM   VIEW_DIARY_MEMBER_PLAN 
	                WHERE
	                    DIARY_OPEN = 0
	             		
	            		AND PLAN_AREA_CODE = (SELECT AREA_CODE  FROM AREA WHERE AREA_SHORT_NAME = '서울')
	            		
	                ORDER BY diary_date_real DESC NULLS LAST
	    ) t1
	) where rn  BETWEEN 1  AND  8;
---좋아요 조회
select * from 
(   select t1.*, rownum rn from 
        (SELECT  DIARY_ID,   MEM_NICK, DIARY_TITLE,
                to_char(DIARY_DATE,'yyyy-MM-dd') DIARY_DATE,
             NVL(likes,0) likes,
                DIARY_VIEWS,   DIARY_THEME,   PLAN_AREA_CODE,
                DBMS_LOB.SUBSTR(DIARY_CONTENT, 1000) DIARY_CONTENT
                , DIARY_OPEN        , DIARY_PLAN_ID        , DIARY_MEM_EMAIL
                , diary_date diary_date_real
                FROM   VIEW_DIARY_MEMBER_PLAN 
            LEFT OUTER JOIN (select	count (mem_email) likes, diary_id from diary_likes group by diary_id)  USING (diary_id)
                WHERE
                    DIARY_OPEN = 0 
            --AND PLAN_AREA_CODE = (SELECT AREA_CODE  FROM AREA WHERE AREA_SHORT_NAME = '서울')
                ORDER BY likes DESC NULLS LAST
    ) t1
) where rn <= 3  
--OK
;
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

-- diary write 글 쓰기 시 plan title 가져오기
SELECT plan_id AS planId, plan_title AS planTitle
		FROM view_plan_member
        WHERE mem_email = 'qothwls5@naver.com'
        ;


desc diary;
INSERT INTO diary (
		DIARY_ID, DIARY_MEM_EMAIL,
		DIARY_PLAN_ID, DIARY_TITLE,
		DIARY_CONTENT,
		DIARY_DATE, DIARY_OPEN,
		DIARY_VIEWS,
		DIARY_THEME)
		VALUES
		(diary_seq.NEXTVAL, 'qothwls5@naver.com',
		12, '강릉 여행',
		to_clob('<p>ㄴㅇㄹㅇㄹ</p><p>ㅇㄹㄴㅇㄹ</p><figure class="image"><img style="aspect-ratio:3000/2808;" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAC7gAAAr4CAYAAADYOvShAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA3JpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDkuMS1jMDAxIDc5LmE4ZDQ3NTM0OSwgMjAyMy8wMy8yMy0xMzowNTo0NSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDphNGExMzNhNy00NjUxLTYxNDUtYTA2Ny0xOWMzYzJlNzRlMWUiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6QTdGNEE2NjUzMkY4MTFFRTgzNDBBMEIzMTc5MjRFQjAiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6QTdGNEE2NjQzMkY4MTFFRTgzNDBBMEIzMTc5MjRFQjAiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIDI0LjYgKFdpbmRvd3MpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6YjlhYWVkNDYtY2QwNC05MTQ3LTg0NjQtOWQwYTZiNmM1ZDVlIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOkMzNTNFMUQ3MkIxRjExRUU5NkQxOUU3QzMxNTlDNjE3Ii8+IDwv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')
        || to_clob('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')
        || to_clob('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')
        || to_clob('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')
        ,
		to_date('2024-06-25', 'yyyy-mm-dd'), '0',
		0,
		'theme1')
;

commit;
select * from (
select t1.*, ROWNUM rn from (
		SELECT
		DIARY_ID,
		DIARY_MEM_EMAIL,
		MEM_NICK,
		DIARY_PLAN_ID,
		DIARY_TITLE,
		DBMS_LOB.SUBSTR(D.DIARY_CONTENT, 3900) DIARY_CONTENT,
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
		where 4-4  < 		rn and rn  <= 		4
;
select * from diary;
delete from diary  where diary_id in (95, 96);



-----------------------------24/06/27 
-- 한개의 다이어리에 여러명의 이메일로 눌린 하트의 개수 구하기
SELECT COUNT(*) likes
		FROM diary_likes
		WHERE DIARY_ID='111'
        ;
SELECT COUNT(*) is_my_likes		FROM diary_likes 		WHERE DIARY_ID = 111  and mem_email = 'qothwls5@naver.com'
        ;
SELECT diary_id		FROM diary_likes		WHERE mem_email = 'qothwls5@naver.com'
;
SELECT COUNT(*) likes, (SELECT COUNT(*) from diary_likes t2 where t1.DIARY_ID = t2.DIARY_ID and mem_email = 'qothwls5@naver.com') is_my_likes
		FROM diary_likes t1
		WHERE t1.DIARY_ID = 111
        ;
select diary_id, diary_views, nvl(likes,0) likes, mem_nick, diary_title, to_char(diary_date,'yyyy-MM-dd') diary_date, diary_mem_email
    from VIEW_DIARY_MEMBER 
        left join (select	count (mem_email) likes, diary_id from diary_likes group by diary_id) 
        using (diary_id)
    order by diary_views desc nulls last
;
select diary_id, diary_views, nvl(likes,0) likes, mem_nick, diary_title, to_char(diary_date,'yyyy-MM-dd') diary_date, diary_mem_email
    from VIEW_DIARY_MEMBER 
        left join (select	count (mem_email) likes, diary_id from diary_likes group by diary_id)   using (diary_id)
    order by likes desc nulls last
;
select diary_id, diary_views, nvl(likes,0) likes, mem_nick, diary_title, to_char(DIARY_DATE,'yyyy-MM-dd') diary_date, diary_mem_email
            , DBMS_LOB.SUBSTR(D.DIARY_CONTENT, 1000) DIARY_CONTENT
            , DIARY_THEME
            , PLAN_AREA_CODE
            , DIARY_OPEN
            , diary_date diary_date_real -- realtime dto just order by
    from VIEW_DIARY_MEMBER 
        left join (select	count (mem_email) likes, diary_id from diary_likes group by diary_id) 
        using (diary_id)
    order by diary_date_real desc nulls last
;
desc diary_likes;

-- 나의 조회수 
select 
    (SELECT COUNT(t1.DIARY_ID) FROM diary_likes t1 WHERE t1.DIARY_ID = t2.DIARY_ID  and t1.mem_email = 'seojw0730@gmail.com') is_my_likes, 
    t2.* 
from 
(   select t1.*, rownum rn from 
        (SELECT  DIARY_ID,   MEM_NICK, DIARY_TITLE,
                to_char(DIARY_DATE,'yyyy-MM-dd') DIARY_DATE,
             NVL(likes,0) likes,
                DIARY_VIEWS,   DIARY_THEME,   PLAN_AREA_CODE,
                DBMS_LOB.SUBSTR(DIARY_CONTENT, 1000) DIARY_CONTENT
                , DIARY_OPEN        , DIARY_PLAN_ID        , DIARY_MEM_EMAIL
                , diary_date diary_date_real
                FROM   VIEW_DIARY_MEMBER_PLAN 
            LEFT OUTER JOIN (select	count (mem_email) likes, diary_id from diary_likes group by diary_id)  USING (diary_id)
                WHERE
                    DIARY_OPEN = 0 
            --AND PLAN_AREA_CODE = (SELECT AREA_CODE  FROM AREA WHERE AREA_SHORT_NAME = '서울')
                ORDER BY likes DESC NULLS LAST
    ) t1
)t2 where rn between 1 and 100  
;
  
  select 
    (SELECT COUNT(t1.DIARY_ID) FROM diary_likes t1 WHERE t1.DIARY_ID = t2.DIARY_ID  and t1.mem_email = 'seojw0730@gmail.com') is_my_likes, 
    t2.* 
from 
(   select t1.*, rownum rn from 
        (SELECT  DIARY_ID,   MEM_NICK, DIARY_TITLE,
                to_char(DIARY_DATE,'yyyy-MM-dd') DIARY_DATE,
                DIARY_VIEWS,   DIARY_THEME,   PLAN_AREA_CODE,
                DBMS_LOB.SUBSTR(DIARY_CONTENT, 1000) DIARY_CONTENT
                , DIARY_OPEN        , DIARY_PLAN_ID        , DIARY_MEM_EMAIL
                , diary_date diary_date_real
                FROM   VIEW_DIARY_MEMBER_PLAN 
                WHERE
                    DIARY_OPEN = 0 
            --AND PLAN_AREA_CODE = (SELECT AREA_CODE  FROM AREA WHERE AREA_SHORT_NAME = '서울')
                ORDER BY diary_date_real DESC NULLS LAST
    ) t1
)t2 where rn between 1 and 100  
--OK
;
  select*from diary;
   select*from diary_save;
   
  ---글 미리보기 추출 쿼리문
 
SELECT diary_id,
       DBMS_LOB.SUBSTR(diary_content, 1000, 1) AS diary_preview
FROM diary
WHERE diary_id = '112';
--- 신고하기
select 
    (SELECT COUNT(t1.DIARY_ID) FROM diary_reports t1 WHERE t1.DIARY_ID = t2.DIARY_ID  and t1.mem_email = 'seojw0730@gmail.com') is_my_reports, 
    t2.* 
from 
(   select t1.*, rownum rn from 
        (SELECT  DIARY_ID,   MEM_NICK, DIARY_TITLE,
                to_char(DIARY_DATE,'yyyy-MM-dd') DIARY_DATE,
                DIARY_VIEWS,   DIARY_THEME,   PLAN_AREA_CODE,
                DBMS_LOB.SUBSTR(DIARY_CONTENT, 1000) DIARY_CONTENT
                , DIARY_OPEN        , DIARY_PLAN_ID        , DIARY_MEM_EMAIL
                , diary_date diary_date_real
                FROM   VIEW_DIARY_MEMBER_PLAN 
                WHERE
                    DIARY_OPEN = 0 
            --AND PLAN_AREA_CODE = (SELECT AREA_CODE  FROM AREA WHERE AREA_SHORT_NAME = '서울')
                ORDER BY diary_date_real DESC NULLS LAST
    ) t1
)t2 where rn between 1 and 100 ;
-- 글 신고하기
INSERT INTO
DIARY_REPORTS (DIARY_ID, MEM_EMAIL)
		VALUES ('500','qothwls5@naver.com');


select * from diary_reports;
desc diary_reports

------ON DELETE CASCADE 추가해서 변경
ALTER TABLE DIARY_LIKES
ADD CONSTRAINT FK_DIARY_TO_DIARY_LIKES_1
FOREIGN KEY ("DIARY_ID")
REFERENCES DIARY ("DIARY_ID")
ON DELETE CASCADE;
commit;
select * from user_tables;
desc DIARY_SAVE;
select * from DIARY_SAVE;
alter table diary_save rename column DLARY_IMAGE to DIARY_IMAGE;
alter table diary_save rename column DLARY_PREVIEW to DIARY_PREVIEW;


-- 기존 외래 키 제약 조건 삭제
ALTER TABLE DIARY_SAVE
DROP CONSTRAINT FK_DIARY_TO_DIARY_SAVE_1;

-- 새로운 외래 키 제약 조건 추가
ALTER TABLE DIARY_SAVE
ADD CONSTRAINT FK_DIARY_TO_DIARY_SAVE_1
FOREIGN KEY (DIARY_ID)
REFERENCES DIARY (DIARY_ID)
ON DELETE CASCADE;

-- 새로운 외래 키 제약 조건 추가
ALTER TABLE DIARY_REPORTS
DROP CONSTRAINT FK_DIARY_TO_DIARY_REPORTS_1;
-- 새로운 외래 키 제약 조건 추가
ALTER TABLE DIARY_REPORTS
ADD CONSTRAINT FK_DIARY_TO_DIARY_REPORTS_1
FOREIGN KEY (DIARY_ID)
REFERENCES DIARY (DIARY_ID)
ON DELETE SET NULL;


select diary_content
,SUBSTR(diary_content,2)
from diary;


--나의 여행기 목록
SELECT
			
			(SELECT COUNT(t1.DIARY_ID) FROM diary_likes t1 WHERE t1.DIARY_ID = t2.DIARY_ID and t1.mem_email ='qothwls5@naver.com') is_my_likes,
			
			(SELECT diary_Image FROM diary_save t1 WHERE	t1.DIARY_ID = t2.DIARY_ID) diary_Image,
			(SELECT diary_Preview FROM diary_save t1 WHERE	t1.DIARY_ID = t2.DIARY_ID) diary_Preview,		
			t2.*
		FROM
			( select t1.*, rownum rn 
			  from
			  (SELECT DIARY_ID, MEM_NICK, DIARY_TITLE,	to_char(DIARY_DATE,'yyyy-MM-dd') DIARY_DATE, 
			  			DIARY_VIEWS, DIARY_THEME, PLAN_AREA_CODE, DIARY_OPEN , DIARY_PLAN_ID ,
			  			DIARY_MEM_EMAIL,  diary_date diary_date_real
				FROM VIEW_DIARY_MEMBER_PLAN
				WHERE DIARY_MEM_EMAIL = 'qothwls5@naver.com'
					
				ORDER BY diary_date_real DESC NULLS LAST) t1
			)t2 
		where rn between 1 and 3;
        
-----   나의 글 보기(이미지)
	SELECT
			
			(SELECT COUNT(t1.DIARY_ID) FROM diary_likes t1 WHERE t1.DIARY_ID = t2.DIARY_ID and t1.mem_email ='qothwls5@naver.com')as is_my_likes,
			
			(SELECT diary_Image FROM diary_save t1 WHERE	t1.DIARY_ID = t2.DIARY_ID) as diary_Image,
			(SELECT diary_Preview FROM diary_save t1 WHERE	t1.DIARY_ID = t2.DIARY_ID) as diary_Preview,		
			t2.*

FROM
    (SELECT t1.*, rownum rn 
     FROM
         (SELECT DIARY_ID, 
                 MEM_NICK,
                 DIARY_TITLE,
                 TO_CHAR(DIARY_DATE, 'yyyy-MM-dd') AS DIARY_DATE,
                 DIARY_VIEWS,
                 DIARY_THEME, 
                 PLAN_AREA_CODE,
                 DIARY_OPEN, 
                 DIARY_PLAN_ID, 
                 DIARY_MEM_EMAIL,
                 DIARY_DATE AS diary_date_real
          FROM VIEW_DIARY_MEMBER_PLAN
          WHERE DIARY_MEM_EMAIL = 'qothwls5@naver.com'
          ORDER BY diary_date_real DESC NULLS LAST) t1
    ) t2
WHERE rn BETWEEN 1 AND 2;

select distinct
    (SELECT COUNT(*) likes FROM diary_likes t1 WHERE DIARY_ID = t2.DIARY_ID) a
    , (SELECT COUNT(t1.DIARY_ID) FROM diary_likes t1 WHERE t1.DIARY_ID = t2.DIARY_ID and mem_email ='qothwls5@naver.com') is_my_likes
FROM diary_likes t2
WHERE DIARY_ID = 144 and mem_email ='qothwls5@naver.com'
;

select count(a)
from  (
    select count(*) a, mem_email 
        FROM diary_likes t2
        WHERE DIARY_ID = 144 
        group by mem_email
) t1
;
----분석함수 사용 좋아요
select distinct 
        count(*) over( PARTITION BY DIARY_ID ) likes
        ,(SELECT COUNT(t1.DIARY_ID) FROM diary_likes t1 WHERE t1.DIARY_ID = t2.DIARY_ID and t1.mem_email ='qothwls5@naver.com') is_my_likes
    FROM diary_likes t2
    WHERE DIARY_ID = 144 
;
----
		SELECT t2.* 
            ,(SELECT COUNT(t1.DIARY_ID) FROM diary_likes t1 WHERE t1.DIARY_ID = t2.DIARY_ID and t1.mem_email ='qothwls5@naver.com') is_my_likes
			FROM VIEW_DIARY_MEMBER t2
			WHERE DIARY_ID = 144
            ;
            
select count(*) from diary_likes
WHERE DIARY_ID = 144
and mem_email ='qothwls5@naver.com'
;
SELECT distinct diary_id FROM diary_likes
minus
SELECT diary_id FROM diary_likes
    WHERE mem_email = 'qothwls5@naver.com'
;
-------------최신순
SELECT
			
				(SELECT COUNT(t1.DIARY_ID) FROM diary_likes t1 	WHERE t1.DIARY_ID = t2.DIARY_ID and t1.mem_email ='qothwls5@naver.com') is_my_likes,
		
		
			(SELECT diary_Image FROM diary_save t1 WHERE	t1.DIARY_ID = t2.DIARY_ID) diary_Image,
			(SELECT diary_Preview FROM diary_save t1 WHERE	t1.DIARY_ID = t2.DIARY_ID) diary_Preview,
			t2.*
		FROM
			( SELECT t1.*, rownum rn 
				FROM
					(SELECT DIARY_ID, MEM_NICK,DIARY_TITLE,
						to_char(DIARY_DATE,'yyyy-MM-dd') DIARY_DATE,
						DIARY_VIEWS,DIARY_THEME, PLAN_AREA_CODE,
						DIARY_OPEN , DIARY_PLAN_ID , DIARY_MEM_EMAIL,
						diary_date diary_date_real
					FROM VIEW_DIARY_MEMBER_PLAN
					WHERE DIARY_OPEN = 0
	
			AND PLAN_AREA_CODE = (SELECT AREA_CODE FROM AREA WHERE AREA_SHORT_NAME = '서울')
		
		ORDER BY diary_date_real DESC NULLS LAST) t1)t2 
		WHERE rn between 1 and 8;
-----좋아요 정렬
SELECT
			
				(SELECT COUNT(t1.DIARY_ID) FROM diary_likes t1 	WHERE t1.DIARY_ID = t2.DIARY_ID and t1.mem_email ='qothwls5@naver.com') is_my_likes,
		
	
				(SELECT diary_Image FROM diary_save t1 WHERE	t1.DIARY_ID = t2.DIARY_ID) diary_Image,
				(SELECT diary_Preview FROM diary_save t1 WHERE	t1.DIARY_ID = t2.DIARY_ID) diary_Preview,
				t2.*
			FROM
				( SELECT t1.*, rownum rn 
					FROM
					(SELECT DIARY_ID, MEM_NICK,DIARY_TITLE,
						to_char(DIARY_DATE,'yyyy-MM-dd') DIARY_DATE,NVL(likes,0)likes,
						DIARY_VIEWS,DIARY_THEME, PLAN_AREA_CODE,
						DIARY_OPEN , DIARY_PLAN_ID , DIARY_MEM_EMAIL,
						diary_date diary_date_real
					FROM VIEW_DIARY_MEMBER_PLAN
					LEFT OUTER JOIN (select count (mem_email) likes,diary_id from diary_likes group by diary_id) USING (diary_id)
					WHERE DIARY_OPEN = 0
		
			AND PLAN_AREA_CODE = (SELECT AREA_CODE FROM AREA WHERE AREA_SHORT_NAME = '서울')
		
			ORDER BY likes DESC NULLS LAST) t1)t2 
			WHERE rn between 1 and 3;
-----조회수 정렬
SELECT
			
				(SELECT COUNT(t1.DIARY_ID) FROM diary_likes t1 	WHERE t1.DIARY_ID = t2.DIARY_ID and t1.mem_email ='qothwls5@naver.com') is_my_likes,
			
				(SELECT diary_Image FROM diary_save t1 WHERE	t1.DIARY_ID = t2.DIARY_ID) diary_Image,
				(SELECT diary_Preview FROM diary_save t1 WHERE	t1.DIARY_ID = t2.DIARY_ID) diary_Preview,
				t2.*
			FROM
				( SELECT t1.*, rownum rn FROM
					(SELECT DIARY_ID, MEM_NICK,DIARY_TITLE,
						to_char(DIARY_DATE,'yyyy-MM-dd') DIARY_DATE,
						DIARY_VIEWS,DIARY_THEME, PLAN_AREA_CODE,
						DIARY_OPEN , DIARY_PLAN_ID , DIARY_MEM_EMAIL,
						diary_date diary_date_real
					FROM VIEW_DIARY_MEMBER_PLAN
					
					WHERE DIARY_OPEN = 0
	
			AND PLAN_AREA_CODE = (SELECT AREA_CODE FROM AREA WHERE AREA_SHORT_NAME = '서울')
		
			ORDER BY DIARY_VIEWS DESC NULLS LAST) t1)t2 
			WHERE rn between 1 and 3;
            
      select * from diary;
      select * from diary_save;
      
-------------수정하기
UPDATE DIARY
SET
    DIARY_TITLE = '비공개글 업데이트해서 공개글로 전환',
    diary_plan_id='3',
    DIARY_CONTENT = TO_CLOB('국가는 농·어민과 중소기업의 자조조직을 육성하여야 하며, 그 자율적 활동과 발전을 보장한다. 원장은 국회의 동의를 얻어 대통령이 임명하고, 그 임기는 4년으로 하며, 1차에 한하여 중임할 수 있다. 국회의원의 수는 법률로 정하되, 200인 이상으로 한다. 모든 국민은 언론·출판의 자유와 집회·결사의 자유를 가진다. 비상계엄하의 군사재판은 군인·군무원의 범죄나 군사에 관한 간첩죄의 경우와 초병·초소·유독음식물공급·포로에 관한 죄중 법률이 정한 경우에 한하여 단심으로 할 수 있다. 다만, 사형을 선고한 경우에는 그러하지 아니하다.'),
    DIARY_DATE = TO_DATE('24/07/08', 'YY/MM/DD'),
    DIARY_OPEN = '1',
    DIARY_THEME = 'theme2'
WHERE DIARY_ID = 138
AND DIARY_MEM_EMAIL = 'qothwls5@naver.com';
 
-------theme 적용
    SELECT ITEM_CODE, ITEM_NAME, ITEM_COLOR
    FROM (
        SELECT ITEM.ITEM_CODE, ITEM.ITEM_NAME, ITEM.ITEM_COLOR
        FROM BUY
        JOIN ITEM ON BUY.ITEM_CODE = ITEM.ITEM_CODE
        WHERE BUY.MEM_EMAIL ='gyrua34@gmail.com'
        AND ROWNUM <= 10
    );
    ----------폰트 구매 안보이게
    SELECT ITEM.ITEM_CODE, ITEM.ITEM_NAME, ITEM.ITEM_COLOR
    FROM BUY
    JOIN ITEM ON BUY.ITEM_CODE = ITEM.ITEM_CODE
    WHERE BUY.MEM_EMAIL = 'gyrua34@gmail.com'
      AND ITEM.ITEM_CODE NOT IN ('F0', 'F1')
      AND ROWNUM <= 10;
      
----- 색상 변경 뒤에 33 붙여서
ALTER table item modify item_color varchar2(10);
update item set item_color = '13358F33' where item_code ='T9';
      commit;
      