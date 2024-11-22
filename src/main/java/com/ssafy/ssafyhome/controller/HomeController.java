package com.ssafy.ssafyhome.controller;

import com.ssafy.ssafyhome.domain.entity.Dabang;
import com.ssafy.ssafyhome.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

  private final HomeService homeService;

  @GetMapping("/dabang")
  public List<Dabang> selectDabangHomeList(@RequestParam(defaultValue = "35.2") float wnLat,
                                           @RequestParam(defaultValue = "126.8") float wnLng,
                                           @RequestParam(defaultValue = "35.18") float esLat,
                                           @RequestParam(defaultValue = "126.84") float esLng) {
    return homeService.selectDabangHomeList(wnLat, wnLng, esLat, esLng);
  }
}
