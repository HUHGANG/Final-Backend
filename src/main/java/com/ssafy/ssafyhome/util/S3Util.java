package com.ssafy.ssafyhome.util;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class S3Util {

  private final AmazonS3 s3Client;

  @Value("${cloud.aws.s3.bucket}")
  private String bucketName;
  @Value("${cloud.aws.cloudfront.domain}")
  private String cloudfrontDomain;

  public S3Util(@Value("${cloud.aws.credentials.accessKey}") String accessKey,
                   @Value("${cloud.aws.credentials.secretKey}") String secretKey,
                   @Value("${cloud.aws.region.static}") String region) {
    BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

    this.s3Client = AmazonS3ClientBuilder.standard()
        .withRegion(region)
        .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
        .build();
  }

  public String uploadFile(MultipartFile file, String fileName) {
    if (file == null) {
      return null;
    }

    try {
      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentLength(file.getSize());

      s3Client.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata)
          .withCannedAcl(CannedAccessControlList.PublicRead));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return cloudfrontDomain + fileName;
  }

  public void deleteFile(String fileName) {
    if (fileName.startsWith(cloudfrontDomain)) {
      fileName = fileName.substring(cloudfrontDomain.length());
      s3Client.deleteObject(bucketName, fileName);
    }
  }
}