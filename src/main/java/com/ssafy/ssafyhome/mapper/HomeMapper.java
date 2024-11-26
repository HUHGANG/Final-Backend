package com.ssafy.ssafyhome.mapper;

import com.ssafy.ssafyhome.domain.dto.HomeBCodeResDto;
import com.ssafy.ssafyhome.domain.entity.Dabang;
import com.ssafy.ssafyhome.domain.entity.Image;
import com.ssafy.ssafyhome.domain.entity.Ssafy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HomeMapper {

  List<HomeBCodeResDto> selectLocationList(String location);

  List<Dabang> selectDabangHomeList(Long bCode,
                                    String rentType,
                                    Integer depositRangeMin,
                                    Integer depositRangeMax,
                                    Integer monthlyRentRangeMin,
                                    Integer monthlyRentRangeMax,
                                    Integer maintenanceCostRangeMin,
                                    Integer maintenanceCostRangeMax,
                                    Float exclusiveAreaRangeMin,
                                    Float exclusiveAreaRangeMax,
                                    String roomType,float neLat, float neLng, float swLat, float swLng, int offset, int size);


  int countTotalDabangHome( Long bCode,
                           String rentType,
                           Integer depositRangeMin,
                           Integer depositRangeMax,
                           Integer monthlyRentRangeMin,
                           Integer monthlyRentRangeMax,
                           Integer maintenanceCostRangeMin,
                           Integer maintenanceCostRangeMax,
                           Float exclusiveAreaRangeMin,
                           Float exclusiveAreaRangeMax,
                           String roomType, float neLat, float neLng, float swLat, float swLng);

  List<Ssafy> selectSsafyHomeList(Long bCode,
                                  String rentType,
                                  Integer depositRangeMin,
                                  Integer depositRangeMax,
                                  Integer monthlyRentRangeMin,
                                  Integer monthlyRentRangeMax,
                                  Integer maintenanceCostRangeMin,
                                  Integer maintenanceCostRangeMax,
                                  Float exclusiveAreaRangeMin,
                                  Float exclusiveAreaRangeMax,
                                  String roomType, float neLat, float neLng, float swLat, float swLng, int offset, int size);

  int countTotalSsafyHome(Long bCode,
                          String rentType,
                          Integer depositRangeMin,
                          Integer depositRangeMax,
                          Integer monthlyRentRangeMin,
                          Integer monthlyRentRangeMax,
                          Integer maintenanceCostRangeMin,
                          Integer maintenanceCostRangeMax,
                          Float exclusiveAreaRangeMin,
                          Float exclusiveAreaRangeMax,
                          String roomType, float neLat, float neLng, float swLat, float swLng);


  void insertSsafyHome(@Param("ssafy") Ssafy ssafy);

  void insertHomeImage(List<Image> imageList);

  Integer selectSsafyHome(int id);

  void deleteSsafyHome(int id);
}
