package com.ssafy.ssafyhome.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class Image {

  private int id;
  private int homeId;
  private String url;
  private Date createdAt;
  private Date updatedAt;
}
