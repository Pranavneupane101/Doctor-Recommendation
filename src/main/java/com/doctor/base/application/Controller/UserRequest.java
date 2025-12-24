package com.doctor.base.application.Controller;


import com.doctor.base.application.Repository.PersonalInfo.DoctorDistanceResponseDTO;
import com.doctor.base.core.models.Availability;
import com.doctor.base.core.models.User;
import com.doctor.base.core.service.User.UserService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Map;

@Controller("/user")
public class UserRequest {
    @Inject
    UserService userService;

    @Post("/get")
    public List<DoctorDistanceResponseDTO> getDoctors(@Body User user) {
        return userService.getDoctorList(user);
    }
}
