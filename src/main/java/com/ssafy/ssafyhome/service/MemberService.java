package com.ssafy.ssafyhome.service;

import com.ssafy.ssafyhome.domain.dto.LoginReqDto;
import com.ssafy.ssafyhome.domain.dto.LoginResDto;
import com.ssafy.ssafyhome.domain.dto.SignupReqDto;
import com.ssafy.ssafyhome.domain.entity.Member;
import com.ssafy.ssafyhome.domain.enums.Role;
import com.ssafy.ssafyhome.exception.BadRequestException;
import com.ssafy.ssafyhome.mapper.MemberMapper;
import com.ssafy.ssafyhome.util.EmailUtil;
import com.ssafy.ssafyhome.util.JWTUtil;
import com.ssafy.ssafyhome.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberMapper memberMapper;
  private final JWTUtil jwtUtil;
  private final RedisUtil redisUtil;
  private final EmailUtil emailUtil;

  public void isExist(String email) {
    if (memberMapper.isExist(email)) {
      throw new BadRequestException("이미 존재하는 이메일입니다.");
    }
  }

  public void sendVerificationEmail(String email) {
    isExist(email);

    String code = generateVerificationCode();
    emailUtil.sendEmail(email, code);

    String key = "code:" + email;
    redisUtil.setex(key, code, 180);
  }

  public void verifyEmail(String email, String code) {
    String key = "code:" + email;

    if (!code.equals(redisUtil.get(key))) {
      throw new BadRequestException("인증번호가 일치하지 않습니다.");
    }
  }

  public Member selectById(int id) {
    return memberMapper.selectById(id);
  }

  public void signup(SignupReqDto dto) {
    // 이메일 중복 확인
    isExist(dto.getEmail());

    // 인증번호 확인
    verifyEmail(dto.getEmail(), dto.getCode());

    String hashPassword = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());

    Member member = new Member();
    member.setEmail(dto.getEmail());
    member.setPassword(hashPassword);
    member.setName(dto.getName());
    member.setRole(Role.USER);

    memberMapper.insertMember(member);
  }

  public LoginResDto login(LoginReqDto dto) {
    Member member = memberMapper.selectByEmail(dto.getEmail());

    if (member == null) {
      throw new BadRequestException("로그인 실패");
    }

    boolean checkPassword = BCrypt.checkpw(dto.getPassword(), member.getPassword());
    if (!checkPassword) {
      throw new BadRequestException("로그인 실패");
    }

    String accessToken = jwtUtil.createAccessToken(member);
    String refreshToken = jwtUtil.createRefreshToken(member);

    return new LoginResDto(accessToken, refreshToken);
  }

  private String generateVerificationCode() {
    return String.format("%06d", new Random().nextInt(1000000));
  }
}
