package console_application;

import java.time.LocalDate;

/**
 COPYRIGHT (C) Sandeepa Induwara Samaranayake - 20210302 - W1867067- sandeepa.20210302@iit.ac.lk. All Rights Reserved.
 Object-Oriented Programming Coursework L5 sem 1
 @author Sandeepa Induwara Samaranayake
 @version 1 Console application
 */

/**
 * person class will be the parent class for both Doctor and Patient classes.
 */
public class Person
{
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String mobileNumber;
    private String address;

    /**
     * Default constructor for Person class.
     */
    public Person()
    {

    }

    /**
     * Constructor for Person class which accepts one parameter name.
     * @param name first name of the person.
     */
    public Person(String name)
    {
        this.name = name;

    }

    /**
     * Constructor for Person class which accepts two parameters name and surname.
     * @param name first name of the person.
     * @param surname surname of the person.
     */
    public Person(String name, String surname)
    {
        this.name = name;
        this.surname = surname;
    }

    /**
     * Constructor for Person class which accepts two parameters name, surname and birthday.
     * @param name first name of the person.
     * @param surname surname of the person.
     * @param dateOfBirth birthday of the person.
     */
    public Person(String name, String surname, LocalDate dateOfBirth)
    {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Constructor for Person class which accepts two parameters name, surname, birthday and mobile number.
     * @param name first name of the person.
     * @param surname surname of the person.
     * @param dateOfBirth birthday of the person.
     * @param mobileNumber mobile number of the person.
     */
    public Person(String name, String surname, LocalDate dateOfBirth, String mobileNumber)
    {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
    }

    /**
     * Constructor for Person class which accepts two parameters name, surname, birthday, mobile number, address.
     * @param name name of the person
     * @param surname surname of the person.
     * @param dateOfBirth birthday of the person.
     * @param mobileNumber mobile number of the person.
     * @param address address of the person.
     */
    public Person(String name, String surname, LocalDate dateOfBirth, String mobileNumber, String address)
    {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
        this.address = address;
    }
    /**
     * getter for name.
     * @return first name of the person.
     */
    public String getName()
    {
        return name;
    }

    /**
     * setter for name.
     * @param name first name of the person.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * getter for surname.
     * @return surname of the person.
     */
    public String getSurname()
    {
        return surname;
    }

    /**
     * setter for the surname
     * @param surname surname of the person.
     */
    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    /**
     * getter for the date of birth.
     * @return date of birth as a Date.
     */
    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    /**
     * setter for the date of birth.
     * @param dateOfBirth date of the birth as a Date
     */
    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * getter for mobileNumber.
     * @return mobile number of the person.
     */
    public String getMobileNumber()
    {
        return mobileNumber;
    }

    /**
     * setter for the mobileNumber.
     * @param mobileNumber mobile number of the person
     */
    public void setMobileNumber(String mobileNumber)
    {
        this.mobileNumber = mobileNumber;
    }

    /**
     * getter of the address.
     * @return address of the person.
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * setter for the address.
     * @param address address of the person.
     */
    public void setAddress(String address)
    {
        this.address = address;
    }


}
