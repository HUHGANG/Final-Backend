package com.ssafy.ssafyhome.domain.dto;

import lombok.Getter;

@Getter
public class VerifyEmailReqDto {

    private String email;
    private String code;
}
