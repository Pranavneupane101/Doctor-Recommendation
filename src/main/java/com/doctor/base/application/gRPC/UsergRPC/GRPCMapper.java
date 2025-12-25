package com.doctor.base.application.gRPC.UsergRPC;


import com.doctor.base.application.Repository.PersonalInfo.DoctorDistanceResponseDTO;
import com.doctor.base.core.models.User;
import com.gRPC.doctorRecommendation.ListResponse;
import com.gRPC.doctorRecommendation.UserResponse;
import com.gRPC.doctorRecommendation.UsersRequest;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


  class GRPCMapper {

    public static  User fromgRPC(UsersRequest request ){
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
                     .addAllAppointments(
                                   dto.getAvailability().getAppointments().stream().map(LocalTime::toString).toList())
                     .setAvailableFrom(dto.getAvailability().getAvailablefrom().toString())
                     .setAvailableTo(dto.getAvailability().getAvailableTo().toString())
                     .setDate(dto.getAvailability().getDate().toString())
                    .build());
        }

        return ListResponse.newBuilder().addAllResponse(userResponses).build();

    }


}
