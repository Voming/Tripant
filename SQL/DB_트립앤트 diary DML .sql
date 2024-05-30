SELECT * FROM diary;
SELECT * FROM plan;
SELECT * FROM member;

DESC diary;
--DIARY_ID 컬럼을 자동으로 증가 시퀀스
CREATE SEQUENCE diary_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

INSERT INTO diary (
    DIARY_ID,
    DIARY_MEM_EMAIL,
    DIARY_PLAN_ID,
    DIARY_TITLE,
    DIARY_CONTENT,
    DIARY_DATE,
    DIARY_OPEN,
    DIARY_VIEWS,
    DIARY_THEME
) VALUES (
    diary_seq.NEXTVAL,        -- DIARY_ID from sequence
    'owner@tripant.store',       -- DIARY_MEM_EMAIL
    1,                      -- DIARY_PLAN_ID
    '제주여행',         -- DIARY_TITLE
    '제주여행 내용', -- DIARY_CONTENT
    TO_DATE('2024-05-30', 'YYYY-MM-DD'), -- DIARY_DATE
    'Y',                      -- DIARY_OPEN (Y for yes, N for no)
   '',
   ''
);