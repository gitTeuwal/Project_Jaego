package com.jaego.web.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jaego.web.dto.FindDto;
import com.jaego.web.service.MemberServiceImpl;

@Controller
public class FindController {

	@Autowired
	private MemberServiceImpl memberService;
	
	@Autowired
	private FindDto findDto;
	
	public void setMemberService(MemberServiceImpl memberService) {
		this.memberService = memberService;
	}
	

	@RequestMapping(value = "/findPage", method = RequestMethod.GET)
	public String findPage(Model model, HttpSession session) {

		return "/member/memberFind";
	}

	@RequestMapping(value = "/findEmail", method = RequestMethod.POST)
	@ResponseBody
	public String findEmail(@ModelAttribute("findDto") @Valid FindDto findDto,
			BindingResult bindingResult, Errors errors, Model model) {

		FindDto resultMem = memberService.findEmail(findDto);
		
		String result = null;

		if (resultMem != null) {
			if (resultMem.getMemberEmail() != null) {
				result = resultMem.getMemberEmail();
			}
		}else {
			result = "fail";
		}

		System.out.println("웅: " + result);

		return result;
		
		// 여기는 이메일 찾는 부분이고
	}

	
	
	@RequestMapping(value = "/findPassword", method = RequestMethod.POST)
	public String findPassword(@ModelAttribute("findDto") @Valid FindDto findDto,
			BindingResult bindingResult, Model model) {

		memberService.findPassword(findDto);
		
		return "member/memberFind"; 
		
		//그롬 굳이 reponsebody를 사용 안하고 redirect로 허묜 되는건가요?응답을 jsp선택할거면 return "파일명이고"
		//문자열 자체를 응답하려면 responsebody를 사용하는 거고 
		//딱히 줄 게 없는데 굳이 responsebody를 사용할 필요는 없지 않나요? 아하 넵 알겠습ㄴ디다!넵!감사합니당화이팅!
	
		// 여기가 비번 찾는부분인데 비번을 새로운 비번으로 변경하는거라 return이 없는데 reponsebody를 어떻게 쓸 수 있을가요?
		//응답할 내용이 없는데 responsebody를 사용할 필요가 있나요?
		//redirect하면 끝 인거같은데

	}

}
