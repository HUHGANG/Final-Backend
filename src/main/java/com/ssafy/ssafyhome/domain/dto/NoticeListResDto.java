package com.ssafy.ssafyhome.domain.dto;

import com.ssafy.ssafyhome.domain.entity.Notice;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class NoticeListResDto {

  private int currentPage;
  private int totalPage;
  private int totalNotice;
  private List<Notice> noticeList;

}
