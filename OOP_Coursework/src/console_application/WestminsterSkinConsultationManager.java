package console_application;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

/**
 COPYRIGHT (C) Sandeepa Induwara Samaranayake - 20210302 - W1867067- sandeepa.20210302@iit.ac.lk. All Rights Reserved.
 Object-Oriented Programming Coursework L5 sem 1
 @author Sandeepa Induwara Samaranayake
 @version 1 Console application
 */

/**
 * The class containing main method. This class will handle every action in the program.
 */
public class WestminsterSkinConsultationManager implements SkinConsultationManager
{
    private static ArrayList<Doctor> docArrList = new ArrayList<Doctor>();                        // create a Doctor type arraylist.
    private static ArrayList<Consultation> consultArrList = new ArrayList<Consultation>();              // create a Consultation type arraylist.
    private static ArrayList<Patient> patientArrayList = new ArrayList<Patient>();                 // create a Patient type arraylist.
    private static DatabaseConnector databaseConnector;                                      // create DatabaseConnector object.
    InputReceiver input = new InputReceiver();

    /**
     * main method of the program.
     * @param args any argument pass to the main method.
     */
    public static void main(String[] args) throws DatabaseConnectionErrorException
    {
        Scanner sc = new Scanner(System.in);
        databaseConnector = new DatabaseConnector(true);
        WestminsterSkinConsultationManager wscm = new WestminsterSkinConsultationManager();
        wscm.loadData(databaseConnector.getConnection());
        wscm.showMenu();
        while (true)
        {
            System.out.print("\nENTER YOUR OPTION :");
            String userInput = sc.next().toUpperCase();
            wscm.callActions(userInput);
        }
    }

    /**
     * showMenu() method will show the menu.
     */
    public void showMenu() {
        // use a text block. no need of string concatenation.
        System.out.println("""

                ______________WESTMINSTER SKIN CONSULTATION MANAGER_________________

                M: SHOW MENU
                A: ADD A NEW DOCTOR
                V: VIEW ALL DOCTORS
                D: DELETE A DOCTOR
                C: ADD CONSULTATIONS
                N: VIEW CONSULTATIONS
                F: CANCEL CONSULTATIONS
                T: CHECK AVAILABLE TIMESLOTS
                B: VIEW ALL PATIENTS
                S: STORE PROGRAM DATA INTO A FILE
                G: GRAPHICAL USER INTERFACE
                X: EXIT
                ____________________________________________________________________
                """);
    }

