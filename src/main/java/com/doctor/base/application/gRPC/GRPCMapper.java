package com.doctor.base.application.gRPC;


import com.doctor.base.application.Repository.PersonalInfo.DoctorDistanceResponseDTO;
import com.doctor.base.core.models.User;
import com.gRPC.doctorRecommendation.ListResponse;
import com.gRPC.doctorRecommendation.UserResponse;
import com.gRPC.doctorRecommendation.userRequest;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


  class GRPCMapper {

    public static  User fromgRPC(userRequest request ){
        User user = new User();
        user.setUserId(request.getUserId());
        user.setDoctorType(request.getDoctorType());
        user.setName(request.getName());
        user.setTime(LocalTime.now());
        user.setLatitude(request.getLatitude());
        user.setLongitude(request.getLongitude());


        return user;
    }

    public static  ListResponse togRPC(List<DoctorDistanceResponseDTO> sortedDTO){
        List<UserResponse> userResponses = new ArrayList<>();

        for(DoctorDistanceResponseDTO dto:  sortedDTO){


           userResponses.add( UserResponse.newBuilder()
                    .setDoctorName(dto.getDoctor().getDoctorName())
                    .setDistance(dto.getDistance())
                    .setDesignation(dto.getDoctor().getDoctorDesignation())
                    .setLatitude(dto.getDoctor().getLatitude())
                    .setLongitude(dto.getDoctor().getLongitude())
                    .addAllExperties(dto.getDoctor().getExperties())
                    .setExperience(dto.getDoctor().getExperience())
                           .setPhoneNo(dto.getDoctor().getPhone_no())
                    .build());
        }

        return ListResponse.newBuilder().addAllResponse(userResponses).build();

    }


}
