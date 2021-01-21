package com.choi.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.choi.web.domain.GuestbookVo;

@Mapper
public interface GuestbookDao {

	public List<GuestbookVo> selectGuestbookList();

}
