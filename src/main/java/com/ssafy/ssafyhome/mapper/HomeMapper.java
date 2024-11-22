package com.ssafy.ssafyhome.mapper;

import com.ssafy.ssafyhome.domain.entity.Dabang;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomeMapper {

  List<Dabang> selectDabangHomeList(float wnLat, float wnLng, float esLat, float esLng);
}
