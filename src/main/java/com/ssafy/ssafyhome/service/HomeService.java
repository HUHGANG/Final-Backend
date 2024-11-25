package com.ssafy.ssafyhome.service;

import com.ssafy.ssafyhome.domain.dto.HomeBCodeResDto;
import com.ssafy.ssafyhome.domain.dto.HomeListResDto;
import com.ssafy.ssafyhome.domain.dto.HomeSsafyReqDto;
import com.ssafy.ssafyhome.domain.entity.*;
import com.ssafy.ssafyhome.exception.BadRequestException;
import com.ssafy.ssafyhome.exception.ForbiddenException;
import com.ssafy.ssafyhome.mapper.HomeMapper;
import com.ssafy.ssafyhome.util.GeocoderUtil;
import com.ssafy.ssafyhome.util.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HomeService {

  private final HomeMapper homeMapper;
  private final GeocoderUtil geocoderUtil;
  private final S3Util s3Util;

  public List<HomeBCodeResDto> selectLocationList(String location) {
    return homeMapper.selectLocationList(location);
  }

  public HomeListResDto selectDabangHomeList(Long bCode, float neLat, float neLng, float swLat, float swLng, int page, int size) {
    int offset = (page - 1) * size;

    List<Dabang> homeList = homeMapper.selectDabangHomeList(bCode, neLat, neLng, swLat, swLng, offset, size);
    int totalCnt = homeMapper.countTotalHome(bCode, neLat, neLng, swLat, swLng);

    return HomeListResDto.builder()
        .currentPage(page)
        .totalPage((totalCnt + size - 1) / size)
        .totalCnt(totalCnt)
        .homeList(homeList)
        .build();
  }

  @Transactional
  public Ssafy insertSsafyHome(Member member, HomeSsafyReqDto dto, List<MultipartFile> multipartFiles) {
    Map result;
    float lat, lng;

    try {
      result = geocoderUtil.getGeocoder(dto.getAddress());
      lng = Float.parseFloat((String) ((Map) ((Map) ((Map) result.get("response")).get("result")).get("point")).get("x"));
      lat = Float.parseFloat((String) ((Map) ((Map) ((Map) result.get("response")).get("result")).get("point")).get("y"));
    } catch (Exception e) {
      throw new BadRequestException("지번 ㄱㄱ");
    }

    Ssafy ssafy = Ssafy.builder()
        .member(member)
        .address(dto.getAddress())
        .lat(lat)
        .lng(lng)
        .bCode(BCode.builder()
            .bCode(Long.parseLong(((String) (
                (Map) (
                    (Map) (
                        (Map) result.get("response")
                    ).get("refined")
                ).get("structure")
            ).get("level4LC")).substring(0, 10)))
            .build())
        .rentType(dto.getRentType())
        .deposit(dto.getDeposit())
        .monthlyRent(dto.getMonthlyRent())
        .maintenanceCost(dto.getMaintenanceCost())
        .title(dto.getTitle())
        .content(dto.getContent())
        .roomType(dto.getRoomType())
        .exclusiveArea(dto.getExclusiveArea())
        .floor(dto.getFloor())
        .roomCnt(dto.getRoomCnt())
        .bathroomCnt(dto.getBathroomCnt())
        .direction(dto.getDirection())
        .expirationDate(dto.getExpirationDate())
        .availableFrom(dto.getAvailableFrom())
        .homeType(dto.getHomeType())
        .approvalDate(dto.getApprovalDate())
        .resistedDate(new Date())
        .build();

    homeMapper.insertSsafyHome(ssafy);


    // 이미지 저장
    if (!multipartFiles.isEmpty()) {
      List<Image> imageList = new ArrayList<>();

      for (MultipartFile multipartFile : multipartFiles) {
        String fileName = "/ssafy/home/" + System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
        String url = s3Util.uploadFile(multipartFile, fileName);
        imageList.add(Image.builder()
            .homeId(ssafy.getId())
            .url(url)
            .build());
      }

      homeMapper.insertHomeImage(imageList);
      ssafy.setImgList(imageList);
    }

    return ssafy;
  }

  public void deleteSsafyHome(Member member, int id) {
    Integer authorId = homeMapper.selectSsafyHome(id);

    if(authorId == null) {
      throw new BadRequestException("매물 존재x");
    }

    if (authorId != member.getId()) {
      throw new ForbiddenException("권한이 없습니다.");
    }

    homeMapper.deleteSsafyHome(id);
  }
}
