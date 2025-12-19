package com.doctor.base.application.Repository.PersonalInfo;




public class DoctorEntity {

        private String doctorId;
        private String doctorDesignation;
        private String doctorName;
        private String experties;
        private String experience;
        private String fee;
        private String location;



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

    public String getExperties() {
        return experties;
    }

    public void setExperties(String experties) {
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

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }


