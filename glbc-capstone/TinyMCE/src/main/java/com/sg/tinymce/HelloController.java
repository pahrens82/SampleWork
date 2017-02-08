package com.sg.tinymce;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class HelloController {
        
    public HelloController() {
    }
	
	@RequestMapping(value="/hello", method=RequestMethod.GET)
    public String homePage(Map<String, Object> model) {
        return "hello";
    }
	
	@RequestMapping(value="new_page", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ResponseBody
	public String submittedTinyMCE(@RequestBody String string) {
		
		return string;
	}
}