package com.ssafy.ssafyhome.domain.entity;

import com.ssafy.ssafyhome.domain.enums.RentType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Dabang {

  private int id;
  private String detailId;
  private String linkUrl;
  private String img;
  private String address;
  private float lat;
  private float lng;
  private BCode bCode;
  private RentType rentType;
  private int deposit;
  private int monthlyRent;
  private String title;
  private String roomType;
  private float exclusiveArea;
  private String floor;
  private int maintenanceCost;
  private int roomCnt;
  private int bathroomCnt;
  private String direction;
  private String availableFrom;
  private String homeType;
  private Date approvalDate;
  private Date resistedDate;
  private Date createdAt;
  private Date updatedAt;
}
