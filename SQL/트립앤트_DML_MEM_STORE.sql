-- 트립앤트 및 관리자
begin
    for i in 1..10 loop
        insert into member values(
            'admin'||i||'@tripant.store', 
            'admin'||i, 
            '$2a$12$ezqkPXRgYVoKN01wpt5v8O9wMYiG3snSkWFtVZs7i7sHgCE9QiNo2', 
            'ROLE_ADMIN', 
            1, 
            8, 
            to_date('01/01/01', 'YY/MM/DD'), 
            to_date('01/01/01', 'YY/MM/DD'), 
            '010', 
            0, 
            null, 
            null, 
            null, 
            null, 
            0
        );
    end loop;
    commit;
end;
/
-- 트립앤트 테스트 계정
begin
    for i in 1..5 loop
        insert into member values(
            'test'||i||'@tripant.store', 
            'test'||i, 
            '$2a$12$ezqkPXRgYVoKN01wpt5v8O9wMYiG3snSkWFtVZs7i7sHgCE9QiNo2', 
            'ROLE_MEM', 
            1, 
            8, 
            to_date('01/01/01', 'YY/MM/DD'), 
            to_date('01/01/01', 'YY/MM/DD'), 
            '010', 
            0, 
            null, 
            null, 
            null, 
            null, 
            0
        );
    end loop;
    commit;
end;
/
-- 트립앤트 회원
insert into member values(
    'seojw0730@naver.com', 
    '재원', 
    '$2a$12$6jbDn8AQYh/5V1OI7ok4UuW.Wurj0vOGZqpmqYzfOLEsVez6BNn5O', 
    'ROLE_MEM', 
    1, 
    'T', 
    to_date('24/06/03', 'RR/MM/DD'), 
    to_date('97/07/30', 'RR/MM/DD'), 
    '01063891527', 
    0, 
    null, 
    null, 
    null, 
    null, 
    0
);
insert into member values(
    'seojw0730@gmail.com', 
    '서재원', 
    '$2a$12$6jbDn8AQYh/5V1OI7ok4UuW.Wurj0vOGZqpmqYzfOLEsVez6BNn5O', 
    'ROLE_MEM', 
    1, 
    8, 
    to_date('24/06/08', 'RR/MM/DD'), 
    to_date('97/07/30', 'RR/MM/DD'), 
    '01063891527', 
    default, 
    null, 
    null, 
    null, 
    null, 
    0
);
insert into member values(
    'bomin1107@naver.com', 
    '김보민', 
    '$2a$10$7DTFJmTDLsC3vitXfDfFHerJPbnSfXMczAg34Y7vd8NiZZXI8tKLK', 
    'ROLE_MEM', 
    1, 
    8, 
    to_date('24/06/03', 'RR/MM/DD'), 
    to_date('24/06/06', 'RR/MM/DD'), 
    '010', 
    0, 
    null, 
    null, 
    null, 
    null, 
    0
);
insert into member values(
    'qothwls5@naver.com', 
    '군침루피', 
    '$2a$10$m4dtdSV1KSaMuylA9jzQDulgNjEFJILea.e5KHaIFoRMHNyqzo4xm', 
    'ROLE_MEM', 
    1, 
    8, 
    to_date('24/06/03', 'RR/MM/DD'), 
    to_date('99/08/05', 'RR/MM/DD'), 
    '010', 
    0, 
    null, 
    null, 
    null, 
    null, 
    0
);
insert into member values(
    'gyrua34@gmail.com', 
    '효겸', 
    '$2a$10$OkiTJp9MeIZTKSSHV6ClaOgLhYX6QveeWY62OxeycL9PoBkpBGxO.', 
    'ROLE_MEM', 
    1, 
    8, 
    to_date('24/06/05', 'RR/MM/DD'), 
    to_date('95/06/29', 'RR/MM/DD'), 
    '010', 
    0, 
    null, 
    null, 
    null, 
    null, 
    0
);
insert into member values(
    'dpdls898@naver.com', 
    '오예', 
    '$2a$10$0a0QuNdikz2mttbPInhXyeesWiMDpTZtGgeilB2r49QhX6qp0yd.C', 
    'ROLE_MEM', 
    1, 
    8, 
    to_date('24/06/10', 'RR/MM/DD'), 
    to_date('00/03/26', 'RR/MM/DD'), 
    '010', 
    0, 
    null, 
    null, 
    null, 
    null, 
    0
);
insert into member values(
    'ej.kh.kim@gmail.com', 
    '이제이', 
    '$2a$10$/3ujw9k68wsumBdkyG3jN.3QUnFUDskHIMQwGQE3hV4ObQxGqtOL.', 
    'ROLE_MEM', 
    1, 
    8, 
    to_date('24/06/10', 'RR/MM/DD'), 
    to_date('24/06/09', 'RR/MM/DD'), 
    '010', 
    0, 
    null, 
    null, 
    null, 
    null, 
    0
);
commit;
-- 회원 탈퇴 트리거
--create or replace NONEDITIONABLE TRIGGER trg_member_quit
--    BEFORE delete ON member
--    REFERENCING OLD AS OLD
--    FOR EACH ROW
--DECLARE
--BEGIN
--   insert into quit_member values (
--   :OLD.MEM_EMAIL, 
--   :OLD.MEM_NICK, 
--   :OLD.MEM_ROLE, 
--   :OLD.MEM_ENABLED, 
--   :OLD.MEM_TYPE, 
--   :OLD.MEM_JOIN_DATE, 
--   default, 
--   :OLD.MEM_BIRTH
--   );
--END;
--/
-- 상품 목록 추가
---- 테마
insert into item values ('T0', '테마1', 1000, null, null, 'E54B4B');
insert into item values ('T1', '테마2', 1000, null, null, 'C943CC');
insert into item values ('T2', '테마3', 1000, null, null, 'FFA7C5');
insert into item values ('T3', '테마4', 1000, null, null, 'E5794B');
insert into item values ('T4', '테마5', 1000, null, null, '4BC9E5');
insert into item values ('T5', '테마6', 1000, null, null, 'E5C34B');
insert into item values ('T6', '테마7', 1000, null, null, 'CDF263');
insert into item values ('T7', '테마8', 1000, null, null, '2EB67D');
insert into item values ('T8', '테마9', 1000, null, null, '4B76E5');
insert into item values ('T9', '테마10', 1000, null, null, '13358F');
---- 폰트
insert into item values('F0', '폰트 30일', 2000, 30, null, null);
insert into item values('F1', '폰트 90일', 6000, 90, 10, null);
commit;

-- 장바구니 추가
begin
    for i in 0..9 loop
        insert into cart values('T'||i, 'seojw0730@naver.com');
    end loop;
    commit;
end;
/
