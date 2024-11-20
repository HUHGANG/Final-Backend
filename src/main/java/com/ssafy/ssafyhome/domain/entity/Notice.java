package com.ssafy.ssafyhome.domain.entity;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Notice {

  private int id;
  private String title;
  private String content;
  private int authorId;
  private String authorName;
  private Timestamp createdAt;
  private Timestamp updatedAt;

  @Builder
  public Notice(int id, String title, String content, int authorId, String authorName, Timestamp createdAt, Timestamp updatedAt) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.authorId = authorId;
    this.authorName = authorName;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
