INSERT INTO plan VALUES (
    seq_plan_id.NEXTVAL,
    1,
    '멋진 여행2', DEFAULT,
    sysdate + 4, DEFAULT,
    NULL
);

--PLAN SCHEDULE 생성(plan_id 값 넣어서 사용) => 일별 스케쥴 11~22시 자동생성

CREATE OR REPLACE NONEDITIONABLE PROCEDURE pro_insert_schedule (
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

    FOR i IN 0..end_day - start_day LOOP
        INSERT INTO plan_schedule VALUES (
            ( start_day + i ),
            schedule_plan_id,
            ( to_date(to_char(start_day, 'YYYY-MM-DD')
                      || ' 11:00:00', 'YYYY-MM-DD HH24:MI:SS') ),
            ( to_date(to_char(start_day + i, 'YYYY-MM-DD')
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

EXEC pro_insert_schedule(2);