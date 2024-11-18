package com.ssafy.ssafyhome.service;

import com.ssafy.ssafyhome.domain.dto.LoginReqDto;
import com.ssafy.ssafyhome.domain.dto.LoginResDto;
import com.ssafy.ssafyhome.domain.entity.Member;
import com.ssafy.ssafyhome.domain.enums.Role;
import com.ssafy.ssafyhome.exception.BadRequestException;
import com.ssafy.ssafyhome.mapper.MemberMapper;
import com.ssafy.ssafyhome.util.JWTUtil;
import com.ssafy.ssafyhome.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberMapper memberMapper;
  private final JWTUtil jwtUtil;
  private final RedisUtil redisUtil;

  public void checkEmailDuplicate(Member member) {

  }

  public void sendVerificationEmail() {

  }

  public void verifyEmail(Member member) {

  }

  public void isExist(String email) {
    if (memberMapper.isExist(email)) {
      // 예외 추가
      throw new BadRequestException("이미 존재하는 이메일입니다.");
    }
  }
  public Member selectById(int id) {
    return memberMapper.selectById(id);
  }

  public void signup(Member member) {
    // 이메일 중복 확인

    // 인증번호 확인

    // 회원가입
    String hashPassword = BCrypt.hashpw(member.getPassword(), BCrypt.gensalt());
    member.setPassword(hashPassword);

    member.setRole(Role.USER);

    memberMapper.insertMember(member);
  }

  public LoginResDto login(LoginReqDto dto) {
    Member member = memberMapper.selectByEmail(dto.getEmail());

    boolean checkPassword = BCrypt.checkpw(dto.getPassword(), member.getPassword());
    if(!checkPassword){
      throw new RuntimeException();
    }

    String accessToken = jwtUtil.createAccessToken(member);
    String refreshToken = jwtUtil.createRefreshToken(member);
    log.debug("access token : {}", accessToken);
    log.debug("refresh token : {}", refreshToken);

    return new LoginResDto(accessToken, refreshToken);
  }

  public void logout(){

  }
}
