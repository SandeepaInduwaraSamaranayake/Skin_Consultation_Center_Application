package console_application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.Scanner;

/**
 COPYRIGHT (C) Sandeepa Induwara Samaranayake - 20210302 - W1867067- sandeepa.20210302@iit.ac.lk. All Rights Reserved.
 Object-Oriented Programming Coursework L5 sem 1
 @author Sandeepa Induwara Samaranayake
 @version 1 Console application
 */

/**
 * This class will get all inputs from user.
 */
public class InputReceiver
{
    Validation val = new Validation();

    /**
     *
     * This method will get user input and return it.
     *
     * @param instruction the instruction which need to print as a prompt for user.
     * @return the given answer by user will return.
     */
    public String getData(String instruction)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print(instruction);
        String answer = sc.nextLine();
        return answer;
    }

    /**
     *
     * This method will use to get both first name and surname.
     *
     * @param instructions instructions to prompt user.
     * @param fieldName fieldname of the field to show in the error.
     * @param errDetails error details to show in the error.
     * @param example sample answer to help user.
     * @return if the user's answer is valid, answer will return. otherwise it will throw an error.
     */
    public String inputGetName(String instructions, String fieldName, String errDetails, String example)
    {
        final int FLAG = 1;
        while(true)
        {
            String docFirstName = getData(instructions);
            if (!docFirstName.equalsIgnoreCase("Q"))
            {
                if(val.validateName(docFirstName)) return docFirstName.toUpperCase();
                try
                {
                    throw new ValidateErrorException(FLAG, fieldName, errDetails, example);
                }
                catch (ValidateErrorException exc)
                {
                    System.out.println(exc);
                }
            }
            else return null;
        }
    }

    /**
     *
     * This method will use to get dates from the user.
     *
     * @param instructions instructions to prompt user.
     * @param fieldName fieldname of the field to show in the error.
     * @param errDetails  error details to show in the error.
     * @param example sample answer to help user.
     * @return if the user's answer is valid, answer will return. otherwise it will throw an error.
     */
    public LocalDate inputGetDate(String instructions, String fieldName, String errDetails, String example, boolean isPast)
    {
        final int FLAG = 3;
        while (true) {
            String inputDate = getData(instructions);
            if (!inputDate.equalsIgnoreCase("Q")) {
                if (val.validateDate(inputDate, isPast)) {
                   try {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = formatter.parse(inputDate);                                   // create a Date object using user input.
                        Instant instant =  date.toInstant();                                      // convert Date object to LocalDate type.
                        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
                        LocalDate localDate = zonedDateTime.toLocalDate();
                        return localDate;                                                         // return the localDate.
                   } catch (ParseException e) {
                        System.out.println("ERROR OCCURRED: THE GIVEN DATE VALIDATION FAILED.");
                    }
                } else {
                    try {
                        throw new ValidateErrorException(FLAG, fieldName, errDetails, example);
                    } catch (ValidateErrorException exc) {
                        System.out.println(exc);
                    }
                }
            } else return null;
        }
    }

    /**
     *
     * This method will use to get mobile numbers from the user.
     *
     * @param instructions instructions to prompt user.
     * @param fieldName fieldname of the field to show in the error.
     * @param errDetails  error details to show in the error.
     * @param example sample answer to help user.
     * @return if the user's answer is valid, answer will return. otherwise it will throw an error.
     */
        public String inputGetMobileNumber(String instructions, String fieldName, String errDetails, String example)
        {
            final int FLAG = 4;
            while (true) {
                String docMobileNumber = getData(instructions);
                if (!docMobileNumber.equalsIgnoreCase("Q")) {
                    if (val.validateMobileNo(docMobileNumber)) return docMobileNumber;
                    try {
                        throw new ValidateErrorException(FLAG, fieldName, errDetails, example);
                    } catch (ValidateErrorException exc) {
                        System.out.println(exc);
                    }
                } else return null;
            }
        }

    /**
     *
     * This method will use to get addresses from the user.
     *
     * @param instructions instructions to prompt user.
     * @param fieldName fieldname of the field to show in the error.
     * @param errDetails  error details to show in the error.
     * @param example sample answer to help user.
     * @return if the user's answer is valid, answer will return. otherwise it will throw an error.
     */
        public String inputGetAddress(String instructions, String fieldName, String errDetails, String example)
        {
            final int FLAG = 5;
            while (true)
            {
                String docAddress = getData(instructions);
                if (!docAddress.equalsIgnoreCase("Q")) {
                    if (val.validateAddress(docAddress)) return docAddress.toUpperCase();
                    try {
                        throw new ValidateErrorException(FLAG, fieldName, errDetails, example);
                    } catch (ValidateErrorException exc) {
                        System.out.println(exc);
                    }
                } else return null;
            }
        }

    /**
     *
     * This method will use to get medical licence numbers of the Doctor and patient ids of the patient.
     *
     * @param instructions instructions to prompt user.
     * @param fieldName fieldname of the field to show in the error.
     * @param errDetails  error details to show in the error.
     * @param example sample answer to help user.
     * @return if the user's answer is valid, answer will return. otherwise it will throw an error.
     *
     * Return value signature from validateMedLicenceNumberOrPatientId(String medLicenceNumberOrPatientId, int role, int process) method.
     * 1 - The medical licence number or patientId is valid.
     * 2 - The medical licence number or patientId is Not valid.
     * 3 - The medical licence number is Duplicating.
     * 4 - The patientId is duplicating.
     * 5 - The given medical licence is not existing.
     * 6 - The patientId is not existing.
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
     */
        public Integer inputGetMedLicenceNoOrPatientID(String instructions, String fieldName, String errDetails, String example, int role, int process)
        {
            final int FLAG = 6;
            while (true)
            {
                String docMedLicenceNoOrPatId = getData(instructions);
                if (!docMedLicenceNoOrPatId.equalsIgnoreCase("Q"))
                {
                    int validationOutput = val.validateMedLicenceNumberOrPatientId(docMedLicenceNoOrPatId, role, process);
                    if (validationOutput == 1) return Integer.parseInt(docMedLicenceNoOrPatId);
                    else
                    {
                        if(validationOutput == 3)       // medical licence number is duplicating
                        {
                            errDetails = "YOU ARE NOT DUPLICATING MEDICAL LICENCE NUMBER. USE ANOTHER ID AND TRY AGAIN";
                        }
                        else if(validationOutput == 4)  // patient id is duplicating in consultations. It is possible to duplicate patient id in consultations.
                        {
                            return Integer.parseInt(docMedLicenceNoOrPatId);
                        }
                        else if (validationOutput == 5) // medical licence number not existing in the system.
                        {
                            errDetails = "THE ENTERED MEDICAL LICENCE NUMBER IS CORRECT. SEEMS LIKE THE ENTERED MEDICAL LICENCE NOT EXISTING IN THE SYSTEM. TRY AGAIN USING ANOTHER LICENCE NUMBER";
                        }
                        else if (validationOutput == 6) // patient id not existing in the system.
                        {
                            errDetails = "THE ENTERED PATIENT ID IS CORRECT. SEEMS LIKE THE ENTERED PATIENT ID NOT EXISTING IN THE SYSTEM. TRY AGAIN USING ANOTHER ID NUMBER";
                        }

                        try
                        {
                            throw new ValidateErrorException(FLAG, fieldName, errDetails, example);
                        }
                        catch (ValidateErrorException exc)
                        {
                            System.out.println(exc);
                        }
                    }
                } else return null;
            }
        }

    /**
     *
     * This method will use to get Doctor's specialisation and Doctor's Category from the user.
     *
     * @param instructions instructions to prompt user.
     * @param fieldName fieldname of the field to show in the error.
     * @param errDetails  error details to show in the error.
     * @param example sample answer to help user.
     * @return if the user's answer is valid, answer will return. otherwise it will throw an error.
     */
        public String inputGetSpecialisationOrCategory(String instructions, String fieldName, String errDetails, String example)
        {
            final int FLAG = 7;
            while(true) {
                String docSpecialisation = getData(instructions);
                if (!docSpecialisation.equalsIgnoreCase("Q")) {
                    if (val.validateSpecialisationAndCategory(docSpecialisation)) return docSpecialisation.toUpperCase();
                    try {
                        throw new ValidateErrorException(FLAG, fieldName, errDetails, example);
                    } catch (ValidateErrorException exc) {
                        System.out.println(exc);
                    }
                } else return null;
            }
        }

    /**
     *
     * This method will use to get patient requirement from the user.
     *
     * @param instructions instructions to prompt user.
     * @param fieldName fieldname of the field to show in the error.
     * @param errDetails  error details to show in the error.
     * @param example sample answer to help user.
     * @return if the user's answer is valid, answer will return. otherwise it will throw an error.
     */
       public String inputGetPatientRequirementOrNotes(String instructions, String fieldName, String errDetails, String example)
        {
            final int FLAG = 13;
            while (true) {
                String patientRequirement = getData(instructions);
                if (!patientRequirement.equalsIgnoreCase("Q"))
                {
                    if (val.validateRequirementOrNotes(patientRequirement)) return patientRequirement.toUpperCase();
                    try
                    {
                        throw new ValidateErrorException(FLAG, fieldName, errDetails, example);
                    }
                    catch (ValidateErrorException exc)
                    {
                        System.out.println(exc);
                    }
                } else return null;
            }
        }

    /**
     * this method will use to get a timeslot from the user( timeslot id need to be entered by the user )
     *
     * callIdentifier signature
     *
     * 1 - just printing available timeslots for viewing purpose only. not getting any inputs related to timeslots.
     * 2 - getting timeslot id from user.
     *
     * @param instructions instructions to user about the format of the timeslot to enter(user should enter just timeslot id e.g. 1,2,3 ..., 10)
     * @param fieldName field name of the field to show in the error
     * @param errDetails error details to show in the error.
     * @param example  sample answer to help user.
     * @param medLicenceNo medical licence number of the doctor associated with the timeslot
     * @param timeSlotDate the date of the timeslot
     * @return Timeslot object will be returned
     */
        public TimeSlot inputGetTimeSlot(String instructions, String fieldName, String errDetails, String example,int medLicenceNo,  LocalDate timeSlotDate)
        {
            final int FLAG = 2;
            TimeSlot timeSlot = new TimeSlot();
            timeSlot.printAvailableTimeSlots(medLicenceNo, timeSlotDate, 2);
            while(true)
            {
                String inTimeSlot = getData(instructions);
                if (!inTimeSlot.equalsIgnoreCase("Q"))
                {
                    if( val.validateTimeSlotInput(inTimeSlot) )
                    {
                        TimeSlot slot = new TimeSlot(Integer.parseInt(inTimeSlot));

                            return slot;
                    }
                    else
                    {
                        try
                        {
                            throw new ValidateErrorException(FLAG, fieldName, errDetails, example);
                        }
                        catch (ValidateErrorException exc)
                        {
                            System.out.println(exc);
                        }
                    }
                }
                else return null;
            }
        }

    /**
     * this method will get consultation id from the user.
     *
     * @param instructions instructions to prompt user.
     * @param fieldName fieldname of the field to show in the error.
     * @param errDetails error details to show in the error.
     * @param example sample answer to help user.
     * @return consultation id of the consultation.
     */
        public Integer inputGetConsultationId(String instructions, String fieldName, String errDetails, String example)
        {
            final int FLAG = 16;
            while (true)
            {
                String consultId = getData(instructions);
                if (!consultId.equalsIgnoreCase("Q"))
                {
                    if(val.validateConsultationId( consultId ))
                    {
                        return Integer.parseInt(consultId);
                    }
                    else
                    {
                        try
                        {
                            throw new ValidateErrorException(FLAG, fieldName, errDetails, example);
                        }
                        catch (ValidateErrorException exc)
                        {
                            System.out.println(exc);
                        }
                    }
                } else return null ;
            }
        }
}
