package com.fcsdm.sdmserver.mvc.mapper;

import com.fcsdm.sdmserver.mvc.model.dto.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Peter on 2018-02-06.
 */
@Mapper
public interface MemberMapper {
	public List<Member> selectMembers();
    public Member selectMember(@Param("id") int id);
    public Member selectMemberByName(@Param("name") String name);
}
