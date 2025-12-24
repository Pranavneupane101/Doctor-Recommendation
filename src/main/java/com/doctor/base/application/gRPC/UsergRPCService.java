package com.doctor.base.application.gRPC;

import com.doctor.base.application.Repository.PersonalInfo.DoctorDistanceResponseDTO;
import com.doctor.base.core.service.User.UserService;
import com.gRPC.doctorRecommendation.ListResponse;
import com.gRPC.doctorRecommendation.UserServiceGrpc;
import com.gRPC.doctorRecommendation.userRequest;
import io.grpc.stub.StreamObserver;
import jakarta.inject.Inject;
import java.util.List;



public class UsergRPCService extends UserServiceGrpc.UserServiceImplBase {
    @Inject
    UserService userService;

    @Override
    public void getDoctors(userRequest request, StreamObserver<ListResponse> responseObserver) {
        List<DoctorDistanceResponseDTO> rankedDoctor =userService.getDoctorList(GRPCMapper.fromgRPC(request));
         for (DoctorDistanceResponseDTO dto : rankedDoctor) {
             System.out.println(dto.getAvailability().getAppointments());
         }
         ListResponse list=GRPCMapper.togRPC(rankedDoctor);
         responseObserver.onNext(ListResponse
                .newBuilder()
                .addAllResponse(list.getResponseList()).build());
        responseObserver.onCompleted();
    }
}
