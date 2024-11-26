package com.ssafy.ssafyhome.domain.entity;

import com.ssafy.ssafyhome.domain.enums.RentType;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Ssafy {

  private int id;
  private Member member;
  private List<Image> imgList;
  private String address;
  private double lat;
  private double lng;
  private BCode bCode;
  private String rentType;
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
  private Date resistedDate;
  private Date createdAt;
  private Date updatedAt;

  @Builder
  public Ssafy(int id, Member member, List<Image> imgList, String address, double lat, double lng, BCode bCode, String rentType, int deposit, int monthlyRent, int maintenanceCost, String title, String content, String roomType, float exclusiveArea, String floor, int roomCnt, int bathroomCnt, String direction, Date expirationDate, String availableFrom, String homeType, Date approvalDate, Date resistedDate, Date createdAt, Date updatedAt) {
    this.id = id;
    this.member = member;
    this.imgList = imgList;
    this.address = address;
    this.lat = lat;
    this.lng = lng;
    this.bCode = bCode;
    this.rentType = rentType;
    this.deposit = deposit;
    this.monthlyRent = monthlyRent;
    this.maintenanceCost = maintenanceCost;
    this.title = title;
    this.content = content;
    this.roomType = roomType;
    this.exclusiveArea = exclusiveArea;
    this.floor = floor;
    this.roomCnt = roomCnt;
    this.bathroomCnt = bathroomCnt;
    this.direction = direction;
    this.expirationDate = expirationDate;
    this.availableFrom = availableFrom;
    this.homeType = homeType;
    this.approvalDate = approvalDate;
    this.resistedDate = resistedDate;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
