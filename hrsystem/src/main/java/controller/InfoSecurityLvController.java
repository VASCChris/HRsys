package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hr.model.InfoSecurityLvService;

@Controller
@RequestMapping("/securitylv")
public class InfoSecurityLvController {

	@Autowired
    private InfoSecurityLvService infoSecurityLvService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String depList() throws Exception {
		return infoSecurityLvService.iSLList().toString();
	}
}
