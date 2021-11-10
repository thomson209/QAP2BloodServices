package com.keyin.manager;

import com.keyin.domain.Database;
import com.keyin.domain.appointment.AppointmentSlot;
import com.keyin.domain.appointment.BloodDonationAppointment;
import com.keyin.domain.donor.BloodDonor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class BloodDonationAppointmentManagerTest {
    @Mock
    private Database mockDatabase;

    @Test
    public void testBloodDonorTooYoung() {
        BloodDonor bloodDonorTooYoung = new BloodDonor();
        bloodDonorTooYoung.setFirstName("Doug");
        bloodDonorTooYoung.setLastName("Thomson");
        bloodDonorTooYoung.setBloodType("A");
        bloodDonorTooYoung.setDateOfBirth(LocalDate.of(2018, Month.FEBRUARY, 11));
        bloodDonorTooYoung.setId(1);

        Mockito.when(mockDatabase.getDonor(1)).thenReturn(
                bloodDonorTooYoung
        );

        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(mockDatabase);

        try {
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(1);
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("Donor too young!"));
        }
    }

        //Assertions.fail("Did not hit expected Exception!");
    @Test
    public void testBloodDonorTooOld() {
        BloodDonor bloodDonorTooOld = new BloodDonor();
        bloodDonorTooOld.setFirstName("Doug");
        bloodDonorTooOld.setLastName("Thomson");
        bloodDonorTooOld.setBloodType("O");
        bloodDonorTooOld.setDateOfBirth(LocalDate.of( 1933 , Month.MARCH , 24 ));
        bloodDonorTooOld.setId(1);

        Mockito.when(mockDatabase.getDonor(1)).thenReturn(
                bloodDonorTooOld
        );

        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(mockDatabase);

        try {
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(1);
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("Donor too old!"));
        }


    }

    @Test
    public void testBloodDonorInvalidType() {
        BloodDonor bloodDonorInvalidType = new BloodDonor();
        bloodDonorInvalidType.setFirstName("Doug");
        bloodDonorInvalidType.setLastName("Thomson");
        bloodDonorInvalidType.setBloodType("A");
        bloodDonorInvalidType.setDateOfBirth(LocalDate.of( 1985 , Month.FEBRUARY , 11 ));
        bloodDonorInvalidType.setId(1);

        Mockito.when(mockDatabase.getDonor(1)).thenReturn(
                bloodDonorInvalidType
        );

        ArrayList<AppointmentSlot> appointmentSlots = new ArrayList<AppointmentSlot>();

        AppointmentSlot appointmentSlotTypeA = new AppointmentSlot();
        appointmentSlotTypeA.setId(1);
        appointmentSlotTypeA.setLocation("123 Water St. St. John's NL");
        appointmentSlotTypeA.setBloodType("A");
        appointmentSlots.add(appointmentSlotTypeA);

        AppointmentSlot appointmentSlot = new AppointmentSlot();
        appointmentSlot.setId(1);
        appointmentSlot.setLocation("123 Water St. St. John's NL");
        appointmentSlot.setBloodType("B");
        appointmentSlots.add(appointmentSlot);


        Mockito.when(mockDatabase.getAppointmentSlots()).thenReturn(appointmentSlots);

        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(mockDatabase);

        try {
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(1);
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("Invalid blood type!"));
        }
    }

    @Test
    public void testTooSoonDate() {
        BloodDonor tooSoonDate = new BloodDonor();
        tooSoonDate.setFirstName("Doug");
        tooSoonDate.setLastName("Thomson");
        tooSoonDate.setBloodType("O");
        tooSoonDate.setDateOfBirth(LocalDate.of(1973, Month.MARCH, 24));
        tooSoonDate.setLastDonationDate(LocalDate.of(2021, Month.NOVEMBER, 11));
        tooSoonDate.setId(1);

        Mockito.when(mockDatabase.getDonor(1)).thenReturn(
                tooSoonDate
        );
        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(mockDatabase);

        try {
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(1);
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("Too soon to donate!"));
        }
    }
    @Test
    public void testTooLateDate() {
        BloodDonor tooLateDate = new BloodDonor();
        tooLateDate.setFirstName("Doug");
        tooLateDate.setLastName("Thomson");
        tooLateDate.setBloodType("O");
        tooLateDate.setDateOfBirth(LocalDate.of( 1973 , Month.MARCH , 24 ));
        tooLateDate.setLastDonationDate(LocalDate.of(2019, Month.NOVEMBER, 11));
        tooLateDate.setId(1);

        Mockito.when(mockDatabase.getDonor(1)).thenReturn(
                tooLateDate
        );
        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(mockDatabase);

        try {
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(1);
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("Too late to donate!"));
        }
    }
    @Test
    public void testBloodDonorHasAppt() {
        BloodDonor bloodDonorHasAppt = new BloodDonor();
        bloodDonorHasAppt.setFirstName("Doug");
        bloodDonorHasAppt.setLastName("Thomson");
        bloodDonorHasAppt.setBloodType("A");
        bloodDonorHasAppt.setDateOfBirth(LocalDate.of( 1985 , Month.FEBRUARY , 11 ));
        bloodDonorHasAppt.setNextApptDate(LocalDate.of(2021 , Month.NOVEMBER, 29));
        bloodDonorHasAppt.setId(1);

        Mockito.when(mockDatabase.getDonor(1)).thenReturn(
                bloodDonorHasAppt
        );

        ArrayList<AppointmentSlot> appointmentSlots = new ArrayList<AppointmentSlot>();

        AppointmentSlot appointmentSlotTypeA = new AppointmentSlot();
        appointmentSlotTypeA.setId(1);
        appointmentSlotTypeA.setLocation("123 Water St. St. John's NL");
        appointmentSlotTypeA.setBloodType("A");
        appointmentSlots.add(appointmentSlotTypeA);


        AppointmentSlot appointmentSlot = new AppointmentSlot();
        appointmentSlot.setId(1);
        appointmentSlot.setLocation("123 Water St. St. John's NL");
        appointmentSlot.setBloodType("B");
        appointmentSlots.add(appointmentSlot);


        Mockito.when(mockDatabase.getAppointmentSlots()).thenReturn(appointmentSlots);

        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(mockDatabase);

        try {
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(1);
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertFalse(e.getMessage().equalsIgnoreCase("Appointment already scheduled!"));
        }
    }
}
