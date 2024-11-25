package com.ssafy.ssafyhome.domain.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Image {

  private int id;
  private int homeId;
  private String url;
  private Date createdAt;
  private Date updatedAt;

  @Builder

  public Image(int id, int homeId, String url, Date createdAt, Date updatedAt) {
    this.id = id;
    this.homeId = homeId;
    this.url = url;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
