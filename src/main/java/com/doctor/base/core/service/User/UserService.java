package com.doctor.base.core.service.User;

import com.doctor.base.application.Repository.PersonalInfo.DoctorDistanceResponseDTO;

import com.doctor.base.core.models.Availability;
import com.doctor.base.core.models.User;

import java.util.List;
import java.util.Map;


public interface UserService {
    List<DoctorDistanceResponseDTO> getDoctorList(User user);


}
