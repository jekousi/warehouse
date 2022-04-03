package com.pluto.warehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pluto.warehouse.constant.UrlConstant;

import lombok.extern.slf4j.Slf4j;

/**
 * ログインコントロールー
 * 
 * @author pluto
 *
 */
@Controller
@Slf4j
public class LoginInController {
    
//    @Autowired
//    private LoginService loginService;

	@GetMapping(UrlConstant.URL_LOGIN)
	public String init(Model model) {

	    log.info("abc");
	    
	    model.addAttribute("message", "目ss－次");
	    
//	    loginService.login();
	    
		return UrlConstant.VIEWID_LOGIN;
	}

}
