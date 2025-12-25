package com.doctor.base.application.gRPC.UsergRPC;

import com.doctor.base.application.Repository.PersonalInfo.DoctorDistanceResponseDTO;
import com.doctor.base.core.service.User.UserService;
import com.gRPC.doctorRecommendation.ListResponse;
import com.gRPC.doctorRecommendation.UserServiceGrpc;
import com.gRPC.doctorRecommendation.UsersRequest;

import io.grpc.stub.StreamObserver;
import jakarta.inject.Inject;
import java.util.List;



public class UsergRPCService extends UserServiceGrpc.UserServiceImplBase {
    @Inject
    UserService userService;

    @Override
    public void getDoctors(UsersRequest request, StreamObserver<ListResponse> responseObserver) {
        List<DoctorDistanceResponseDTO> rankedDoctor =userService.getDoctorList(GRPCMapper.fromgRPC(request));

         ListResponse list=GRPCMapper.togRPC(rankedDoctor);
         responseObserver.onNext(ListResponse
                .newBuilder()
                .addAllResponse(list.getResponseList()).build());
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<UsersRequest> getDoctorLiveLocation(StreamObserver<ListResponse> responseObserver) {
         return new  StreamObserver<UsersRequest>(){
             @Override
             public void onNext(UsersRequest userRequest) {
                 List<DoctorDistanceResponseDTO> rankedDoctor =userService.getDoctorList(GRPCMapper.fromgRPC(userRequest));

                 ListResponse list=GRPCMapper.togRPC(rankedDoctor);
                 responseObserver.onNext(ListResponse
                         .newBuilder()
                         .addAllResponse(list.getResponseList()).build());

             }

             @Override
             public void onError(Throwable t) {
                 t.getMessage();


             }
             @Override
             public void onCompleted() {
                 responseObserver.onCompleted();

             }
         };
    }
}
