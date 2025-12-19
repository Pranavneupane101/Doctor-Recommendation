package com.doctor.base.application.Controller;


import com.doctor.base.core.models.DoctorLocation;
import com.doctor.base.core.service.Doctor.DoctorLocationService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import jakarta.inject.Inject;


@Controller("/location")

public class DoctorLocationController {
    @Inject
    DoctorLocationService doctorLocationService;
    @Post("/add")
    public HttpResponse<DoctorLocation> Addlocation(@Body DoctorLocation location){
        boolean status=doctorLocationService.AddLocation(location);
        if(status){
            return HttpResponse.ok(location);
        }
        return HttpResponse.notFound(null);
    }
    @Put("/update")
    public HttpResponse<DoctorLocation> Updatelocation(@Body DoctorLocation location){
        boolean status=doctorLocationService.UpdateLocation(location);
        if(status){
            return HttpResponse.ok(location);

        }
        return HttpResponse.notFound(null);
    }
}
