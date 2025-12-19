package com.doctor.base.application.Repository.Location;

  class LocationEntity {
    private String doctorId;
    private double latitude;
    private double longitude;
    private String hashcode6;
    private String hashcode5;
    private String experties;
    public String getDoctorId() {
        return doctorId;
    }

    public String getExperties() {
        return experties;
    }

    public void setExperties(String experties) {
        this.experties = experties;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

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

      public String getHashcode6() {
          return hashcode6;
      }

      public void setHashcode6(String hashcode6) {
          this.hashcode6 = hashcode6;
      }

      public String getHashcode5() {
          return hashcode5;
      }

      public void setHashcode5(String hashcode5) {
          this.hashcode5 = hashcode5;
      }
  }
