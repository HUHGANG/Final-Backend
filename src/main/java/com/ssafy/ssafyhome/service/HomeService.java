package com.ssafy.ssafyhome.service;

import com.ssafy.ssafyhome.domain.dto.HomeBCodeResDto;
import com.ssafy.ssafyhome.domain.dto.HomeListResDto;
import com.ssafy.ssafyhome.domain.entity.Dabang;
import com.ssafy.ssafyhome.mapper.HomeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService {

  private final HomeMapper homeMapper;
  private final GeocoderUtil geocoderUtil;

  public List<HomeBCodeResDto> selectLocationList(String location) {
    return homeMapper.selectLocationList(location);
  }

  public HomeListResDto selectDabangHomeList(float neLat, float neLng, float swLat, float swLng, int page, int size) {
    int offset = (page - 1) * size;

    List<Dabang> homeList = homeMapper.selectDabangHomeList(neLat, neLng, swLat, swLng, offset, size);
    int totalCnt = homeMapper.countTotalHome(neLat, neLng, swLat, swLng);

    return HomeListResDto.builder()
            .currentPage(page)
            .totalPage((totalCnt + size - 1) / size)
            .totalCnt(totalCnt)
            .homeList(homeList)
            .build();
  }
}
