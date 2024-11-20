package com.ssafy.ssafyhome.mapper;

import com.ssafy.ssafyhome.domain.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {

  int countTotalNotice();

  List<Notice> selectNoticeList(@Param("offset") int offset,
                                @Param("size") int size);

  Notice selectNoticeDetail(@Param("id") int id);

  void insertNotice(@Param("notice") Notice notice);

  void updateNotice(@Param("notice") Notice notice);

  void deleteNotice(@Param("id") int id);
}
