package com.ssafy.ssafyhome.domain.dto;

import com.ssafy.ssafyhome.domain.entity.Ssafy;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class HomeSsafyListResDto {

  private int currentPage;
  private int totalPage;
  private int totalCnt;
  private List<Ssafy> homeList;

}
