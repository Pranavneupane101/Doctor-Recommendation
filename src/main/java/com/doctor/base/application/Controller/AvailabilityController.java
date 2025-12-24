package com.doctor.base.application.Controller;

import com.doctor.base.core.models.Availability;
import com.doctor.base.core.service.Doctor.AvailabilityService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

import java.util.Optional;


@Controller("/availability")
public class AvailabilityController {
    @Inject
    AvailabilityService availabilityService;
    @Post("/add")
   public HttpResponse<Availability> AddAvailability(@Body Availability availability){
       boolean status=availabilityService.AddAvailability(availability);
       if (status){
           return HttpResponse.ok(availability);
       }else{
           return HttpResponse.notFound();
       }
   }
    @Post("/update")
   public HttpResponse<Availability> UpdateAvailability(@Body Availability availability){
        Optional<Availability> optional=availabilityService.UpdateAvailability(availability);
        if (optional.isPresent()){
           return HttpResponse.ok(optional.get());
        }
        return HttpResponse.notFound();
   }
}
