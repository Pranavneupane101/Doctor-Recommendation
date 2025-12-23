package com.doctor.base.application.gRPC;

import com.doctor.base.application.Repository.PersonalInfo.DoctorDistanceResponseDTO;
import com.doctor.base.core.service.User.UserService;
import com.gRPC.doctorRecommendation.ListResponse;

import com.gRPC.doctorRecommendation.UserServiceGrpc;
import com.gRPC.doctorRecommendation.userRequest;
import io.grpc.stub.StreamObserver;
import io.micronaut.grpc.annotation.GrpcService;
import jakarta.inject.Inject;

import java.util.List;


@GrpcService
public class UsergRPCService extends UserServiceGrpc.UserServiceImplBase {
    @Inject
    UserService userService;

    @Override
    public void getDoctors(userRequest request, StreamObserver<ListResponse> responseObserver) {
        List<DoctorDistanceResponseDTO> rankedDoctor =userService.getDoctorList(GRPCMapper.fromgRPC(request));

        responseObserver.onNext(ListResponse
                .newBuilder()
                .addAllResponse(GRPCMapper.togRPC(rankedDoctor).getResponseList()).build());
        responseObserver.onCompleted();
    }
}
