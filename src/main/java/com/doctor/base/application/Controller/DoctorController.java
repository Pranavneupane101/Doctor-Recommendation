package com.doctor.base.application.Controller;

import com.doctor.base.core.models.Doctor;
import com.doctor.base.core.service.Doctor.DoctorService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;


@Controller("/doctor")
public class DoctorController {
    @Inject
    DoctorService  doctorService;
    @Post("/add")
    public HttpResponse<Doctor>AddDoctor(@Body Doctor doctor){

        boolean status=doctorService.AddDoctor(doctor);
        if(status){
            return HttpResponse.ok().body(doctor);
        }
        return HttpResponse.badRequest();
    }
    @Put("/update")
    public HttpResponse<Doctor>UpdateDoctor(@Body Doctor doctor){
        boolean status=doctorService.UpdateDoctor(doctor);
        if(status){
            return HttpResponse.ok().body(doctor);

        }
        return HttpResponse.badRequest();
    }
    @Post("/get")
    public HttpResponse<Doctor>GetDoctor(@Body FindDoctorDTO dto){
        Doctor doctor=doctorService.findDoctor(dto.getDoctor_id(),dto.getExperties());
        if(doctor!=null){
            return HttpResponse.ok().body(doctor);
        }else{
            return HttpResponse.notFound();
        }
    }

}
