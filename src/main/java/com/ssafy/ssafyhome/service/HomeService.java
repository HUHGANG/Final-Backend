package com.ssafy.ssafyhome.service;

import com.ssafy.ssafyhome.domain.entity.Dabang;
import com.ssafy.ssafyhome.mapper.HomeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService {

  private final HomeMapper homeMapper;

  public List<Dabang> selectDabangHomeList(float neLat, float neLng, float swLat, float swLng) {
    return homeMapper.selectDabangHomeList(neLat, neLng, swLat, swLng);
  }
}
