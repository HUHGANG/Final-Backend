package com.ssafy.ssafyhome.domain.entity;

import com.ssafy.ssafyhome.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {

  private int id;
  private String email;
  private String password;
  private String name;
  private Role role;
  private String createdAt;

}
