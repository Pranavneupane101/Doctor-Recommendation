package com.doctor.base.application.Repository.PersonalInfo;


import java.util.Set;

public class DoctorEntity {

        private String doctorId;
        private String doctorDesignation;
        private String doctorName;
        private Set<String> experties;
        private String experience;
        private String fee;
        private double latitude;
        private double  longitude;
        private String phone_no;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getDoctorDesignation() {
            return doctorDesignation;
        }

        public void setDoctorDesignation(String doctorDesignation) {
            this.doctorDesignation = doctorDesignation;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

    public Set<String> getExperties() {
        return experties;
    }

    public void setExperties(Set<String> experties) {
        this.experties = experties;
    }

    public String getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(String doctorId) {
            this.doctorId = doctorId;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }


    }


