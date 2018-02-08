package edu.scsu.div.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import edu.scsu.div.model.GroupRequest;
import edu.scsu.div.service.GroupService;



@RestController
@CrossOrigin
public class Controller {
	
	@Autowired
	GroupService groupService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public @ResponseBody String welcome(){	
		return "welcome to the diversity group" ;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/group")
	public @ResponseBody List<List<Integer>>  group(@RequestBody GroupRequest request){	
		//return "welcome to the diversity group" 
		return groupService.group(request);
	}

}
