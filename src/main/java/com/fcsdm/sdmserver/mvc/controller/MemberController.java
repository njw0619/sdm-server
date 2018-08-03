package com.fcsdm.sdmserver.mvc.controller;

import java.util.List;

import com.fcsdm.sdmserver.mvc.model.dto.Member;
import com.fcsdm.sdmserver.mvc.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("members")
public class MemberController {
	
private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService memberService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getMembers(Model model) {
		List<Member> members = memberService.getMembers();
		model.addAttribute("members", members);		
		return "member/list";
	}
	
}
