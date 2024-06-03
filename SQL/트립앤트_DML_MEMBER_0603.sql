desc member;
select * from member;
insert into member values(
    'seojw0730@naver.com', 
    '한글', 
    '$2a$12$6jbDn8AQYh/5V1OI7ok4UuW.Wurj0vOGZqpmqYzfOLEsVez6BNn5O', 
    'ROLE_MEM', 
    1, 
    'T', 
    to_date('24/06/03', 'RR/MM/DD'), 
    to_date('97/07/30', 'RR/MM/DD')
);

update member set mem_password = '$2a$12$6jbDn8AQYh/5V1OI7ok4UuW.Wurj0vOGZqpmqYzfOLEsVez6BNn5O' where mem_nick = '한글';
commit;
create or replace NONEDITIONABLE TRIGGER trg_member_quit
    BEFORE delete ON member
    REFERENCING OLD AS OLD
    FOR EACH ROW
DECLARE
BEGIN
   insert into quit_member values (
   :OLD.MEM_EMAIL, 
   :OLD.MEM_NICK, 
   :OLD.MEM_ROLE, 
   :OLD.MEM_ENABLED, 
   :OLD.MEM_TYPE, 
   :OLD.MEM_JOIN_DATE, 
   default, 
   :OLD.MEM_BIRTH
   );
END;
/
desc member;
desc quit_member;
select * from quit_member;
commit;
select * from member;
delete from member;