    /**
     * This method will add doctor to the system.
     */
    public void addNewDoctor() throws ValidateErrorException
    {
        if(docArrList.size() < 10)
        {
            InputReceiver input = new InputReceiver();

            Integer medLicenceNo = input.inputGetMedLicenceNoOrPatientID("ENTER MEDICAL LICENCE NUMBER (USE 6 DIGIT NUMBER FORMAT (E.g. 123456). ENTER Q TO GO BACK.):",
                    "MEDICAL LICENCE NUMBER", "THE MEDICAL LICENCE NUMBER FORMAT IS CORRECT", "123456", 1,1); if(medLicenceNo==null) return;

            String firstName = input.inputGetName("ENTER DOCTOR'S FIRST NAME (E.g.- John. ENTER Q TO GO BACK.):", "FIRST NAME",
                     "ONLY THE LETTERS ARE INCLUDED IN THE FIRST NAME", "John");if(firstName==null) return;

            String surName = input.inputGetName("ENTER DOCTOR'S SURNAME (E.g.- Steve. ENTER Q TO GO BACK.):", "SURNAME",
                     "ONLY THE LETTERS ARE INCLUDED IN THE SURNAME", "Steve"); if(surName==null) return;

            LocalDate dob = input.inputGetDate("ENTER DATE OF BIRTH (USE YYYY-MM-DD FORMAT. ENTER Q TO GO BACK.):", "BIRTH DAY",
                     "THE CORRECT BIRTHDAY FORMAT (YYYY-MM-DD) IS USED", "2001-08-03", true); if(dob==null) return;

            String mobileNumber = input.inputGetMobileNumber("ENTER MOBILE NUMBER (USE +94773568837 FORMAT. ENTER Q TO GO BACK.):", "MOBILE NUMBER",
                     "THE CORRECT MOBILE NUMBER FORMAT IS USED", "+94773568837"); if(mobileNumber==null) return;

            String address = input.inputGetAddress("ENTER ADDRESS (USE '76 PARK ROAD, LONDON' FORMAT. ENTER Q TO GO BACK.):", "ADDRESS",
                     "THE ADDRESS FORMAT IS CORRECT", "76 PARK ROAD, LONDON"); if(address==null) return;

            String specialisation = input.inputGetSpecialisationOrCategory("ENTER SPECIALISATION OF THE DOCTOR (E.g. Cosmetic Dermatology. ENTER Q TO GO BACK.):",
                    "SPECIALISATION", "TO USE ONLY LETTERS. YOU CAN USE SPACES TO SEPARATE WORDS", "Cosmetic Dermatology"); if(specialisation==null) return;

            String category = input.inputGetSpecialisationOrCategory("ENTER CATEGORY OF THE DOCTOR (E.g. Surgeon. ENTER Q TO GO BACK.):",
                    "CATEGORY", "TO USE ONLY LETTERS. YOU CAN USE SPACES TO SEPARATE WORDS", "Surgeon"); if(category==null) return;

            getDocArrList().add( new Doctor( medLicenceNo, firstName, surName, dob, mobileNumber, address, specialisation,category  ));
            System.out.printf("\nDOCTOR %s %s WITH LICENCE NUMBER %d SUCCESSFULLY ADDED TO THE SYSTEM.%n", firstName, surName, medLicenceNo);
        }
        else
        {
            System.out.println("THE CONSULTATION CENTRE CAN HANDLE MAXIMUM OF TEN DOCTORS ONLY. TRY AGAIN AFTER DELETING A DOCTOR.");
        }
    }

    /**
     * This method will print all doctor's details in alphabetical order of their surname.
     */
    public void viewDoctors()
    {
        final int FLAG = 9;
        docArrList.sort(Comparator.comparing(Doctor::getSurname));
        if(docArrList.size() > 0)
        {
            for (Doctor doc : docArrList)
            {
                if( docArrList.indexOf(doc) == 0)
                {System.out.print("---------------------------------------------------------------------");}
                System.out.println(
                        "\nMEDICAL LICENCE NUMBER : " + doc.getMedicalLicenceNumber() +
                        "\nFIRST NAME             : " + doc.getName() +
                        "\nLAST NAME              : " + doc.getSurname() +
                        "\nBIRTHDAY               : " + doc.getDateOfBirth() +
                        "\nMOBILE NUMBER          : " + doc.getMobileNumber() +
                        "\nADDRESS                : " + doc.getAddress() +
                        "\nSPECIALISATION         : " + doc.getSpecialisation() +
                        "\nCATEGORY               : " + doc.getCategory() );
                System.out.println("---------------------------------------------------------------------");
            }
        }
        else
        {
            try
            {
                throw new InvalidInputErrorException(FLAG,"PLEASE ENTER DATA BEFORE USE THE VIEW OPTION. YOU CAN ADD DOCTORS BY ENTERING 'A' MENU KEY.");
            }
            catch (InvalidInputErrorException exc)
            {
                System.out.println(exc);
            }
        }
    }

