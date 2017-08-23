package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hr.model.InfoServiceTypeService;

@Controller
@RequestMapping("/servicetype")
public class InfoServiceTypeController {

	@Autowired
    private InfoServiceTypeService infoServiceTypeService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String depList() throws Exception {
		System.out.println(infoServiceTypeService.iSTList());
		return infoServiceTypeService.iSTList().toString();
	}
	
}
