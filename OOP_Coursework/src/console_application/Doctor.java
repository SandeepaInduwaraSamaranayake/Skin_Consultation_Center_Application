package console_application;

import java.time.LocalDate;

/**
 COPYRIGHT (C) Sandeepa Induwara Samaranayake - 20210302 - W1867067- sandeepa.20210302@iit.ac.lk. All Rights Reserved.
 Object-Oriented Programming Coursework L5 sem 1
 @author Sandeepa Induwara Samaranayake
 @version 1 Console application
 */

/**
 * This class will handle the doctor related attributes and actions.
 */
public class Doctor extends Person
{
    private int medicalLicenceNumber;
    private String specialisation;
    private String category;

    /**
     * Default constructor of Doctor class.
     */
    public Doctor()
    {

    }

    /**
     * Constructor for Doctor class which accepts eight parameters such as name, surname, dateOfBirth, mobileNumber,
     * address, medicalLicenceNumber, specialisation, category.
     * @param name name of the doctor.
     * @param surname surname of the doctor.
     * @param dateOfBirth birthday of the doctor.
     * @param mobileNumber mobile number of the doctor.
     * @param medicalLicenceNumber licence number of the doctor.
     * @param specialisation specialised field of the doctor.
     * @param category category of the doctor.
     */
    public Doctor(int medicalLicenceNumber, String name, String surname, LocalDate dateOfBirth, String mobileNumber, String address, String specialisation, String category)
    {
        super(name, surname, dateOfBirth, mobileNumber, address);
        this.medicalLicenceNumber = medicalLicenceNumber;
        this.specialisation = specialisation;
        this.category = category;
    }

    /**
     * getter for medicalLicenceNumber.
     * @return medical licence number as an integer.
     */
    public int getMedicalLicenceNumber()
    {
        return medicalLicenceNumber;
    }

    /**
     * setter for medicalLicenceNumber.
     * @param medicalLicenceNumber medical licence number.
     */
    public void setMedicalLicenceNumber(int medicalLicenceNumber)
    {
        this.medicalLicenceNumber = medicalLicenceNumber;
    }

    /**
     * getter for specialisation.
     * @return specialisation of the doctor.
     */
    public String getSpecialisation()
    {
        return specialisation;
    }

    /**
     * setter for specialisation.
     * @param specialisation specialisation of the doctor like  cosmetic dermatology,
     * medical dermatology, paediatric dermatology.
     */
    public void setSpecialisation(String specialisation)
    {
        this.specialisation = specialisation;
    }

    /**
     * getter for category
     * @return category of the doctor.
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * setter for category.
     * @param category category of the doctor like family physician, surgeon etc.
     */
    public void setCategory(String category)
    {
        this.category = category;
    }

}
