package com.ssafy.ssafyhome.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class GeocoderUtil {
  @Value("${geocoder.api-key}")
  private String apiKey;
  private final WebClient webClient;


  public GeocoderUtil(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl("https://api.vworld.kr").build();
  }

  public Map getGeocoder(String address) {
    return webClient.get()
        .uri("/req/address?" +
            "service=address" +
            "&request=getCoord" +
            "&format=json" +
            "&type=parcel" +
            "&crs=epsg:4326" +
            "&address=" + address +
            "&key=" + apiKey)
            .retrieve()
        .bodyToMono(Map.class)
        .block();
  }
}
