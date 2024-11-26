package com.ssafy.ssafyhome.domain.entity;

import com.ssafy.ssafyhome.domain.enums.Role;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Member {

  private int id;
  private String email;
  private String password;
  private String name;
  private Role role;
  private String createdAt;

  @Builder
  public Member(int id, String email, String password, String name, Role role, String createdAt) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.name = name;
    this.role = role;
    this.createdAt = createdAt;
  }
}
