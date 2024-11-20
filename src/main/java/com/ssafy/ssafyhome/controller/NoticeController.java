package com.ssafy.ssafyhome.controller;

import com.ssafy.ssafyhome.annotation.Login;
import com.ssafy.ssafyhome.annotation.RoleCheck;
import com.ssafy.ssafyhome.domain.dto.NoticeReqDto;
import com.ssafy.ssafyhome.domain.dto.NoticeListResDto;
import com.ssafy.ssafyhome.domain.entity.Member;
import com.ssafy.ssafyhome.domain.entity.Notice;
import com.ssafy.ssafyhome.domain.enums.Role;
import com.ssafy.ssafyhome.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/notices")
@RequiredArgsConstructor
@Tag(name = "공지사항 컨트롤러", description = "공지사항 목록과 상세보기, 등록, 수정, 삭제등 전반적인 공지사항 관리를 처리하는 클래스")
public class NoticeController {

  private final NoticeService noticeService;

  @GetMapping()
  @Operation(summary = "공지사항 리스트 조회", description = "")
  public NoticeListResDto getNoticeList(@RequestParam(defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "10") int size) {
    return noticeService.selectNoticeList(page, size);
  }

  @GetMapping("/{id}")
  @Operation(summary = "공지사항 상세 조회", description = "")
  public Notice selectNoticeDetail(@PathVariable("id") int id) {
    return noticeService.selectNoticeDetail(id);
  }

  @RoleCheck(Role.ADMIN)
  @PostMapping()
  @Operation(summary = "공지사항 작성", description = "")
  public Notice insertNotice(@Login Member member, @RequestBody NoticeReqDto dto) {
    return noticeService.insertNotice(member, dto.getTitle(), dto.getContent());
  }

  @RoleCheck(Role.ADMIN)
  @DeleteMapping("/{id}")
  @Operation(summary = "공지사항 삭제", description = "")
  public void deleteNotice(@PathVariable("id") int id) {
    noticeService.deleteNotice(id);
  }
}
