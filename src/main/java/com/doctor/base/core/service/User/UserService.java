package com.doctor.base.core.service.User;

import com.doctor.base.application.Repository.PersonalInfo.DoctorDistanceResponseDTO;

import com.doctor.base.core.models.User;

import java.util.List;


public interface UserService {
    List<DoctorDistanceResponseDTO>  getDoctorList(User user);


}
