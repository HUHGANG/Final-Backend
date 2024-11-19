package com.ssafy.ssafyhome.controller;

import com.ssafy.ssafyhome.domain.dto.LoginReqDto;
import com.ssafy.ssafyhome.domain.dto.LoginResDto;
import com.ssafy.ssafyhome.domain.dto.SignupReqDto;
import com.ssafy.ssafyhome.domain.dto.VerifyEmailReqDto;
import com.ssafy.ssafyhome.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Tag(name = "회원 인증 컨트롤러", description = "로그인 로그아웃, 토큰처리등 회원의 인증관련 처리하는 클래스.")
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/check-email")
  @Operation(summary = "이메일 중복 확인", description = "해당 이메일이 이미 존재하는지 확인")
  public ResponseEntity<?> checkEmailDuplicate(String email) {
    memberService.isExist(email);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/send-verification")
  @Operation(summary = "인증번호 전송", description = "이메일 확인을 위한 인증번호 전송")
  public ResponseEntity<?> sendVerificationEmail(String email) {
    memberService.sendVerificationEmail(email);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/verify-email")
  @Operation(summary = "인증번호 확인", description = "이메일로 전송한 인증번호와 일치한지 확인")
  public void verifyEmail(@RequestBody VerifyEmailReqDto dto) {
    memberService.verifyEmail(dto.getEmail(), dto.getCode());
  }

  @PostMapping("/signup")
  @Operation(summary = "회원가입", description = "이메일, 이름, 비밀번호 정보로 회원가입")
  public ResponseEntity<String> signup(@RequestBody SignupReqDto dto) {
    memberService.signup(dto);
    return ResponseEntity.ok("회원가입이 완료되었습니다.");
  }

  @PostMapping("/login")
  @Operation(summary = "로그인", description = "이메일과 비밀번호를 이용하여 로그인 처리.")
  public LoginResDto login(@RequestBody LoginReqDto dto) {
    return memberService.login(dto);
  }
}