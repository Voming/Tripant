package mclass.store.tripant.diary.controller;


import java.security.Principal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import mclass.store.tripant.diary.domain.DiaryBoardEntity;
import mclass.store.tripant.diary.domain.WritePlanTitleEntity;
import mclass.store.tripant.diary.service.DiaryService;




@Controller
@RequestMapping("/my")
public class MyDiaryController {

	@Autowired
	private DiaryService diaryService;

    @GetMapping("/diary") // 특정 사용자가 작성한 모든 글 조회
    public ModelAndView mydiary(ModelAndView mv,String diaryMemEmail) {
 //       List<DiaryBoardEntity> diaries = diaryService.selectMyDiaryList(userEmail, maxNum);
    		mv.setViewName("diary/my/my_board");
        return mv;
    }

	@PostMapping("/diary")// 특정 사용자가 작성한 모든 글 조회 더보기
	public String mydiaryMore(Model model,String email,Integer clickNum) {
		int maxNum = (clickNum + 1) * 4;
		model.addAttribute("diaries", diaryService.selectDiaryList(email, maxNum));
		return "diary/my/my_board";
	}
	
	
	@GetMapping("/post")
	public ModelAndView showDiaryForm(Principal pricipal) {
		ModelAndView mv = new ModelAndView();
		List<WritePlanTitleEntity> plans = diaryService.getAllPlans(pricipal.getName());
		mv.addObject("plans", plans);
		mv.addObject("diaryEntry", new WritePlanTitleEntity()); // 폼 데이터를 위한 빈 객체 추가
		mv.setViewName("diary/my/my_write");
		return mv;
	}
    // 글쓰기 처리
    @PostMapping("/post")
    @ResponseBody
    public ResponseEntity<?> createDiary(@RequestBody DiaryBoardEntity diaryForm, Principal pricipal) {
	
    	diaryForm.setDiaryMemEmail(pricipal.getName());
    	diaryForm.setDiaryViews(diaryForm.getDiaryViews() == null ? 0 : diaryForm.getDiaryViews()); // 기본값 설정

        // 공개 여부 설정
        diaryForm.setDiaryOpen(diaryForm.getDiaryOpen() != null ? "0" : "1"); // true면 공개 ("0"), false면 비공개 ("1")

        // DiaryPostEntity 저장 (diaryService를 통해 저장 후 diary 객체는 DB에 저장된 후 자동으로 생성된 ID가 채워짐)
        diaryForm = diaryService.save(diaryForm);

        // 저장된 DiaryPostEntity를 ResponseEntity로 반환
        return ResponseEntity.ok().body(diaryForm);
    }

}
