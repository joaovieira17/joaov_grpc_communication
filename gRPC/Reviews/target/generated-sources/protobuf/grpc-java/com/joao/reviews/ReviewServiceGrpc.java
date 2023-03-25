package com.joao.reviews;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.51.0)",
    comments = "Source: ReviewService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ReviewServiceGrpc {

  private ReviewServiceGrpc() {}

  public static final String SERVICE_NAME = "com.joao.reviews.ReviewService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.joao.reviews.ReviewRequest,
      com.joao.reviews.ReviewResponse> getGetReviewMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getReview",
      requestType = com.joao.reviews.ReviewRequest.class,
      responseType = com.joao.reviews.ReviewResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.joao.reviews.ReviewRequest,
      com.joao.reviews.ReviewResponse> getGetReviewMethod() {
    io.grpc.MethodDescriptor<com.joao.reviews.ReviewRequest, com.joao.reviews.ReviewResponse> getGetReviewMethod;
    if ((getGetReviewMethod = ReviewServiceGrpc.getGetReviewMethod) == null) {
      synchronized (ReviewServiceGrpc.class) {
        if ((getGetReviewMethod = ReviewServiceGrpc.getGetReviewMethod) == null) {
          ReviewServiceGrpc.getGetReviewMethod = getGetReviewMethod =
              io.grpc.MethodDescriptor.<com.joao.reviews.ReviewRequest, com.joao.reviews.ReviewResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getReview"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.joao.reviews.ReviewRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.joao.reviews.ReviewResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ReviewServiceMethodDescriptorSupplier("getReview"))
              .build();
        }
      }
    }
    return getGetReviewMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ReviewServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReviewServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReviewServiceStub>() {
        @java.lang.Override
        public ReviewServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReviewServiceStub(channel, callOptions);
        }
      };
    return ReviewServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ReviewServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReviewServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReviewServiceBlockingStub>() {
        @java.lang.Override
        public ReviewServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReviewServiceBlockingStub(channel, callOptions);
        }
      };
    return ReviewServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ReviewServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReviewServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReviewServiceFutureStub>() {
        @java.lang.Override
        public ReviewServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReviewServiceFutureStub(channel, callOptions);
        }
      };
    return ReviewServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ReviewServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getReview(com.joao.reviews.ReviewRequest request,
        io.grpc.stub.StreamObserver<com.joao.reviews.ReviewResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetReviewMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetReviewMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.joao.reviews.ReviewRequest,
                com.joao.reviews.ReviewResponse>(
                  this, METHODID_GET_REVIEW)))
          .build();
    }
  }

  /**
   */
  public static final class ReviewServiceStub extends io.grpc.stub.AbstractAsyncStub<ReviewServiceStub> {
    private ReviewServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReviewServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReviewServiceStub(channel, callOptions);
    }

    /**
     */
    public void getReview(com.joao.reviews.ReviewRequest request,
        io.grpc.stub.StreamObserver<com.joao.reviews.ReviewResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetReviewMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ReviewServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<ReviewServiceBlockingStub> {
    private ReviewServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReviewServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReviewServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.joao.reviews.ReviewResponse getReview(com.joao.reviews.ReviewRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetReviewMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ReviewServiceFutureStub extends io.grpc.stub.AbstractFutureStub<ReviewServiceFutureStub> {
    private ReviewServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReviewServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReviewServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.joao.reviews.ReviewResponse> getReview(
        com.joao.reviews.ReviewRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetReviewMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_REVIEW = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ReviewServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ReviewServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_REVIEW:
          serviceImpl.getReview((com.joao.reviews.ReviewRequest) request,
              (io.grpc.stub.StreamObserver<com.joao.reviews.ReviewResponse>) responseObserver);
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

  private static abstract class ReviewServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ReviewServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.joao.reviews.ReviewServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ReviewService");
    }
  }

  private static final class ReviewServiceFileDescriptorSupplier
      extends ReviewServiceBaseDescriptorSupplier {
    ReviewServiceFileDescriptorSupplier() {}
  }

  private static final class ReviewServiceMethodDescriptorSupplier
      extends ReviewServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ReviewServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ReviewServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ReviewServiceFileDescriptorSupplier())
              .addMethod(getGetReviewMethod())
              .build();
        }
      }
    }
    return result;
  }
}
