package com.ssafy.ssafyhome.intercepter;

import com.ssafy.ssafyhome.annotation.RoleCheck;
import com.ssafy.ssafyhome.domain.entity.Member;
import com.ssafy.ssafyhome.exception.ForbiddenException;
import com.ssafy.ssafyhome.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;

@Component
public class RoleCheckInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (handler instanceof HandlerMethod handlerMethod) {
      RoleCheck roleCheck = handlerMethod.getMethodAnnotation(RoleCheck.class);
      if (roleCheck == null) {
        // 메서드에 없으면 클래스 레벨에서 확인
        roleCheck = handlerMethod.getBeanType().getAnnotation(RoleCheck.class);
      }

      if (roleCheck != null) {
        Member member = (Member) request.getAttribute("authenticatedMember");

        if (member == null) {
          throw new UnauthorizedException("로그인 후 이용 가능합니다.");
        }

        if (member.getRole() == null) {
          throw new ForbiddenException("권한 없음");
        }

        boolean hasAccess = Arrays.stream(roleCheck.value())
                .anyMatch(role -> role.equals(member.getRole()));

        if (!hasAccess) {
          throw new ForbiddenException("권한 없음");
        }

        return true;
      }
    }
    return true;
  }
}
