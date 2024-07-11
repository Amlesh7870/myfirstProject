package in.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MyController {

	@GetMapping("/first")
	public ModelAndView getFirst() {
		return new ModelAndView("hello");
	}
	
}
