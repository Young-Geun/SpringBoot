package com.choi.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String main(Model model) {
		List<String> list = Arrays.asList("메뉴_1", "메뉴_2", "메뉴_3", "기타 메뉴");
		model.addAttribute("menuList", list);
		return "main";
	}

}
