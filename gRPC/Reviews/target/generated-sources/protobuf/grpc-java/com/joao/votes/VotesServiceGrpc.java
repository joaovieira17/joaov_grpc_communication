package com.joao.votes;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.51.0)",
    comments = "Source: VoteService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class VotesServiceGrpc {

  private VotesServiceGrpc() {}

  public static final String SERVICE_NAME = "com.joao.votes.VotesService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.joao.votes.VotesRequest,
      com.joao.votes.VotesResponse> getUpdateVoteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "updateVote",
      requestType = com.joao.votes.VotesRequest.class,
      responseType = com.joao.votes.VotesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.joao.votes.VotesRequest,
      com.joao.votes.VotesResponse> getUpdateVoteMethod() {
    io.grpc.MethodDescriptor<com.joao.votes.VotesRequest, com.joao.votes.VotesResponse> getUpdateVoteMethod;
    if ((getUpdateVoteMethod = VotesServiceGrpc.getUpdateVoteMethod) == null) {
      synchronized (VotesServiceGrpc.class) {
        if ((getUpdateVoteMethod = VotesServiceGrpc.getUpdateVoteMethod) == null) {
          VotesServiceGrpc.getUpdateVoteMethod = getUpdateVoteMethod =
              io.grpc.MethodDescriptor.<com.joao.votes.VotesRequest, com.joao.votes.VotesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "updateVote"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.joao.votes.VotesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.joao.votes.VotesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new VotesServiceMethodDescriptorSupplier("updateVote"))
              .build();
        }
      }
    }
    return getUpdateVoteMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static VotesServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<VotesServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<VotesServiceStub>() {
        @java.lang.Override
        public VotesServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new VotesServiceStub(channel, callOptions);
        }
      };
    return VotesServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static VotesServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<VotesServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<VotesServiceBlockingStub>() {
        @java.lang.Override
        public VotesServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new VotesServiceBlockingStub(channel, callOptions);
        }
      };
    return VotesServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static VotesServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<VotesServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<VotesServiceFutureStub>() {
        @java.lang.Override
        public VotesServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new VotesServiceFutureStub(channel, callOptions);
        }
      };
    return VotesServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class VotesServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void updateVote(com.joao.votes.VotesRequest request,
        io.grpc.stub.StreamObserver<com.joao.votes.VotesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateVoteMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getUpdateVoteMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.joao.votes.VotesRequest,
                com.joao.votes.VotesResponse>(
                  this, METHODID_UPDATE_VOTE)))
          .build();
    }
  }

  /**
   */
  public static final class VotesServiceStub extends io.grpc.stub.AbstractAsyncStub<VotesServiceStub> {
    private VotesServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected VotesServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new VotesServiceStub(channel, callOptions);
    }

    /**
     */
    public void updateVote(com.joao.votes.VotesRequest request,
        io.grpc.stub.StreamObserver<com.joao.votes.VotesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateVoteMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class VotesServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<VotesServiceBlockingStub> {
    private VotesServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected VotesServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new VotesServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.joao.votes.VotesResponse updateVote(com.joao.votes.VotesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateVoteMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class VotesServiceFutureStub extends io.grpc.stub.AbstractFutureStub<VotesServiceFutureStub> {
    private VotesServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected VotesServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new VotesServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.joao.votes.VotesResponse> updateVote(
        com.joao.votes.VotesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateVoteMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_UPDATE_VOTE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final VotesServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(VotesServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UPDATE_VOTE:
          serviceImpl.updateVote((com.joao.votes.VotesRequest) request,
              (io.grpc.stub.StreamObserver<com.joao.votes.VotesResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class VotesServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    VotesServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.joao.votes.VoteService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("VotesService");
    }
  }

  private static final class VotesServiceFileDescriptorSupplier
      extends VotesServiceBaseDescriptorSupplier {
    VotesServiceFileDescriptorSupplier() {}
  }

  private static final class VotesServiceMethodDescriptorSupplier
      extends VotesServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    VotesServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (VotesServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new VotesServiceFileDescriptorSupplier())
              .addMethod(getUpdateVoteMethod())
              .build();
        }
      }
    }
    return result;
  }
}
