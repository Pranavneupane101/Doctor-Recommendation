package com.doctor.base.application.gRPC.Doctor;


import com.doctor.base.core.models.Doctor;
import com.doctor.base.core.service.Doctor.DoctorService;
import com.gRPC.doctor.*;

import io.grpc.stub.StreamObserver;
import jakarta.inject.Inject;

import java.util.Optional;


public class DoctorgRPCService extends DoctorServiceGrpc.DoctorServiceImplBase {
    @Inject
    DoctorService doctorService;

    @Override
    public void addDoctor(AddDoctorRequest request, StreamObserver<EmptyResponse> responseObserver) {
        doctorService.AddDoctor(DoctorgRPCMapper.fromgRPC(request));
        responseObserver.onNext(
                EmptyResponse.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void updateDoctor(UpdateRequest request, StreamObserver<UpdatedResponse> responseObserver) {
        Optional<Doctor> doctor = doctorService.UpdateDoctor(DoctorgRPCMapper.fromgRPCUpdate(request));

        if (doctor.isPresent()) {
            responseObserver.onNext(

                    UpdatedResponse.newBuilder()
                            .setUpdate(
                                    DoctorgRPCMapper.togRPCUpdate(doctor.get())
                            )
                            .build()

            );

        } else {
            responseObserver.onNext(UpdatedResponse.newBuilder().build());
        }
        responseObserver.onCompleted();
    }
}