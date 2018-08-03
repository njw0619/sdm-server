package com.fcsdm.sdmserver.mvc.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fcsdm.sdmserver.mvc.mapper.MembershipMapper;
import com.fcsdm.sdmserver.mvc.model.dto.Membership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MembershipService {
	
	@Autowired
	MembershipMapper membershipMapper;

	public List<Membership> getMemberships(){
		return membershipMapper.selectMemberships();
	}

	public List<Membership> getUnpaidMemberList(){
		return membershipMapper.selectUnpaidMemberList();
	}
	
	public int addMembership(int memberId, String occurMonth){
		return membershipMapper.insertMembership(memberId, occurMonth);
	}

	public String getUnpaidMemberListToString(){

		List<Membership> memberships = this.getUnpaidMemberList();
		List<Membership> unpaid = new ArrayList<Membership>();

		for(Membership membership : memberships) {

			membership.setOccupation("W".equals(membership.getOccupation()) ? "직장인" : "학생");
			membership.setGrade("1".equals(membership.getGrade()) ? "정규" : "명예");

			String occurMonth = membership.getOccurMonth();
			if(StringUtils.isEmpty(occurMonth)) membership.setOccurMonth("납부이력 없음");
			else{
				membership.setOccurMonth(occurMonth.substring(0, 4) + "." + StringUtils.trimLeadingCharacter(occurMonth.substring(4, 6), '0'));
			}
			
			unpaid.add(membership);
		}

		StringBuilder memo = new StringBuilder();
		memo.append("회비 미납자 명단\n");
		for(Membership membership : unpaid) {
			memo.append(membership.getName() + "(" + membership.getOccupation() + ") " + membership.getOccurMonth() + "\n");
		}

		return memo.toString();
	}
}
