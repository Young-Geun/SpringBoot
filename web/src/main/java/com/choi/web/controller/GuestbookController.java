package com.choi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.choi.web.domain.GuestbookVo;
import com.choi.web.service.GuestbookService;

@RestController
public class GuestbookController {

	@Autowired
	GuestbookService guestbookService;

	@GetMapping("/guestbook/list")
	public @ResponseBody List<GuestbookVo> getGuestbookList() {
		return guestbookService.getGuestbookList();
	}

}
