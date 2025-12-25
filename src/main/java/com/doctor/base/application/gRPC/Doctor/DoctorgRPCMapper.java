package com.doctor.base.application.gRPC.Doctor;

import com.doctor.base.core.models.Doctor;
import com.gRPC.doctor.AddDoctorRequest;
import com.gRPC.doctor.UpdateRequest;

import java.util.HashSet;
import java.util.Set;

 class DoctorgRPCMapper {
    public static Doctor fromgRPC(AddDoctorRequest addDoctorRequest){

        Doctor doctor = new Doctor();
        doctor.setDoctorDesignation(addDoctorRequest.getDoctorDesignation());
        doctor.setDoctorName(addDoctorRequest.getDoctorName());
        doctor.setPhone_no(addDoctorRequest.getPhoneNo());
        Set<String> set = new HashSet<>(addDoctorRequest.getExpertiesList());
        doctor.setExperties(set);
        doctor.setLatitude(addDoctorRequest.getLatitude());
        doctor.setLongitude(addDoctorRequest.getLongitude());
        doctor.setExperience(addDoctorRequest.getExperience());
        doctor.setFee(addDoctorRequest.getFee());
        return doctor;

    }

     public static Doctor fromgRPCUpdate(UpdateRequest updateRequest){

         Doctor doctor = new Doctor();
         doctor.setDoctorId(updateRequest.getDoctorId());
         doctor.setDoctorDesignation(updateRequest.getDoctorDesignation());
         doctor.setDoctorName(updateRequest.getDoctorName());
         doctor.setPhone_no(updateRequest.getPhoneNo());
         Set<String> set = new HashSet<>(updateRequest.getExpertiesList());
         doctor.setExperties(set);
         doctor.setLatitude(updateRequest.getLatitude());
         doctor.setLongitude(updateRequest.getLongitude());
         doctor.setExperience(updateRequest.getExperience());
         doctor.setFee(updateRequest.getFee());
         return doctor;

     }

     public static UpdateRequest togRPCUpdate(Doctor doctor ){

        return UpdateRequest.newBuilder()
                .setDoctorId(doctor.getDoctorId())
                .setExperience(doctor.getExperience())
                .setLatitude(doctor.getLatitude())
                .setLongitude(doctor.getLongitude())
                .setExperience(doctor.getExperience())
                .setFee(doctor.getFee())
                .addAllExperties(doctor.getExperties())
                .setPhoneNo(doctor.getPhone_no())
                .setDoctorName(doctor.getDoctorName())
                .build();

     }


}
