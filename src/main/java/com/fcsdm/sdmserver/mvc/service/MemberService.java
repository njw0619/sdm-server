package com.fcsdm.sdmserver.mvc.service;

import java.util.List;

import com.fcsdm.sdmserver.mvc.mapper.MemberMapper;
import com.fcsdm.sdmserver.mvc.model.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	MemberMapper memberMapper;
	
	public List<Member> getMembers(){
		return memberMapper.selectMembers();
	}
	
	public Member getMember(int id) {
		return memberMapper.selectMember(id);
	}
	
	public Member getMemberByName(String name) {
		return memberMapper.selectMemberByName(name);
	}

}
