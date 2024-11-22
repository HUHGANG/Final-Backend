package com.ssafy.ssafyhome.domain.dto;

import com.ssafy.ssafyhome.domain.entity.Dabang;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class HomeListResDto {

  private int currentPage;
  private int totalPage;
  private int totalCnt;
  private List<Dabang> homeList;

}
