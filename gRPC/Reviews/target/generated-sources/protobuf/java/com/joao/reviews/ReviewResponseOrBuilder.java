// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ReviewService.proto

package com.joao.reviews;

public interface ReviewResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.joao.reviews.ReviewResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 code = 1;</code>
   * @return The code.
   */
  int getCode();

  /**
   * <code>string reviewId = 2;</code>
   * @return The reviewId.
   */
  java.lang.String getReviewId();
  /**
   * <code>string reviewId = 2;</code>
   * @return The bytes for reviewId.
   */
  com.google.protobuf.ByteString
      getReviewIdBytes();

  /**
   * <code>string status = 3;</code>
   * @return The status.
   */
  java.lang.String getStatus();
  /**
   * <code>string status = 3;</code>
   * @return The bytes for status.
   */
  com.google.protobuf.ByteString
      getStatusBytes();
}