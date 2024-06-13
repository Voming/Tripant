--회원조회 join
select a.MEM_EMAIL  MEM_EMAIL  ,a.MEM_NICK MEM_NICK, a.MEM_JOIN_DATE MEM_JOIN_DATE, a.MEM_ROLE  MEM_ROLE , b.MEM_QUIT_DATE
from member a
left join quit_member b
on a.MEM_EMAIL=b.MEM_EMAIL;

select * from member;

--게시글 조회 join / 게시글 별 좋아요 수 count
select count(mem_email) from diary_likes ;

--수정 중
(select a.diary_id, a.diary_title diary_title, a.diary_date diary_date, a.diary_views diary_views,a.diary_mem_email, b.likes
from DIARY a
left join (select count (mem_email) likes , diary_id from diary_likes group by diary_id) b on a.diary_id= b.diary_id)
;
--아이디별 갯수
select count (mem_email) , diary_id from diary_likes group by diary_id;

--게시글 조회 닉네임,좋아요 join
select diary_id, diary_title ,to_char(diary_date,'yyyy-MM-dd') diary_date,diary_views,likes, mem_nick from 
((select a.diary_id, a.diary_title diary_title, a.diary_date diary_date, a.diary_views diary_views,a.diary_mem_email, b.likes
from DIARY a
left join (select count (mem_email) likes , diary_id from diary_likes group by diary_id) b on a.diary_id= b.diary_id))
join member on diary_mem_email=mem_email
;

select count(d.diary_id), d.mem_email,f.* from diary_likes d join (select a.diary_id, a.diary_title diary_title, a.diary_date diary_date, a.diary_views diary_views, b.mem_nick mem_nick , b.mem_email mem_email
from DIARY a
left join MEMBER b on a.diary_mem_email= b.mem_email) f on d.mem_email=f.mem_email GROUP by d.mem_email,f.*
;
select * from diary_likes;
select count(*) from diary_likes;
