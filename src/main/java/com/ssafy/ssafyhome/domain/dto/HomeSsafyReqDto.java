package com.ssafy.ssafyhome.domain.dto;

import com.ssafy.ssafyhome.domain.enums.RentType;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class HomeSsafyReqDto {

  private String address;
  private RentType rentType;
  private int deposit;
  private int monthlyRent;
  private int maintenanceCost;
  private String title;
  private String content;
  private String roomType;
  private float exclusiveArea;
  private String floor;
  private int roomCnt;
  private int bathroomCnt;
  private String direction;
  private Date expirationDate;
  private String availableFrom;
  private String homeType;
  private Date approvalDate;
}