    /**
     * This method will delete a doctor by using medical licence number.
     */
    public void deleteDoctor()
    {
        final int FLAG = 14;
        if(!docArrList.isEmpty())
        {
            Integer medLicenceNo = input.inputGetMedLicenceNoOrPatientID("ENTER MEDICAL LICENCE NUMBER OF THE DOCTOR YOU WANT TO DELETE.(USE 6 DIGIT NUMBER FORMAT (E.g. 123456). ENTER Q TO GO BACK.):",
                   "MEDICAL LICENCE NUMBER", "THE MEDICAL LICENCE NUMBER FORMAT IS CORRECT", "123456", 1, 2); if(medLicenceNo==null) return;
            for (Doctor doc : docArrList)
            {
                if (doc.getMedicalLicenceNumber() == medLicenceNo)
                {
                    Doctor doctor = doc;
                    try
                    {
                        // if user try to first delete a doctor from the system and add a doctor with the same medical id, it will not going to be saved to database
                        // because that id is already existing in the database. So when user delete a doctor record from system we have to delete it from both
                        // arraylist and database.

                        // deleting doctor from arraylist
                        docArrList.remove(doc);

                        // deleting doctor from database
                        Statement statement = databaseConnector.getConnection().createStatement();
                        statement.executeUpdate("DELETE FROM Doctors WHERE Medical_licence_number = " + medLicenceNo);

                        // print remaining number of doctors and successfully deleted message.
                        int remainingTotalDoctors = docArrList.size();
                        System.out.printf("THE DOCTOR %s %s SPECIALIST IN %s WITH LICENCE ID : %d HAS BEEN REMOVED FROM SYSTEM.\nTOTAL NUMBER OF REMAINING DOCTORS : %d",
                                doctor.getName(), doctor.getSurname(), doctor.getSpecialisation(), doctor.getMedicalLicenceNumber(), remainingTotalDoctors);
                    }
                    catch (Exception exc)
                    {
                        System.out.printf("ERROR: AN ERROR OCCURRED WHILE DELETING THE DOCTOR. ERROR FLAG = %d", FLAG);
                    }
                    break;
                }
            }
        }
        else
        {
            try
            {
                throw new InvalidInputErrorException(FLAG,"PLEASE ENTER DATA BEFORE USE THE DELETE OPTION. YOU CAN ADD DOCTORS BY ENTERING 'A' MENU KEY.");
            }
            catch (InvalidInputErrorException exc)
            {
                System.out.println(exc);
            }
        }
    }

