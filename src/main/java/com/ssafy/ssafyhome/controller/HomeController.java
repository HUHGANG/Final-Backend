package com.ssafy.ssafyhome.controller;

import com.ssafy.ssafyhome.domain.entity.Dabang;
import com.ssafy.ssafyhome.service.HomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
@Tag(name = "다방 크롤링 매물 조회 컨트롤러", description = "다방 크롤링 매물 조회")
public class HomeController {

  private final HomeService homeService;

  @GetMapping("/dabang")
  @Operation(summary = "다방 매물 리스트 조회", description = "")
  public List<Dabang> selectDabangHomeList(@RequestParam(defaultValue = "35.2") float wnLat,
                                           @RequestParam(defaultValue = "126.8") float wnLng,
                                           @RequestParam(defaultValue = "35.18") float esLat,
                                           @RequestParam(defaultValue = "126.84") float esLng) {
    return homeService.selectDabangHomeList(wnLat, wnLng, esLat, esLng);
  }
}
