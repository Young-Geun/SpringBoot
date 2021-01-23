package com.choi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.choi.web.domain.GuestbookVo;
import com.choi.web.service.GuestbookService;

@RestController
public class GuestbookController {

	@Autowired
	GuestbookService guestbookService;

	@GetMapping("/guestbooks")
	public @ResponseBody List<GuestbookVo> getGuestbookList() {
		return guestbookService.getGuestbookList();
	}
	
	@GetMapping("/guestbook/{id}")
	public @ResponseBody GuestbookVo getGuestbook(@PathVariable ("id") int guestbookId) {
		return guestbookService.getGuestbook(guestbookId);
	}
	
	@PostMapping("/guestbook")
	public void addMember(@RequestBody GuestbookVo guestbookVo) {
		System.out.println(guestbookVo.getName() + " : " + guestbookVo.getMessage());
		guestbookService.addGuestbook(guestbookVo);
	}
	
	@PutMapping("/guestbook/{id}")
	public void modifyGuestbook(@PathVariable ("id") int guestbookId, @RequestBody GuestbookVo guestbookVo) {
		guestbookVo.setId(guestbookId);
		guestbookService.modifyGuestbook(guestbookVo);
	}
	
	@DeleteMapping("/guestbook/{id}")
	public void removeGuestbook(@PathVariable ("id") int guestbookId) {
		guestbookService.removeGuestbook(guestbookId);
	}

}
