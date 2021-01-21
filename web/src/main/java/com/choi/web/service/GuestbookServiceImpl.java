package com.choi.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choi.web.dao.GuestbookDao;
import com.choi.web.domain.GuestbookVo;

@Service
public class GuestbookServiceImpl implements GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;
		
	@Override
	public List<GuestbookVo> getGuestbookList() {
		return guestbookDao.selectGuestbookList();
	}

}
