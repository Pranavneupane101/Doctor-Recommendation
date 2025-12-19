package com.doctor.base.core.service.User;

import com.doctor.base.application.Controller.DoctorDistanceResponseDTO;
import com.doctor.base.core.models.Doctor;
import com.doctor.base.core.models.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {
    List<DoctorDistanceResponseDTO> getDoctorList(User user);


}
