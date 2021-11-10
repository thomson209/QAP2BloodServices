package com.keyin.domain.appointment;
import java.time.LocalDateTime;


public class BloodDonationAppointment {
    private int apptId;
    private LocalDateTime apptDateTime;
    private int apptDuration;
    private String apptBloodType;
    private boolean isFirstTime;

    private int donorId;
    private String apptLocation;

    public BloodDonationAppointment(int apptId, LocalDateTime apptDateTime, int apptDuration, String apptLocation, String apptBloodType, boolean isFirstTime, int donorId) {
        this.apptId = apptId;
        this.apptDateTime = apptDateTime;
        this.apptDuration = apptDuration;
        this.apptLocation = apptLocation;

        this.apptBloodType = apptBloodType;
        this.isFirstTime = isFirstTime;
        this.donorId = donorId;
    }

    public int getApptId() {
        return apptId;
    }

    public void setApptId(int apptId) {
        this.apptId = apptId;
    }

    public LocalDateTime getApptDateTime() {
        return apptDateTime;
    }

    public void setApptDateTime(LocalDateTime apptDateTime) {
        this.apptDateTime = apptDateTime;
    }

    public int getApptDuration() {
        return apptDuration;
    }

    public void setApptDuration(int apptDuration) {
        this.apptDuration = apptDuration;
    }

    public String getLocation() {
        return apptLocation;
    }

    public void setLocation(String location) {
        this.apptLocation = apptLocation;
    }

    public String getApptBloodType() {
        return apptBloodType;
    }

    public void setApptBloodType(String apptBloodType) {
        this.apptBloodType = apptBloodType;
    }

    public boolean isFirstTimeDonor() {
        return isFirstTime;
    }

    public void setFirstTimeDonor(boolean firstTimeDonor) {
        this.isFirstTime = firstTimeDonor;
    }

    public int getDonorId() {
        return donorId;
    }

    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }
}


