package console_application;


import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 COPYRIGHT (C) Sandeepa Induwara Samaranayake - 20210302 - W1867067- sandeepa.20210302@iit.ac.lk. All Rights Reserved.
 Object-Oriented Programming Coursework L5 sem 1
 @author Sandeepa Induwara Samaranayake
 @version 1 Console application
 */

/**
 * This class will do the validations.
 */
public class Validation
{
    private static final String NAME_REGEX = "^[a-zA-Z]*$";                                                                 // no signs, no numbers, no whitespaces
    private static final String ADDRESS_REGEX = "^(\\d+) ?([A-Za-z](?= ))? (.*?) ([^ ]+?) ?((?<= )APT)? ?((?<= )\\d*)?$";
    private static final String SPECIALISATION_CATEGORY_REGEX = "[a-zA-Z\\s]+";                                             // no signs. no numbers
    private static final String REQUIREMENT_REGEX = "[a-zA-Z0-9,.\\s]*$";                                                   // only allows letters, numbers, comma and dot

    /**
     * Method to validate First name and surname.
     * @param name first or surname of doctor or patient.
     * @return
     */
    public boolean validateName(String name)
    {
        Pattern pattern = Pattern.compile(NAME_REGEX);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    /**
     * Method to validate mobile number.
     * @param mobileNumber mobile number of the doctor or patient
     * @return valid or not
     */
    public boolean validateMobileNo(String mobileNumber)
    {
        if (mobileNumber.matches("\\d{10}"))                                        // validate phone numbers of format "1234567890"
            return true;
        else if (mobileNumber.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))      // validating phone number with -, . or spaces
            return true;
        else if (mobileNumber.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))  // validating phone number with extension length from 3 to 5
            return true;
        else if (mobileNumber.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))                // validating phone number where area code is in braces ()
            return true;
        else if (mobileNumber.matches("^\\+[0-9]{1,}[\\s-]{0,1}[0-9\\s]{3,}$"))     // validating +94703568839 type numbers
            return true;
        else                                                                              // return false if nothing matches the input
            return false;
    }

    /**
     * Method to validate a date.
     * @param inputDate date that want to validate
     * @param isPassed if a past value time ----> true a future value -----> false
     * @return date valid or not
     */
    public boolean validateDate(String inputDate, boolean isPassed) {
        int year,month,day;

        // separating the year, month and day.
        try
        {
            year = Integer.parseInt(inputDate.substring(0, 4));
            month = Integer.parseInt(inputDate.substring(5, 7));
            day = Integer.parseInt(inputDate.substring(8));
            if ( !( inputDate.charAt(4) == '-' && inputDate.charAt(7) == '-') )
            {
                return false;
            }
        }
        catch (Exception exc)
        {
            return false;
        }

        //int day, int month, int year

        // Check that the month is in the range 1-12
        if (month < 1 || month > 12) {
            return false;
        }

        // Get the number of days in the month
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Check that the day is in the range 1-maxDays
        if (day < 1 || day > maxDay) {
            return false;
        }

        // Check that the date is not in the future
        // Here using Date objects to verify that the given date is not in the future.
        Calendar now = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        Date dob = cal.getTime();
        Date today = now.getTime();

        if(isPassed)
        {
            if (dob.after(today))
            {
                return false;
            }
            return true;
        }
        else
        {
            if (dob.after(today))
            {
                return true;
            }
            return false;
        }
    }

    /**
     * Method to validate address.
     * @param address address of doctor or patient
     * @return valid or not
     */
    public boolean validateAddress(String address)
    {
        return address.matches(ADDRESS_REGEX);
    }

    /**
     * Method to validate specialisation or category of the doctor.
     * @param specialisationOrCategory specialisation or category of the doctor
     * @return valid or not
     */
    public boolean validateSpecialisationAndCategory(String specialisationOrCategory)
    {
        return Pattern.matches(SPECIALISATION_CATEGORY_REGEX, specialisationOrCategory);
    }

    /**
     * Method to validate Medical Licence Number or patientId.
     * @param medLicenceNumberOrPatientId medical licence number of the doctor or patient id of the patient.
     * @return medical licence number valid, not valid or duplicated.
     *
     * role signature
     * 1 - doctor
     * 2 - patient
     *
     * process signature
     * 1 - adding doctor
     * 2 - adding doctor for consultation or deleting doctor
     * 3 - adding patient
     * 4 - adding patient for consultation
     *
     * Return Signature
     * 1 - The medical licence number or patientId is valid.
     * 2 - The medical licence number or patientId is Not valid.
     * 3 - The medical licence number is Duplicating.
     * 4 - The patientId is duplicating.
     * 5 - The given medical licence is not existing.
     * 6 - The patientId is not existing.
     */
    public int validateMedLicenceNumberOrPatientId(String medLicenceNumberOrPatientId, int role, int process)
    {
        int medLicenceOrPatId;

        try
        {
            medLicenceOrPatId = Integer.parseInt(medLicenceNumberOrPatientId);
        }
        catch (Exception exc)
        {
            return 2;                     // id not valid
        }

        boolean isValidFormat = medLicenceNumberOrPatientId.length() == 6 && medLicenceOrPatId >= 0 && medLicenceOrPatId <= 999999;
        if( isValidFormat )
        {
            if(role == 1 && process == 1)                                                           // check med licence number for adding doctors.
            {
                if (!WestminsterSkinConsultationManager.getDocArrList().isEmpty())
                {
                    for (Doctor doc : WestminsterSkinConsultationManager.getDocArrList())
                    {
                        if (doc.getMedicalLicenceNumber() == medLicenceOrPatId)
                        {
                            return 3;     // med licence duplicating
                        }
                    }
                    return 1;             // med licence valid
                }
                return 1;                // med licence valid
            }
            else if (role == 1 && process == 2)                                                    // check medical licence for doctor tasks.
            {
                if(!WestminsterSkinConsultationManager.getDocArrList().isEmpty())
                {
                    for(Doctor doc : WestminsterSkinConsultationManager.getDocArrList())
                    {
                        if(doc.getMedicalLicenceNumber() == medLicenceOrPatId)
                        {
                            return 1;   // valid
                        }
                    }
                    return 5;           // med licence not existing
                }
                return 2;               // not valid
            }
            else if(role ==2 && process == 3)                                                      // check patientId for adding patients.
            {
                if (!WestminsterSkinConsultationManager.getPatientArrayList().isEmpty())
                {
                    for (Patient patient : WestminsterSkinConsultationManager.getPatientArrayList())
                    {
                        if (patient.getPatientId() == medLicenceOrPatId)
                        {
                            return 4;    // patient id duplicating. It is possible to duplicate patient id when placing consultations.
                        }
                    }
                    return 1;            // patient is valid
                }
                return 1;                // patient id valid
            }
            else if (role == 2 && process == 4)                                                    // check patientId for patient tasks.
            {
                if(!WestminsterSkinConsultationManager.getPatientArrayList().isEmpty())
                {
                    for(Patient patient : WestminsterSkinConsultationManager.getPatientArrayList())
                    {
                        if(patient.getPatientId() == medLicenceOrPatId)
                        {
                            return 1;   // patient id valid
                        }
                    }
                    return 6;           // patient id not existing
                }
                return 2;               // patient id not valid
            }

            else return 0;
        }
        else return 2;
    }

    /**
     * Method to validate Patient Requirement.
     * @param requirement requirement of the patient
     * @return requirement valid or not
     */
    public boolean validateRequirementOrNotes(String requirement)
    {
        return Pattern.matches(REQUIREMENT_REGEX, requirement);
    }

    /**
     * this method will validate the user's input of timeslot
     * @param timeSlot user given timeslot id
     * @return boolean value which indicates valid or not
     */
    public boolean validateTimeSlotInput(String timeSlot)
    {
        try
        {
            int selectTimeslot =  Integer.parseInt(timeSlot);
            if(selectTimeslot >= 1 && selectTimeslot <= 10) return true;
        }
        catch (Exception exc)
        {
            return false;
        }
        return false;
    }

    /**
     * valid     --> return true
     * not valid --> return false
     * @param timeSlot timeslot object according to the user provided details
     * @param timeSlotDate timeslot date
     * @param medLicenceNo medical licence number
     * @return boolean value which indicates valid or not
     */
    public boolean validateTimeSlotNotDuplicating(TimeSlot timeSlot, LocalDate timeSlotDate, int medLicenceNo)
    {
        boolean isValid = true;
        if( !WestminsterSkinConsultationManager.getConsultArrList().isEmpty() )
        {
            for (Consultation consult : WestminsterSkinConsultationManager.getConsultArrList())
            {
                if ( consult.getTimeSlot().getTimeslotId() == timeSlot.getTimeslotId() && consult.getConsultationDate().equals(timeSlotDate) && consult.getConsultDoctorMedNo() == medLicenceNo )
                {
                    isValid = false;
                    break;
                }
            }
            return isValid;
        }
        else
        {
            return true;
        }
    }

    /**
     *
     * parameter signature
     * 1 - cancelling consultations.
     *
     * return signature
     * true - consultation id is valid.
     * false - consultation id is not valid.
     * @param inputConsultId consultation id.
     * @return true - valid | false - invalid
     */
    public boolean validateConsultationId( String inputConsultId )
    {
        int consultationId;
        boolean isValid = false;
        try
        {
            // check for non-numeric values.
            consultationId = Integer.parseInt(inputConsultId);

            // check for non-existing consultation ids.
            for (Consultation consult : WestminsterSkinConsultationManager.getConsultArrList())
            {
                if (consult.getConsultationId() == consultationId)
                {
                    isValid = true;
                    break;
                }
            }
        }
        catch (Exception exc)
        {
            isValid = false;
        }
        return isValid;
    }
}
