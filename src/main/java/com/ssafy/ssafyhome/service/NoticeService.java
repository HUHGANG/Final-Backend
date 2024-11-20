package com.ssafy.ssafyhome.service;

import com.ssafy.ssafyhome.domain.entity.Member;
import com.ssafy.ssafyhome.domain.entity.Notice;
import com.ssafy.ssafyhome.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class NoticeService {

  private final NoticeMapper noticeMapper;

  public Notice insertNotice(Member member, String title, String content) {
    Notice notice = Notice.builder()
            .title(title)
            .content(content)
            .authorId(member.getId())
            .authorName(member.getName())
            .createdAt(new Timestamp(new Date().getTime()))
            .updatedAt(new Timestamp(new Date().getTime()))
            .build();
    
    noticeMapper.insertNotice(notice);

    return notice;
  }
}
