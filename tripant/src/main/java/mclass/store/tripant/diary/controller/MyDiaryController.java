package mclass.store.tripant.diary.controller;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import mclass.store.tripant.diary.domain.DiaryBoardEntity;
import mclass.store.tripant.diary.domain.DiaryPostEntity;
import mclass.store.tripant.diary.service.DiaryService;
import mclass.store.tripant.member.model.service.MemberService;


@Controller
public class MyDiaryController {

	@Autowired
	private DiaryService diaryService;
    @Autowired
	private MemberService memberService; // MemberService 주입
	
	// 나의 글보기
	@GetMapping("/my/diary") // 나의 여행기(비공개글) 목록 가져오기
	public String mydiary(Model model) {
		diaryService.selectDiaryList();
		model.addAttribute("diaries", diaryService.selectDiaryList());
		System.out.println("======mydiary controller===" + diaryService.selectDiaryList());
		return "diary/my/my_board";
	}

//		글쓰기 페이지 보이기
	@GetMapping("/my/write")
	public String myWrite() {
		return "diary/my/my_write";
	}

    // 글쓰기 처리
    @PostMapping("/write/post")
    public ResponseEntity<?> createDiary(@RequestBody DiaryPostEntity diaryForm) {

        // 로그인한 사용자의 이메일 가져오기
        String diaryMemEmail = memberService.myInfo(diaryForm.getDiaryMemEmail()).get("MEM_EMAIL").toString();

        // DiaryPostEntity 객체 생성 및 필드 설정
        DiaryBoardEntity diary = new DiaryBoardEntity();
        diary.setDiaryMemEmail(diaryMemEmail);
        diary.setDiaryPlanId(diaryForm.getDiaryPlanId());
        diary.setDiaryTitle(diaryForm.getDiaryTitle());
        diary.setDiaryContent(diaryForm.getDiaryContent());
        diary.setDiaryViews(diaryForm.getDiaryViews() == null ? 0 : diaryForm.getDiaryViews()); // 기본값 설정
        diary.setDiaryTheme(diaryForm.getDiaryTheme());

        try {
            // 날짜 파싱 및 설정
            LocalDate localDate = LocalDate.parse((CharSequence) diaryForm.getDiaryDate(), DateTimeFormatter.ISO_DATE);
            diary.setDiaryDate(java.sql.Date.valueOf(localDate));
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Invalid date format. Please use 'yyyy-MM-dd'.");
        }

        // 공개 여부 설정
        diary.setDiaryOpen(diaryForm.getDiaryOpen() != null ? "0" : "1"); // true면 공개 ("0"), false면 비공개 ("1")

        // DiaryPostEntity 저장 (diaryService를 통해 저장 후 diary 객체는 DB에 저장된 후 자동으로 생성된 ID가 채워짐)
        diary = diaryService.save(diary);

        // 저장된 DiaryPostEntity를 ResponseEntity로 반환
        return ResponseEntity.ok().body(diary);
    }
}