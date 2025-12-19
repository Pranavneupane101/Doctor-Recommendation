package com.doctor.base.application.Controller;


import com.doctor.base.core.models.User;
import com.doctor.base.core.service.User.UserService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import java.util.List;

@Controller("/user")
public class UserRequest {
    @Inject
    UserService userService;

    @Post("/get")
    public  List<DoctorDistanceResponseDTO> getDoctors(@Body User user) {
        return userService.getDoctorList(user);
    }
}