    /**
     * This method will add consultations to system.
     */
    public void addConsultations()
    {
        Consultation consult = new Consultation();

        Integer consultDoctorMedLicence = input.inputGetMedLicenceNoOrPatientID("ENTER MEDICAL LICENCE NUMBER OF THE DOCTOR (USE 6 DIGIT NUMBER FORMAT (E.g. 123456). ENTER Q TO GO BACK.):",
                "MEDICAL LICENCE NUMBER", "THE MEDICAL LICENCE NUMBER FORMAT IS CORRECT", "123456", 1,2); if(consultDoctorMedLicence==null) return;

        Integer patientId = input.inputGetMedLicenceNoOrPatientID("ENTER PATIENT ID (USE 6 DIGIT NUMBER FORMAT (E.g. 123456). ENTER Q TO GO BACK.):",
                "PATIENT ID", "THE PATIENT ID FORMAT IS CORRECT", "123456", 2, 3); if(patientId==null) return;

        String patientFirstName = input.inputGetName("ENTER PATIENT'S FIRST NAME (E.g.- John. ENTER Q TO GO BACK.):", "FIRST NAME",
                "ONLY THE LETTERS ARE INCLUDED IN THE FIRST NAME", "John");if(patientFirstName==null) return;

        String patientSurName = input.inputGetName("ENTER PATIENT'S SURNAME (E.g.- Steve. ENTER Q TO GO BACK.):", "SURNAME",
                "ONLY THE LETTERS ARE INCLUDED IN THE SURNAME", "Steve"); if(patientSurName==null) return;

        LocalDate patientDob = input.inputGetDate("ENTER PATIENT'S DATE OF BIRTH (USE YYYY-MM-DD FORMAT. ENTER Q TO GO BACK.):", "BIRTH DAY",
                "THE CORRECT BIRTHDAY FORMAT (YYYY-MM-DD) IS USED", "2001-08-03", true); if(patientDob==null) return;

        String patientMobileNumber = input.inputGetMobileNumber("ENTER PATIENT'S MOBILE NUMBER (USE +94773568837 FORMAT. ENTER Q TO GO BACK.):", "MOBILE NUMBER",
                "THE CORRECT MOBILE NUMBER FORMAT IS USED", "+94773568837"); if(patientMobileNumber==null) return;

        String patientAddress = input.inputGetAddress("ENTER PATIENT'S ADDRESS (USE '76 PARK ROAD, LONDON' FORMAT. ENTER Q TO GO BACK.):", "ADDRESS",
                "THE ADDRESS FORMAT IS CORRECT", "76 PARK ROAD, LONDON"); if(patientAddress==null) return;

        String patientRequirement = input.inputGetPatientRequirementOrNotes("ENTER PATIENT'S REQUIREMENT FOR CONSULTATION (E.g. ENTER Q TO GO BACK.):", "REQUIREMENT",
                "TO USE ONLY LETTERS AND NUMBERS. YOU CAN USE SPACES TO SEPARATE WORDS", "Rash"); if(patientRequirement == null) return;

        String patientNotes = input.inputGetPatientRequirementOrNotes("ENTER PATIENT'S NOTES FOR CONSULTATION (E.g. ENTER Q TO GO BACK.):", "NOTES",
                "TO USE ONLY LETTERS AND NUMBERS. YOU CAN USE SPACES TO SEPARATE WORDS", "This is a note about patient"); if(patientRequirement == null) return;

        LocalDate consultationDate = input.inputGetDate("ENTER CONSULTATION DATE (USE YYYY-MM-DD FORMAT. ENTER Q TO GO BACK.):", "CONSULTATION DATE",
                "THE CORRECT CONSULTATION DATE FORMAT (YYYY-MM-DD) IS USED", "2023-01-14", false); if(consultationDate==null) return;

        TimeSlot timeSlot = input.inputGetTimeSlot("ENTER TIMESLOT (ENTER Q TO GO BACK.):", "TIME SLOT", "USE CORRECT TIMESLOT ID",
                "1", consultDoctorMedLicence, consultationDate);


        // add the patient to the system if that patient is not already exist in the system.
        boolean isExists = false;
        for(Patient patient : getPatientArrayList())
        {
            if (patient.getPatientId() == patientId)
            {
                isExists = true;
                break;
            }
        }
        if(!isExists){patientArrayList.add( new Patient( patientId, patientFirstName, patientSurName, patientDob, patientMobileNumber, patientAddress) );}

        // check whether timeslot is duplicating(if duplicating, change the doctor randomly, if not duplicating keep the same doctor)
        consultDoctorMedLicence = consult.assignAnotherDoctorIfTimeslotReserved(timeSlot, consultationDate, consultDoctorMedLicence,1);

        if(consultDoctorMedLicence != 0)
        {
            // add consultation to the system.
            consultArrList.add( new Consultation( consultDoctorMedLicence, patientId, patientRequirement, consultationDate,patientNotes, timeSlot));

            System.out.printf("THE CONSULTATION FOR %s HAS BEEN PLACED SUCCESSFULLY ON %s %s WITH Dr. %S" ,patientSurName, consultationDate, timeSlot.getStringTimeslot(),
                    consult.getConsultationDoctor(consultDoctorMedLicence).getSurname());
        }
    }

