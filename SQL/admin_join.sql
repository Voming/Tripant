--회원조회 join
select a.MEM_EMAIL  MEM_EMAIL  ,a.MEM_NICK MEM_NICK, a.MEM_JOIN_DATE MEM_JOIN_DATE, a.MEM_ROLE  MEM_ROLE , b.MEM_QUIT_DATE
from member a
left join quit_member b
on a.MEM_EMAIL=b.MEM_EMAIL;

select * from member;

--게시글 조회 join / 게시글 별 좋아요 수 count

select a.diary_title diary_title, a.diary_date diary_date, a.diary_views diary_views, b.mem_nick mem_nick , b.mem_email
from DIARY a
left join MEMBER b on a.diary_mem_email= b.mem_email
;
