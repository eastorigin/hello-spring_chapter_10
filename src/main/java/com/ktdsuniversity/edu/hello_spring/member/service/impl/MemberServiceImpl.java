package com.ktdsuniversity.edu.hello_spring.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.hello_spring.member.dao.MemberDao;
import com.ktdsuniversity.edu.hello_spring.member.service.MemberService;
import com.ktdsuniversity.edu.hello_spring.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDao memberDao;

	@Override
	public boolean insertNewMember(MemberVO memberVO) {
		int emailCount = memberDao.selectEmailCount(memberVO.getEmail());
		if(emailCount > 0) {
			throw new IllegalArgumentException("Email이 이미 사용 중입니다");
		}
		int insertCount = memberDao.insertNewMember(memberVO);
		return insertCount > 0;
	}
	
	@Override
	public boolean checkAvailableEmail(String email) {
		return this.memberDao.selectEmailCount(email) == 0;
	}
}
