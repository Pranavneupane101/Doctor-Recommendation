package com.doctor.base.application.Controller;

import com.doctor.base.core.models.Doctor;
import com.doctor.base.core.service.Doctor.DoctorService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.Optional;


@Controller("/doctor")
public class DoctorController {
    @Inject
    DoctorService  doctorService;
    @Post("/add")
    public HttpResponse<Doctor>AddDoctor(@Body Doctor doctor){

        doctorService.AddDoctor(doctor);

            return HttpResponse.ok().body(doctor);


    }
    @Put("/update")
    public HttpResponse<Doctor>UpdateDoctor(@Body Doctor doctor){
        Optional<Doctor> updatedDoctor=doctorService.UpdateDoctor(doctor);
        if(updatedDoctor.isPresent()){
            return HttpResponse.ok().body(doctor);

        }
        return HttpResponse.badRequest();
    }
    @Post("/get")
    public HttpResponse<Doctor>GetDoctor(@Body FindDoctorDTO dto){
        Optional<Doctor> doctor=doctorService.findDoctor(dto.getDoctor_id() );
        if(doctor.isPresent()){
            return HttpResponse.ok().body(doctor.get());
        }else{
            return HttpResponse.notFound();
        }
    }

    @Delete("/delete/{doctorId}")
    public HttpResponse<String>DeleteDoctor(@PathVariable String doctorId){

        boolean status=doctorService.DeleteDoctor(doctorId);
        if(status){
            return HttpResponse.ok("Success");

        }else   {
            return HttpResponse.notFound();

        }
    }

}
