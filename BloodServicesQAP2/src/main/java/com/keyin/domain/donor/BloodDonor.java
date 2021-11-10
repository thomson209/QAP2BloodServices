package com.keyin.domain.donor;

import java.time.LocalDate;

public class BloodDonor {
    /*
    o	Donor ID
    o	First Name
    o	Last Name
    o	Date of Birth
    o	Blood Type
    o	Next Appointment
    o	Last Donation Date
    */

    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String bloodType;
    private LocalDate nextApptDate;
    private LocalDate lastDonationDate;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBloodType()
    {
        return bloodType;
    }

    public void setBloodType(String bloodType)
    {
        this.bloodType = bloodType;
    }

    public LocalDate getNextApptDate()
    {
        return nextApptDate;
    }

    public void setNextApptDate(LocalDate nextApptDate)
    {
        this.nextApptDate = nextApptDate;
    }

    public LocalDate getLastDonationDate()
    {
        return lastDonationDate;
    }

    public void setLastDonationDate(LocalDate lastDonationDate)
    {
        this.lastDonationDate = lastDonationDate;
    }
}
