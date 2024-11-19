package com.ssafy.ssafyhome.mapper;

import com.ssafy.ssafyhome.domain.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

  void insertMember(Member member);

  Member selectById(int id);

  Member selectByEmail(String email);

  boolean isExist(String email);
}