    /**
     * This method will cancel consultations.
     */
    public void cancelConsultations()
    {
        final int FLAG = 17;
        if(!consultArrList.isEmpty())
        {
            Integer cancelConsultationId = input.inputGetConsultationId("ENTER CONSULTATION ID (USE 8 DIGIT NUMBER FORMAT (E.g. 20230000). ENTER Q TO GO BACK.):",
                    "CONSULTATION ID", "THE CONSULTATION ID FORMAT IS CORRECT", "20230000");
            if (cancelConsultationId == null) return;
            Validation val = new Validation();                 // using validation class to check whether the cancelling consultation is not in the past.
            for (Consultation consult :consultArrList)
            {
                // check the cancelling consultation is not in the past.
                if (consult.getConsultationId() == cancelConsultationId && !val.validateDate(consult.getConsultationDate().toString(), true) )
                {
                    Consultation memoConsult = consult;
                    try
                    {
                        // if user try to first delete a consultation from the system and add a consultation  with the same consultation id, it will not going to be saved to database
                        // because that id is already existing in the database. So when user delete a consultation record from system we have to delete it from both
                        // arraylist and database.

                        // deleting consultation from arraylist
                        consultArrList.remove(consult);

                        // deleting consultation from database
                        Statement statement = databaseConnector.getConnection().createStatement();
                        statement.executeUpdate("DELETE FROM Consultation WHERE Consultation_Id = " + cancelConsultationId);

                        // print remaining number of consultations and successfully deleted message.
                        int remainingTotalConsultations = consultArrList.size();
                        System.out.printf("THE CONSULTATION ASSOCIATED WITH CONSULTATION ID %d LINKED TO PATIENT %d : %s WITH THE DOCTOR %d : %s  HAS BEEN REMOVED FROM THE SYSTEM.\nTHE REMAINING NUMBER OF CONSULTATIONS : %d",
                                memoConsult.getConsultationId(), memoConsult.getConsultPatientId(), memoConsult.getConsultationPatient(memoConsult.getConsultPatientId()).getSurname(), memoConsult.getConsultDoctorMedNo(),
                                memoConsult.getConsultationDoctor(memoConsult.getConsultDoctorMedNo()).getSurname(), remainingTotalConsultations);
                    }
                    catch (Exception exc)
                    {
                        System.out.printf("ERROR: AN ERROR OCCURRED WHILE DELETING THE CONSULTATION. ERROR FLAG = %d", FLAG);
                    }
                    break;
                }
            }
        }
        else
        {
            try
            {
                throw new InvalidInputErrorException(FLAG,"PLEASE ENTER DATA BEFORE USE THE CANCEL OPTION. YOU CAN ADD CONSULTATIONS BY ENTERING 'C' MENU KEY.");
            }
            catch (InvalidInputErrorException exc)
            {
                System.out.println(exc);
            }
        }
    }

    /**
     * this method will show all the consultations along with their details.
     */
    public void viewConsultations()
    {
        final int FLAG = 8;
        if(!consultArrList.isEmpty())
        {
            for (Consultation consult : consultArrList)
            {
                if( consultArrList.indexOf(consult) == 0)
                {System.out.print("---------------------------------------------------------------------");}
                System.out.println(
                                "\nCONSULTATION ID                  : " + consult.getConsultationId() +
                                "\nPATIENT ID                       : " + consult.getConsultPatientId() +
                                "\nPATIENT NAME                     : " + consult.getConsultationPatient(consult.getConsultPatientId()).getSurname() +
                                "\nDOCTOR MEDICAL LICENCE NO        : " + consult.getConsultDoctorMedNo() +
                                "\nDOCTOR NAME                      : " + consult.getConsultationDoctor(consult.getConsultDoctorMedNo()).getSurname()+
                                "\nCONSULTATION DATE                : " + consult.getConsultationDate() +
                                "\nCONSULTATION PLACED DATE & TIME  : " + consult.getStringConsultationPlacedDate() + " " + consult.getStringConsultationPlacedTime() +
                                "\nTIMESLOT                         : " + consult.getTimeSlot().getStringTimeslot() +
                                "\nPATIENT REQUIREMENT              : " + consult.getPatientRequirement() +
                                "\nPATIENT NOTES                    : " + consult.getNotes() +
                                "\nCOST                             : " + consult.getCost());

                System.out.println("---------------------------------------------------------------------");
            }
        }
        else
        {
            try
            {
                throw new InvalidInputErrorException(FLAG,"PLEASE ENTER DATA BEFORE USE THE VIEW OPTION. YOU CAN ADD CONSULTATIONS BY ENTERING 'C' MENU KEY ");
            }
            catch (InvalidInputErrorException exc)
            {
                System.out.println(exc);
            }
        }
    }

    /**
     * this method will print the available timeslots by getting medical licence no and date of the consultation
     *
     * callIdentifier signature
     *
     * 1 - just printing available timeslots for viewing purpose only. not getting any inputs related to timeslots.
     * 2 - getting timeslot id from user.
     */
    public void checkAvailableTimeslots()
    {
        final int FLAG = 20;
        Integer consultDoctorMedLicence = input.inputGetMedLicenceNoOrPatientID("ENTER MEDICAL LICENCE NUMBER OF THE DOCTOR YOU WANT TO CHECK FOR AVAILABILITY (USE 6 DIGIT NUMBER FORMAT (E.g. 123456). ENTER Q TO GO BACK.):",
                "MEDICAL LICENCE NUMBER", "THE MEDICAL LICENCE NUMBER FORMAT IS CORRECT", "123456", 1,2); if(consultDoctorMedLicence==null) return;

        LocalDate consultationDate = input.inputGetDate("ENTER CONSULTATION DATE YOU WANT TO CHECK FOR AVAILABILITY (USE YYYY-MM-DD FORMAT. ENTER Q TO GO BACK.):", "CONSULTATION DATE",
                "THE CORRECT CONSULTATION DATE FORMAT (YYYY-MM-DD) IS USED", "2023-01-14", false); if(consultationDate==null) return;

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.printAvailableTimeSlots(consultDoctorMedLicence, consultationDate, 1);
    }

