package com.ssafy.ssafyhome.mapper;

import com.ssafy.ssafyhome.domain.entity.Dabang;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomeMapper {

  List<Dabang> selectDabangHomeList(float neLat, float neLng, float swLat, float swLng, int offset, int size);

  int countTotalHome(float neLat, float neLng, float swLat, float swLng);
}
