// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: SandwichService.proto

package com.joao.sandwich;

public final class SandwichServiceOuterClass {
  private SandwichServiceOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_joao_sandwich_SandwichRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_joao_sandwich_SandwichRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_joao_sandwich_SandwichResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_joao_sandwich_SandwichResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\025SandwichService.proto\022\021com.joao.sandwi" +
      "ch\"%\n\017SandwichRequest\022\022\n\nsandwichId\030\001 \001(" +
      "\t\" \n\020SandwichResponse\022\014\n\004code\030\001 \001(\0052i\n\017S" +
      "andwichService\022V\n\013getSandwich\022\".com.joao" +
      ".sandwich.SandwichRequest\032#.com.joao.san" +
      "dwich.SandwichResponseB\002P\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_com_joao_sandwich_SandwichRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_joao_sandwich_SandwichRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_joao_sandwich_SandwichRequest_descriptor,
        new java.lang.String[] { "SandwichId", });
    internal_static_com_joao_sandwich_SandwichResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_joao_sandwich_SandwichResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_joao_sandwich_SandwichResponse_descriptor,
        new java.lang.String[] { "Code", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}