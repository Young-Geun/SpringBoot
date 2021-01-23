package com.choi.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choi.web.dao.GuestbookDao;
import com.choi.web.domain.GuestbookVo;

@Service
@Transactional
public class GuestbookServiceImpl implements GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;
		
	@Override
	public List<GuestbookVo> getGuestbookList() {
		return guestbookDao.selectGuestbookList();
	}

	@Override
	public GuestbookVo getGuestbook(int guestbookId) {
		return guestbookDao.selectGuestbook(guestbookId);
	}

	@Override
	public void addGuestbook(GuestbookVo guestbookVo) {
		guestbookDao.insertGuestbook(guestbookVo);
	}
	
	@Override
	public void modifyGuestbook(GuestbookVo guestbookVo) {
		guestbookDao.updateGuestbook(guestbookVo);
	}
	
	@Override
	public void removeGuestbook(int guestbookId) {
		guestbookDao.deleteGuestbook(guestbookId);
	}

}
