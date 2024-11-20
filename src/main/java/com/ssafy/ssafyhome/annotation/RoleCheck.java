package com.ssafy.ssafyhome.annotation;

import com.ssafy.ssafyhome.domain.enums.Role;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RoleCheck {
  Role[] value();
}
