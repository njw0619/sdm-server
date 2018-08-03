package com.fcsdm.sdmserver.mvc.mapper;

import com.fcsdm.sdmserver.mvc.model.dto.Membership;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MembershipMapper {
    List<Membership> selectMemberships();
    List<Membership> selectUnpaidMemberList();
    int insertMembership(@Param("memberId") int memberId, @Param("occurMonth") String occurMonth);
}