    /**
     * This method will show all patient details in alphabetical order of patient's surname.
     */
    public void viewPatients()
    {
        final int FLAG = 19;
        patientArrayList.sort(Comparator.comparing(Patient::getSurname));
        if(patientArrayList.size() > 0)
        {
            for (Patient patient : patientArrayList)
            {
                if( patientArrayList.indexOf(patient) == 0)
                {System.out.print("---------------------------------------------------------------------");}
                System.out.println(
                                "\nPATIENT ID             : " + patient.getPatientId() +
                                "\nFIRST NAME             : " + patient.getName() +
                                "\nLAST NAME              : " + patient.getSurname() +
                                "\nBIRTHDAY               : " + patient.getDateOfBirth() +
                                "\nMOBILE NUMBER          : " + patient.getMobileNumber() +
                                "\nADDRESS                : " + patient.getAddress());
                System.out.println("---------------------------------------------------------------------");
            }
        }
        else
        {
            try
            {
                throw new InvalidInputErrorException(FLAG,"PLEASE ENTER DATA BEFORE USE THE VIEW OPTION. YOU CAN ADD PATIENTS TO THE SYSTEM BY ADDING CONSULTATIONS USING 'C' MENU KEY.");
            }
            catch (InvalidInputErrorException exc)
            {
                System.out.println(exc);
            }

        }
    }

