package com.ssafy.ssafyhome.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Notice {

  private int id;
  private String title;
  private String content;
  private Member author;
  private String createdAt;
  private String updatedAt;

}
