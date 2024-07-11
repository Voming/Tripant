alter session set nls_date_format='YYYY/MM/DD HH24:MI:SS';


select area_code, area_name,area_short_name,area_eng_name,area_file_name from area;

desc area;

select area_code, area_name,area_short_name,area_eng_name,area_file_name from area where area_name like '%충%';

desc plan;

select * from plan;

--plan, schedule dml 넣기
--insert INTO plan VALUES (9999, 1, '멋진 여행2', DEFAULT, sysdate + 2, DEFAULT, null);
--insert INTO plan_schedule VALUES ( (select plan_start_day from plan where plan_id=9999), 9999, TO_DATE('2024-05-30 10:00:00','YYYY-MM-DD HH24:MI:SS') ,  TO_DATE('2024-05-30 22:00:00','YYYY-MM-DD HH24:MI:SS') );
--insert INTO plan_schedule VALUES ( (select plan_start_day from plan where plan_id=9999)+1, 9999, TO_DATE('2024-05-30 10:00:00','YYYY-MM-DD HH24:MI:SS') ,  TO_DATE('2024-05-30 22:00:00','YYYY-MM-DD HH24:MI:SS') );
--insert INTO plan_schedule VALUES ( (select plan_start_day from plan where plan_id=9999)+2, 9999, TO_DATE('2024-05-30 10:00:00','YYYY-MM-DD HH24:MI:SS') ,  TO_DATE('2024-05-30 22:00:00','YYYY-MM-DD HH24:MI:SS') );

update plan_schedule set schedule_start = (TO_DATE(TO_CHAR(schedule_day,'YYYY-MM-DD') || ' 11:00:00','YYYY-MM-DD HH24:MI:SS')) , schedule_end =  (TO_DATE(TO_CHAR(schedule_day,'YYYY-MM-DD') || ' 22:00:00','YYYY-MM-DD HH24:MI:SS')) where schedule_day = TO_DATE('2024-05-31 09:24:37','YYYY-MM-DD HH24:MI:SS');
update plan_schedule set schedule_start = (TO_DATE('2024-06-01 10:00:00','YYYY-MM-DD HH24:MI:SS')) , schedule_end = (TO_DATE('2024-06-01 22:00:00','YYYY-MM-DD HH24:MI:SS')) where schedule_day = TO_DATE('2024-06-01 09:24:37','YYYY-MM-DD HH24:MI:SS');


select * from plan;
select * from plan_schedule;
--update plan set plan_end_day = (select plan_start_day from plan where plan_id=1) +3  where plan_id=1;
--alter TABLE plan MODIFY plan_end_day not null;

CREATE OR REPLACE NONEDITIONABLE PROCEDURE pro_INSERT_SCHEDULE (
    schedule_plan_id plan.plan_id%TYPE
) IS
    start_day  DATE;
    end_day    DATE;
BEGIN
    -- 다음 시퀀스 값을 선택합니다.
    SELECT
        plan_start_day,
        plan_end_day
    INTO
        start_day,
        end_day
    FROM
        plan
    WHERE
        plan_id = schedule_plan_id;

    FOR i IN 0..end_day-start_day LOOP
        INSERT INTO plan_schedule VALUES (
            (start_day + I),
            schedule_plan_id,
            ( to_date(to_char(start_day+I, 'YYYY-MM-DD')
                      || ' 11:00:00', 'YYYY-MM-DD HH24:MI:SS') ),
            ( to_date(to_char(start_day+I, 'YYYY-MM-DD')
                      || ' 22:00:00', 'YYYY-MM-DD HH24:MI:SS') )
        );

    END LOOP;
        -- 트랜잭션을 커밋합니다.

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        -- 필요한 경우 예외를 처리합니다.
        ROLLBACK;
        -- 이전에 정의된 예외를 발생
        RAISE;
END;
/


EXEC pro_INSERT_SCHEDULE(2);



CREATE OR REPLACE NONEDITIONABLE PROCEDURE pro_INSERT_SPOT (
    schedule_plan_id plan.plan_id%TYPE
) IS
    start_day  DATE;
    end_day    DATE;
BEGIN
    -- 다음 시퀀스 값을 선택합니다.
    SELECT
        plan_start_day,
        plan_end_day
    INTO
        start_day,
        end_day
    FROM
        plan
    WHERE
        plan_id = schedule_plan_id;

    FOR i IN 0..end_day-start_day LOOP
        INSERT INTO plan_schedule VALUES (
            (start_day + I),
            schedule_plan_id,
            ( to_date(to_char(start_day, 'YYYY-MM-DD')
                      || ' 11:00:00', 'YYYY-MM-DD HH24:MI:SS') ),
            ( to_date(to_char(start_day+I, 'YYYY-MM-DD')
                      || ' 22:00:00', 'YYYY-MM-DD HH24:MI:SS') )
        );

    END LOOP;
        -- 트랜잭션을 커밋합니다.

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        -- 필요한 경우 예외를 처리합니다.
        ROLLBACK;
        -- 이전에 정의된 예외를 발생
        RAISE;
END;
/

commit;