    /**
     * This method will push changes to database.
     */
    public void savetoFile(Connection conn)
    {
        final int FLAG = 15;
            try
            {
                if( !docArrList.isEmpty() )
                {
                    // add data to Doctors table.
                    int rowsInsertedToDoctorTable = 0;
                    String sql = "INSERT INTO Doctors (Medical_licence_number, First_name, Surname, Birth_day, Mobile_number, Address, Specialisation, Category) VALUES (?,?,?,?,?,?,?,?)";
                    PreparedStatement statement = conn.prepareStatement(sql);

                    for (Doctor doc : docArrList)
                    {
                        Statement statementValidation = conn.createStatement();
                        ResultSet resultSetValidation = statementValidation.executeQuery("SELECT * FROM Doctors WHERE Medical_licence_number = " + doc.getMedicalLicenceNumber());
                        if(!resultSetValidation.next())
                        {
                            statement.setInt(1, doc.getMedicalLicenceNumber());
                            statement.setString(2, doc.getName());
                            statement.setString(3, doc.getSurname());
                            statement.setString(4, doc.getDateOfBirth().toString());
                            statement.setString(5, doc.getMobileNumber());
                            statement.setString(6, doc.getAddress());
                            statement.setString(7, doc.getSpecialisation());
                            statement.setString(8, doc.getCategory());
                            statement.executeUpdate();
                            rowsInsertedToDoctorTable++;
                        }
                    }
                    System.out.println(rowsInsertedToDoctorTable + " DOCTOR RECORD/S SUCCESSFULLY SAVED TO DATABASE.");
                }

                if( !patientArrayList.isEmpty() )
                {
                    // add data to patient table.
                    int rowsInsertedToPatientTable = 0;
                    String sql = "INSERT INTO Patient (Patient_id, First_name, Surname, Birth_day, Mobile_number, Address) VALUES (?,?,?,?,?,?)";
                    PreparedStatement statement = conn.prepareStatement(sql);

                    for (Patient patient : patientArrayList)
                    {
                        Statement statementValidation = conn.createStatement();
                        ResultSet resultSetValidation = statementValidation.executeQuery("SELECT * FROM Patient WHERE Patient_id = " + patient.getPatientId());

                        if(!resultSetValidation.next())
                        {
                            statement.setInt(1, patient.getPatientId());
                            statement.setString(2, patient.getName());
                            statement.setString(3, patient.getSurname());
                            statement.setString(4, patient.getDateOfBirth().toString());
                            statement.setString(5, patient.getMobileNumber());
                            statement.setString(6, patient.getAddress());
                            statement.executeUpdate();
                            rowsInsertedToPatientTable++;
                        }
                    }
                    System.out.println(rowsInsertedToPatientTable + " PATIENT RECORD/S SUCCESSFULLY SAVED TO DATABASE.");
                }

                if( !consultArrList.isEmpty() )
                {
                    // add data to consultation table.
                    int rowsInsertedToConsultationTable = 0;
                    String sql = "INSERT INTO Consultation (Consultation_Id, Consult_patient_id, Consult_doc_med_no, Patient_requirement, Consultation_placed_date, Consultation_placed_time, Consultation_date, Consultation_timeslot_id, Cost, Notes) VALUES (?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement statement = conn.prepareStatement(sql);

                    for (Consultation consult : consultArrList)
                    {
                        Statement statementValidation = conn.createStatement();
                        ResultSet resultSetValidation = statementValidation.executeQuery("SELECT * FROM Consultation WHERE Consultation_Id = " + consult.getConsultationId());

                        if(!resultSetValidation.next())
                        {
                            statement.setInt(1, consult.getConsultationId());
                            statement.setInt(2, consult.getConsultPatientId());
                            statement.setInt(3, consult.getConsultDoctorMedNo());
                            statement.setString(4, consult.getPatientRequirement());
                            statement.setDate(5, consult.getDateConsultationPlacedDate());
                            statement.setTime(6, consult.getTimeConsultationPlacedTime());
                            statement.setString(7, consult.getConsultationDate().toString());
                            statement.setInt(8, consult.getTimeSlot().getTimeslotId());
                            statement.setDouble(9, consult.getCost());
                            statement.setString(10, consult.getNotes());
                            statement.executeUpdate();
                            rowsInsertedToConsultationTable++;
                        }
                    }
                    System.out.println(rowsInsertedToConsultationTable + " CONSULTATION RECORD/S SUCCESSFULLY SAVED TO DATABASE");
                }
                System.out.println("ALL DATA SAVED SUCCESSFULLY");
            }
            catch (SQLException exc)
            {
                try
                {
                    throw new DatabaseConnectionErrorException(FLAG, exc);
                }
                catch (DatabaseConnectionErrorException err)
                {
                    System.out.println(err);
                }
            }
    }

