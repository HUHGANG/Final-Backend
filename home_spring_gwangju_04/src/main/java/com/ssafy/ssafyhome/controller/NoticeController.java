package com.ssafy.ssafyhome.controller;

import com.ssafy.ssafyhome.domain.entity.Notice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/notices")
@RequiredArgsConstructor
@Tag(name = "공지사항 컨트롤러", description = "공지사항 목록과 상세보기, 등록, 수정, 삭제등 전반적인 공지사항 관리를 처리하는 클래스")
public class NoticeController {

  @PostMapping()
  @Operation(summary = "공지사항 작성", description = "")
  public Notice create(@RequestBody Notice notice) {
    return notice;
  }

  @GetMapping()
  @Operation(summary = "공지사항 리스트 조회", description = "")
  public List<Notice> getNotices() {
    return new LinkedList<>();
  }

  @PostMapping("/{id}")
  @Operation(summary = "공지사항 상세 조회", description = "")
  public Notice getNoticeDetail(@PathVariable("id") String id) {
    return new Notice();
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "공지사항 삭제", description = "")
  public void deleteNotice(@PathVariable("id") String id) {

  }
}
