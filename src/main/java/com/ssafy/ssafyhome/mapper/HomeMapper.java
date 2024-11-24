package com.ssafy.ssafyhome.mapper;

import com.ssafy.ssafyhome.domain.dto.HomeBCodeResDto;
import com.ssafy.ssafyhome.domain.entity.Dabang;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomeMapper {

  List<HomeBCodeResDto> selectLocationList(String location);

  List<Dabang> selectDabangHomeList(Long bCode, float neLat, float neLng, float swLat, float swLng, int offset, int size);

  int countTotalHome(Long bCode, float neLat, float neLng, float swLat, float swLng);
}