    /**
     * this method will load data from the database.
     * @param conn Database connection
     */
    public void loadData(Connection conn)
    {
        Statement stmt = null;
        ResultSet rs = null;

        final int FLAG = 18;
        try
        {
            stmt = conn.createStatement();
            String query = "SELECT * FROM Doctors";
            rs = stmt.executeQuery(query);

            // retrieve doctor's data from database and push them to docArrList.
            while(rs.next())
            {
                int medLicenceNo       = rs.getInt("Medical_licence_number");
                String firstName       = rs.getString("First_name");
                String surname         = rs.getString("Surname");
                Date birthDay          = rs.getDate("Birth_day");
                String mobileNumber    = rs.getString("Mobile_number");
                String address         = rs.getString("Address");
                String specialisation  = rs.getString("Specialisation");
                String category        = rs.getString("Category");

                // add retrieved data to docArrayList
                docArrList.add(new Doctor(medLicenceNo, firstName, surname, birthDay.toLocalDate(), mobileNumber, address, specialisation, category));
            }

            query = "SELECT * FROM Patient";
            rs = stmt.executeQuery(query);

            // retrieve patient's data from database and push them to patientArrayList.
            while(rs.next())
            {
                int patientId         = rs.getInt("Patient_id");
                String firstName      = rs.getString("First_name");
                String surname        = rs.getString("Surname");
                Date birthDay         = rs.getDate("Birth_day");
                String mobileNumber   = rs.getString("Mobile_number");
                String address        = rs.getString("Address");

                // add retrieved data to patientArrayList
                patientArrayList.add(new Patient(patientId, firstName, surname, birthDay.toLocalDate(), mobileNumber, address));
            }

            query = "SELECT * FROM Consultation";
            rs = stmt.executeQuery(query);

            // retrieve consultation's data from database and push them to consultArrayList.
            while(rs.next())
            {
                int consultationId            = rs.getInt("Consultation_Id");
                int consultationPatientId     = rs.getInt("Consult_patient_id");
                int consultationMedLicenceNo  = rs.getInt("Consult_doc_med_no");
                String patientRequirement     = rs.getString("Patient_requirement");
                Date consultationPlacedDate   = rs.getDate("Consultation_placed_date");
                Time consultationPlacedTime   = rs.getTime("Consultation_placed_time");
                Date consultationDate         = rs.getDate("Consultation_date");
                int consultationTimeslotId    = rs.getInt("Consultation_timeslot_id");
                double cost                   = rs.getDouble("Cost");
                String notes                  = rs.getString("Notes");

                // add retrieved data to consultArrayList
                Consultation consult = new Consultation(consultationMedLicenceNo, consultationPatientId, patientRequirement,
                                           consultationDate.toLocalDate(), notes, new TimeSlot( consultationTimeslotId ) ) ;
                consult.setConsultationId(consultationId);
                consult.setConsultationPlacedDate(consultationPlacedDate.toLocalDate());
                consult.setConsultationPlacedTime(consultationPlacedTime.toLocalTime());
                consult.setCost(cost);

                consultArrList.add(consult);
            }
            System.out.println("ALL DATA LOADED BACK TO THE SYSTEM.");
        }
        catch (SQLException exc)
        {
            System.out.println("Error occurred when loading data");
        }
    }

    /**
     * This method will trigger to open User Interface.
     */
    public void showGUI()
    {
        GUIHome home = new GUIHome();
        home.setVisible(true);
        System.out.println("GRAPHICAL USER INTERFACE IS ACTIVATED");
    }

    /**
     * This method will terminate the entire program.
     */
    public void exit()
    {
        try
        {
            System.out.println("DISCONNECTING DATABASE FROM THE SYSTEM");
            databaseConnector.conn.close();
        }
        catch (SQLException exc)
        {
            System.out.println("AN ERROR OCCURRED WHILE CLOSING THE DATABASE CONNECTION");
        }
        finally
        {
            System.out.println("PLEASE WAIT, APPLICATION IS SHUTTING DOWN...");
            System.exit(0);
        }

    }

    /**
     * this method will call the relevant method according to the given user input.
     * @param userInput input of the user.
     */
    public void callActions(String userInput)
    {
        final int FLAG = 10;
        switch (userInput)
        {
            case "M" -> showMenu();
            case "A" ->
            {
                try {addNewDoctor();}
                catch (ValidateErrorException e) {throw new RuntimeException(e);}
            }
            case "V" -> viewDoctors();
            case "B" -> viewPatients();
            case "D" -> deleteDoctor();
            case "C" -> addConsultations();
            case "N" -> viewConsultations();
            case "F" -> cancelConsultations();
            case "T" -> checkAvailableTimeslots();
            case "S" -> savetoFile(databaseConnector.getConnection());
            case "G" -> showGUI();
            case "X" -> exit();
            default ->
            {
                try
                {
                    throw new InvalidInputErrorException(FLAG, "SEEMS LIKE, YOU ARE ENTERING A WRONG MENU KEY.");
                }
                catch (InvalidInputErrorException exc)
                {
                    System.out.println(exc);
                }
            }
        }
    }

    /**
     * getter for Doctor arraylist.
     * @return doctor arraylist.
     */
    public static ArrayList<Doctor> getDocArrList()
    {
        return docArrList;
    }

    /**
     * getter for Consultation arraylist.
     * @return consultation arraylist.
     */
    public static ArrayList<Consultation> getConsultArrList()
    {
        return consultArrList;
    }

    /**
     * getter for Patient arraylist.
     * @return patient arraylist.
     */
    public static ArrayList<Patient> getPatientArrayList()
    {
        return patientArrayList;
    }
}
