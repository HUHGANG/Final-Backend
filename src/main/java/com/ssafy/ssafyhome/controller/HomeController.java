package com.ssafy.ssafyhome.controller;

import com.ssafy.ssafyhome.annotation.Login;
import com.ssafy.ssafyhome.annotation.RoleCheck;
import com.ssafy.ssafyhome.domain.dto.HomeBCodeResDto;
import com.ssafy.ssafyhome.domain.dto.HomeDabangListResDto;
import com.ssafy.ssafyhome.domain.dto.HomeSsafyListResDto;
import com.ssafy.ssafyhome.domain.dto.HomeSsafyReqDto;
import com.ssafy.ssafyhome.domain.entity.Member;
import com.ssafy.ssafyhome.domain.entity.Ssafy;
import com.ssafy.ssafyhome.domain.enums.Role;
import com.ssafy.ssafyhome.service.HomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
@Tag(name = "다방 크롤링 매물 조회 컨트롤러", description = "다방 크롤링 매물 조회")
public class HomeController {

  private final HomeService homeService;

  @GetMapping("/search")
  @Operation(summary = "지역 검색", description = "")
  public List<HomeBCodeResDto> selectLocationList(String location) {
    return homeService.selectLocationList(location);
  }

  @GetMapping("/dabang")
  @Operation(summary = "다방 매물 리스트 조회", description = "")
  public HomeDabangListResDto selectDabangHomeList(@RequestParam(required = false) Long bCode,
                                                   @RequestParam(defaultValue = "35.2") float neLat,
                                                   @RequestParam(defaultValue = "126.84") float neLng,
                                                   @RequestParam(defaultValue = "35.18") float swLat,
                                                   @RequestParam(defaultValue = "126.8") float swLng,
                                                   @RequestParam(defaultValue = "1") int page,
                                                   @RequestParam(defaultValue = "20") int size) {
    return homeService.selectDabangHomeList(bCode, neLat, neLng, swLat, swLng, page, size);
  }

  @GetMapping("/ssafy")
  @Operation(summary = "싸피생 매물 리스트 조회", description = "")
  public HomeSsafyListResDto selectSsafyHomeList(@RequestParam(required = false) Long bCode,
                                                 @RequestParam(defaultValue = "35.2") float neLat,
                                                 @RequestParam(defaultValue = "126.84") float neLng,
                                                 @RequestParam(defaultValue = "35.18") float swLat,
                                                 @RequestParam(defaultValue = "126.7") float swLng,
                                                 @RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "20") int size) {
    return homeService.selectSsafyHomeList(bCode, neLat, neLng, swLat, swLng, page, size);
  }

  @GetMapping("/ssafy/{id}")
  @Operation(summary = "싸피생 매물 상세 조회", description = "")
  public Ssafy selectSsafyHomeDetail(@PathVariable int id) {
    return homeService.selectSsafyHomeDetail(id);
  }

  @RoleCheck({Role.ADMIN, Role.USER})
  @PostMapping("/ssafy/content-ai-generation")
  @Operation(summary = "싸피생 매물 내용 ai 생성", description = "")
  public String generateContentByAI(@RequestBody HomeSsafyReqDto dto){
    return homeService.generateContentByAI(dto);
  }

  @RoleCheck({Role.ADMIN, Role.USER})
  @PostMapping("/ssafy")
  @Operation(summary = "싸피생 매물 등록", description = "")
  public Ssafy insertSsafyHome(@Login Member member,
                               @RequestPart(name = "homeReq") HomeSsafyReqDto dto,
                               @RequestPart(name = "images") List<MultipartFile> multipartFiles) {
    return homeService.insertSsafyHome(member, dto, multipartFiles);
  }

  @RoleCheck({Role.ADMIN, Role.USER})
  @DeleteMapping("/ssafy/{id}")
  @Operation(summary = "싸피생 매물 삭제", description = "")
  public void insertSsafyHome(@Login Member member, @PathVariable int id) {
    homeService.deleteSsafyHome(member, id);
  }
}
