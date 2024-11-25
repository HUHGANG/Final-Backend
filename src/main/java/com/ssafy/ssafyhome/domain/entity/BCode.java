package com.ssafy.ssafyhome.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class BCode {

  private long bCode;
  private String sido;
  private String gugun;
  private String dong;
}
