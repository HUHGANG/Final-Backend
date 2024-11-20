package com.ssafy.ssafyhome.mapper;

import com.ssafy.ssafyhome.domain.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {

  List<Notice> selectNoticeList(@Param("offset") int offset,
                                @Param("size") int size);

  Notice selectNoticeDetail(@Param("id") int id);

  void insertNotice(@Param("notice") Notice notice);
}