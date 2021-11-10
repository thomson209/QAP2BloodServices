package com.keyin.manager;

import com.keyin.domain.Database;
import com.keyin.domain.appointment.AppointmentSlot;
import com.keyin.domain.appointment.BloodDonationAppointment;
import com.keyin.domain.donor.BloodDonor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BloodDonationAppointmentManager {
    private Database database;
    public boolean isFirstTime;

    public BloodDonationAppointmentManager(Database database) {

        this.database = database;
    }

    public BloodDonationAppointment bookAppointment(int bloodDonorId) throws InvalidDonationSchedulingException {
        BloodDonationAppointment bloodDonationAppointment = null;

        BloodDonor bloodDonor = database.getDonor(bloodDonorId);

        LocalDate today = LocalDate.now();
        LocalDate tooYoungDate = today.minus(18, ChronoUnit.YEARS);
        LocalDate tooOldDate = today.minus(80, ChronoUnit.YEARS);
        LocalDate tooSoonDate = today.minus(56, ChronoUnit.DAYS);
        LocalDate tooLateDate = today.minus(365, ChronoUnit.DAYS);

        if (bloodDonor.getDateOfBirth().isAfter(tooYoungDate)) {
            throw new InvalidDonationSchedulingException("Donor too young!");
        }
        if (bloodDonor.getDateOfBirth().isBefore(tooOldDate)) {
            throw new InvalidDonationSchedulingException("Donor too old!");
        }

        while (isFirstTime = false) {
            if (bloodDonor.getLastDonationDate().isAfter(tooSoonDate)) {
                throw new InvalidDonationSchedulingException("Too soon to donate!");
            }
            if (bloodDonor.getLastDonationDate().isBefore(tooLateDate)) {
                throw new InvalidDonationSchedulingException("Too late to donate!");
            } else {
                System.out.println("First time donor!");
            }
        }
        List<AppointmentSlot> appointmentSlotList = database.getAppointmentSlots();

        for (AppointmentSlot appointmentSlot: appointmentSlotList) {
            if (appointmentSlot.getBloodType().equalsIgnoreCase(bloodDonor.getBloodType())) {

            } else {
                throw new InvalidDonationSchedulingException("Invalid blood type!");
            }
        }

        return bloodDonationAppointment;
    }

}
