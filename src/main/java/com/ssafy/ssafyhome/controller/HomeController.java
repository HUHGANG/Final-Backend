package com.ssafy.ssafyhome.controller;

import com.ssafy.ssafyhome.domain.dto.HomeListResDto;
import com.ssafy.ssafyhome.service.HomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
@Tag(name = "다방 크롤링 매물 조회 컨트롤러", description = "다방 크롤링 매물 조회")
public class HomeController {

  private final HomeService homeService;

  @GetMapping("/dabang")
  @Operation(summary = "다방 매물 리스트 조회", description = "")
  public HomeListResDto selectDabangHomeList(@RequestParam(defaultValue = "35.2") float neLat,
                                             @RequestParam(defaultValue = "126.84") float neLng,
                                             @RequestParam(defaultValue = "35.18") float swLat,
                                             @RequestParam(defaultValue = "126.8") float swLng,
                                             @RequestParam(defaultValue = "1") int page,
                                             @RequestParam(defaultValue = "20") int size) {
    return homeService.selectDabangHomeList(neLat, neLng, swLat, swLng, page, size);
  }
}
