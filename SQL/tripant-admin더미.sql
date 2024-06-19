insert INTO diary_reports values (79,'seojw0730@gmail.com',1);

select * from diary_reports;
commit;
desc member;

select * from diary;
select * from diary_likes;

insert INTO diary_likes values(74,'gyrua34@gmail.com');
-----------신고수 초기화
select diary_id, diary_title,to_char(diary_date,'yyyy-MM-dd') diary_date,reports,mem_nick from 
	((select a.diary_id, a.diary_title diary_title, a.diary_date diary_date ,a.diary_mem_email, b.reports
	from DIARY a
	left join (select count (mem_email) reports , diary_id from diary_reports group by diary_id) b on a.diary_id= b.diary_id))
	join member on diary_mem_email=mem_email
	where reports is not null
    ;
    
 insert into diary_reports values(74,'dpdls898@naver.com',1);   
  insert into diary_reports values(79,'gyrua34@gmail.com',1); 
select* from diary_reports;
delete from diary_reports where diary_id=79;
select * from member;

