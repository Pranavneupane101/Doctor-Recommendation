package com.doctor.base.application.Controller;


import com.doctor.base.core.models.DoctorLocation;
import com.doctor.base.core.service.Doctor.DoctorLocationService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import jakarta.inject.Inject;

import java.util.Optional;


@Controller("/location")

public class DoctorLocationController {
    @Inject
    DoctorLocationService doctorLocationService;
    @Post("/add")
    public HttpResponse<DoctorLocation> Addlocation(@Body DoctorLocation location){
         doctorLocationService.AddLocation(location);
         return HttpResponse.ok(location);
    }
    @Put("/update")
    public HttpResponse<DoctorLocation> Updatelocation(@Body DoctorLocation location){
        Optional<DoctorLocation> updatedLocation= doctorLocationService.UpdateLocation(location);
        if(updatedLocation.isPresent()){
            return HttpResponse.ok(updatedLocation.get());
        }
        return HttpResponse.notFound( );
    }
}
