// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: VoteService.proto

package com.joao.votes;

public final class VoteService {
  private VoteService() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_joao_votes_VotesRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_joao_votes_VotesRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_joao_votes_VotesResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_joao_votes_VotesResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021VoteService.proto\022\016com.joao.votes\".\n\014V" +
      "otesRequest\022\020\n\010reviewId\030\001 \001(\t\022\014\n\004vote\030\002 " +
      "\001(\010\"\035\n\rVotesResponse\022\014\n\004code\030\001 \001(\0052Y\n\014Vo" +
      "tesService\022I\n\nupdateVote\022\034.com.joao.vote" +
      "s.VotesRequest\032\035.com.joao.votes.VotesRes" +
      "ponseB\002P\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_com_joao_votes_VotesRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_joao_votes_VotesRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_joao_votes_VotesRequest_descriptor,
        new java.lang.String[] { "ReviewId", "Vote", });
    internal_static_com_joao_votes_VotesResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_joao_votes_VotesResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_joao_votes_VotesResponse_descriptor,
        new java.lang.String[] { "Code", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}