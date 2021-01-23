package com.choi.web.service;

import java.util.List;

import com.choi.web.domain.GuestbookVo;

public interface GuestbookService {

	public List<GuestbookVo> getGuestbookList();
	
	public GuestbookVo getGuestbook(int guestbookId);
	
	public void addGuestbook(GuestbookVo guestbookVo);
	
	public void modifyGuestbook(GuestbookVo guestbookVo);
	
	public void removeGuestbook(int guestbookId);
		
}
