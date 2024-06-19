package mclass.store.tripant.diary.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;



import mclass.store.tripant.diary.domain.DiaryBoardEntity;
import mclass.store.tripant.diary.service.DiaryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/diary")
public class DiaryController {
	@Autowired
	private DiaryService diaryService;

	// 전체 글보기(공개 글)
	@GetMapping("")
	//modelAndView로 db두번 갔다오지 않게 하기
	public ModelAndView diary(ModelAndView mv) {
		mv.addObject("diaries", diaryService.selectDiaryList());
		mv.setViewName("diary/diary_board");
		//System.out.println("======dddd" + diaryService.selectDiaryList());
		return mv;
	}

	// 글 상세보기
    @GetMapping("/read/{id}")
    public String diartRead(@PathVariable Long id, Model model) {
        DiaryBoardEntity diary = diaryService.findById(id);
        model.addAttribute("diary", diary);
        return "diary/diary_read";
	}
    // 좋아요 기능
    @PostMapping("/like/{diaryId}")
    @ResponseBody
    public Map<String, Object> likeDiary(@PathVariable Long diaryId) {
        boolean success = diaryService.incrementLikes(diaryId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        return response;
    }
}
    // 더보기
//@GetMapping
//public String getLists(Model model) {
	// 처음에는 첫 번째 페이지의 글 8개를 불러옴
//	List<>
//}
    //    @PostMapping("/report")
//    public int report() {
//        int result = diaryService.
//        return result;
//    }

    
//}

