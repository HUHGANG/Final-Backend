package com.ssafy.ssafyhome.mapper;

import com.ssafy.ssafyhome.domain.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NoticeMapper {

  void insertNotice(@Param("notice") Notice notice);
}
