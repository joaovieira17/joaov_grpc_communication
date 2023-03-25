package com.joao.sandwich;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.51.0)",
    comments = "Source: SandwichService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class SandwichServiceGrpc {

  private SandwichServiceGrpc() {}

  public static final String SERVICE_NAME = "com.joao.sandwich.SandwichService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.joao.sandwich.SandwichRequest,
      com.joao.sandwich.SandwichResponse> getGetSandwichMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getSandwich",
      requestType = com.joao.sandwich.SandwichRequest.class,
      responseType = com.joao.sandwich.SandwichResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.joao.sandwich.SandwichRequest,
      com.joao.sandwich.SandwichResponse> getGetSandwichMethod() {
    io.grpc.MethodDescriptor<com.joao.sandwich.SandwichRequest, com.joao.sandwich.SandwichResponse> getGetSandwichMethod;
    if ((getGetSandwichMethod = SandwichServiceGrpc.getGetSandwichMethod) == null) {
      synchronized (SandwichServiceGrpc.class) {
        if ((getGetSandwichMethod = SandwichServiceGrpc.getGetSandwichMethod) == null) {
          SandwichServiceGrpc.getGetSandwichMethod = getGetSandwichMethod =
              io.grpc.MethodDescriptor.<com.joao.sandwich.SandwichRequest, com.joao.sandwich.SandwichResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getSandwich"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.joao.sandwich.SandwichRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.joao.sandwich.SandwichResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SandwichServiceMethodDescriptorSupplier("getSandwich"))
              .build();
        }
      }
    }
    return getGetSandwichMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SandwichServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SandwichServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SandwichServiceStub>() {
        @java.lang.Override
        public SandwichServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SandwichServiceStub(channel, callOptions);
        }
      };
    return SandwichServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SandwichServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SandwichServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SandwichServiceBlockingStub>() {
        @java.lang.Override
        public SandwichServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SandwichServiceBlockingStub(channel, callOptions);
        }
      };
    return SandwichServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SandwichServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SandwichServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SandwichServiceFutureStub>() {
        @java.lang.Override
        public SandwichServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SandwichServiceFutureStub(channel, callOptions);
        }
      };
    return SandwichServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class SandwichServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getSandwich(com.joao.sandwich.SandwichRequest request,
        io.grpc.stub.StreamObserver<com.joao.sandwich.SandwichResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetSandwichMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetSandwichMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.joao.sandwich.SandwichRequest,
                com.joao.sandwich.SandwichResponse>(
                  this, METHODID_GET_SANDWICH)))
          .build();
    }
  }

  /**
   */
  public static final class SandwichServiceStub extends io.grpc.stub.AbstractAsyncStub<SandwichServiceStub> {
    private SandwichServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SandwichServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SandwichServiceStub(channel, callOptions);
    }

    /**
     */
    public void getSandwich(com.joao.sandwich.SandwichRequest request,
        io.grpc.stub.StreamObserver<com.joao.sandwich.SandwichResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetSandwichMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class SandwichServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<SandwichServiceBlockingStub> {
    private SandwichServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SandwichServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SandwichServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.joao.sandwich.SandwichResponse getSandwich(com.joao.sandwich.SandwichRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetSandwichMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SandwichServiceFutureStub extends io.grpc.stub.AbstractFutureStub<SandwichServiceFutureStub> {
    private SandwichServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SandwichServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SandwichServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.joao.sandwich.SandwichResponse> getSandwich(
        com.joao.sandwich.SandwichRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetSandwichMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_SANDWICH = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SandwichServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SandwichServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_SANDWICH:
          serviceImpl.getSandwich((com.joao.sandwich.SandwichRequest) request,
              (io.grpc.stub.StreamObserver<com.joao.sandwich.SandwichResponse>) responseObserver);
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

  private static abstract class SandwichServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SandwichServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.joao.sandwich.SandwichServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SandwichService");
    }
  }

  private static final class SandwichServiceFileDescriptorSupplier
      extends SandwichServiceBaseDescriptorSupplier {
    SandwichServiceFileDescriptorSupplier() {}
  }

  private static final class SandwichServiceMethodDescriptorSupplier
      extends SandwichServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SandwichServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (SandwichServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SandwichServiceFileDescriptorSupplier())
              .addMethod(getGetSandwichMethod())
              .build();
        }
      }
    }
    return result;
  }
}
