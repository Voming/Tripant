package mclass.store.tripant.plan.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

//@RestController  - @ResponseBody
@Controller
public class PlaningController {

//	@Autowired
//	private PlanService planService;

	@PostMapping("/planing")
	@ResponseBody
	 public String planing(@RequestBody String a){
        System.out.println("param : " + a);
        return "aaa";
    }
}
