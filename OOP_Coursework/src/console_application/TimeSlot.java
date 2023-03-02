package console_application;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 COPYRIGHT (C) Sandeepa Induwara Samaranayake - 20210302 - W1867067- sandeepa.20210302@iit.ac.lk. All Rights Reserved.
 Object-Oriented Programming Coursework L5 sem 1
 @author Sandeepa Induwara Samaranayake
 @version 1 Console application
 */

/**
 * this class will hold the structure of time slots.
Time slot id

ID                        TimeSlot

1                         9am  - 10am
2                         10am - 11am
3                         11am - 12pm
4                         1pm  - 2pm
5                         2pm  - 3pm
6                         3pm  - 4pm
7                         5pm  - 6pm
8                         6pm  - 7pm
9                         7pm  - 8pm
10                        9pm - 10pm

 */
public class TimeSlot
{

    private int timeslotId;
    private LocalTime startTime;
    private LocalTime endTime;
    private Duration duration;

    /**
     * this is the default constructor for TimeSlot class.
     */
    public TimeSlot()
    {

    }

    /**
     * this is the constructor for TimeSlot class which accepts one parameter timeslotId
     * @param timeslotId id of the timeslot e.g. 1,2,3 ... 10
     */
    public TimeSlot(int timeslotId)
    {
        this.timeslotId = timeslotId;
        switch (timeslotId)
        {
            case 1 -> {
                this.startTime = LocalTime.of(9, 0);
                this.endTime = LocalTime.of(10,0);
            }
            case 2 -> {
                this.startTime = LocalTime.of(10, 0);
                this.endTime = LocalTime.of(11,0);
            }
            case 3 -> {
            this.startTime = LocalTime.of(11, 0);
            this.endTime = LocalTime.of(12,0);
            }
            case 4 -> {
                this.startTime = LocalTime.of(13, 0);
                this.endTime = LocalTime.of(14,0);
            }
            case 5 -> {
                this.startTime = LocalTime.of(14, 0);
                this.endTime = LocalTime.of(15,0);
            }
            case 6 -> {
                this.startTime = LocalTime.of(15, 0);
                this.endTime = LocalTime.of(16,0);
            }
            case 7 -> {
                this.startTime = LocalTime.of(17, 0);
                this.endTime = LocalTime.of(18,0);
            }
            case 8 -> {
                this.startTime = LocalTime.of(18, 0);
                this.endTime = LocalTime.of(19,0);
            }
            case 9 -> {
                this.startTime = LocalTime.of(19, 0);
                this.endTime = LocalTime.of(20,0);
            }
            case 10 -> {
                this.startTime = LocalTime.of(21, 0);
                this.endTime = LocalTime.of(22,0);
            }
        }
    }

    /**
     * this is a constructor for TimeSlot class which accepts two parameters startTime and endTime.
     * @param startTime start time of the timeslot
     * @param endTime end time of the timeslot
     */
    public TimeSlot(LocalTime startTime, LocalTime endTime)
    {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * this is a constructor for TimeSlot class which accepts two parameters startTime and duration.
     * @param startTime start time of the timeslot
     * @param duration end time of the timeslot
     */
    public TimeSlot(LocalTime startTime, Duration duration)
    {
        this.startTime = startTime;
        this.duration = duration;
    }

    /**
     * this method will return a string representation of the timeslot
     * @return string representation of timeslot
     */
    public String getStringTimeslot()
    {
        switch (this.timeslotId)
        {
            case 1 -> {return "09.00 A.M. - 10.00 A.M.";}
            case 2 -> {return "10.00 A.M. - 11.00 A.M.";}
            case 3 -> {return "11.00 A.M. - 12.00 P.M.";}
            case 4 -> {return "01.00 P.M. - 02.00 P.M.";}
            case 5 -> {return "02.00 P.M. - 03.00 P.M.";}
            case 6 -> {return "03.00 P.M. - 04.00 P.M.";}
            case 7 -> {return "05.00 P.M. - 06.00 P.M.";}
            case 8 -> {return "06.00 P.M. - 07.00 P.M.";}
            case 9 -> {return "07.00 P.M. - 08.00 P.M.";}
            case 10 -> {return "09.00 P.M. - 10.00 P.M.";}
            default -> {return "ERROR: SYSTEM ERROR OCCURRED WHILE GETTING THE TIMESLOT. TRY AGAIN LATER.";}
        }
    }

    /**
     * this method will return a string representation of the timeslot
     * @return string representation of timeslot
     */
    public String getStringTimeslotForGivenId(int timeslotId)
    {
        switch (timeslotId)
        {
            case 1 -> {return "09.00 A.M. - 10.00 A.M.";}
            case 2 -> {return "10.00 A.M. - 11.00 A.M.";}
            case 3 -> {return "11.00 A.M. - 12.00 P.M.";}
            case 4 -> {return "01.00 P.M. - 02.00 P.M.";}
            case 5 -> {return "02.00 P.M. - 03.00 P.M.";}
            case 6 -> {return "03.00 P.M. - 04.00 P.M.";}
            case 7 -> {return "05.00 P.M. - 06.00 P.M.";}
            case 8 -> {return "06.00 P.M. - 07.00 P.M.";}
            case 9 -> {return "07.00 P.M. - 08.00 P.M.";}
            case 10 -> {return "09.00 P.M. - 10.00 P.M.";}
            default -> {return "ERROR: SYSTEM ERROR OCCURRED WHILE GETTING THE TIMESLOT. TRY AGAIN LATER.";}
        }
    }

    /**
     * This method will print the timeslots along with its availability.
     *
     * callIdentifier signature
     *
     * 1 - just printing available timeslots for viewing purpose only. not getting any inputs related to timeslots.
     * 2 - getting timeslot id from user.
     *
     * @param doctorMedLicenceNo medical licence number of the doctor doing consultation.
     * @param consultDate date of the consultation.
     */
 public void printAvailableTimeSlots(int doctorMedLicenceNo, LocalDate consultDate, int callIdentifier)
    {
        String [] daySlotsAllocation = new String[10];
        for(int i=0; i < 10; i++){ daySlotsAllocation[i] = "AVAILABLE"; }

        if(!WestminsterSkinConsultationManager.getConsultArrList().isEmpty())
        {
            for( Consultation consult : WestminsterSkinConsultationManager.getConsultArrList() )
            {
                if( consult.getConsultationDate().equals(consultDate) && consult.getConsultDoctorMedNo() == doctorMedLicenceNo )
                {
                    daySlotsAllocation [ consult.getTimeSlot().getTimeslotId() -1 ] =  "RESERVED";
                }
                else
                {
                    daySlotsAllocation [ consult.getTimeSlot().getTimeslotId() -1 ] =  "AVAILABLE";
                }
            }
        }
        if(callIdentifier == 2){ System.out.println("\nCHOOSE YOUR TIMESLOT BY ENTERING THE TIMESLOT ID(E.g. 1)\n" ); }
        System.out.println("\nTIMESLOT 1  : 09.00 AM - 10.00 AM : " + daySlotsAllocation[0] + "\n" +
                           "TIMESLOT 2  : 10.00 AM - 11.00 AM : " + daySlotsAllocation[1]   + "\n" +
                           "TIMESLOT 3  : 11.00 AM - 12.00 AM : " + daySlotsAllocation[2]   + "\n" +
                           "TIMESLOT 4  : 01.00 PM - 02.00 PM : " + daySlotsAllocation[3]   + "\n" +
                           "TIMESLOT 5  : 02.00 PM - 03.00 PM : " + daySlotsAllocation[4]   + "\n" +
                           "TIMESLOT 6  : 03.00 PM - 04.00 PM : " + daySlotsAllocation[5]   + "\n" +
                           "TIMESLOT 7  : 05.00 PM - 06.00 PM : " + daySlotsAllocation[6]   + "\n" +
                           "TIMESLOT 8  : 06.00 PM - 07.00 PM : " + daySlotsAllocation[7]   + "\n" +
                           "TIMESLOT 9  : 07.00 PM - 08.00 PM : " + daySlotsAllocation[8]   + "\n" +
                           "TIMESLOT 10 : 09.00 PM - 10.00 PM : " + daySlotsAllocation[9]   + "\n");
    }

    /**
     * this method will give the availability of a timeslot.
     * @param doctorMedLicenceNo medical licence number of the doctor doing consultation.
     * @param consultDate date of the consultation.
     * @return availability
     */
    public String[] availableTimeSlots(int doctorMedLicenceNo, LocalDate consultDate)
    {
        String [] daySlotsAllocation = new String[10];
        for(int i=0; i < 10; i++){ daySlotsAllocation[i] = "AVAILABLE"; }

        if(!WestminsterSkinConsultationManager.getConsultArrList().isEmpty())
        {
            for( Consultation consult : WestminsterSkinConsultationManager.getConsultArrList() )
            {
                if( consult.getConsultationDate().equals(consultDate) && consult.getConsultDoctorMedNo() == doctorMedLicenceNo )
                {
                    daySlotsAllocation [ consult.getTimeSlot().getTimeslotId() -1 ] =  "RESERVED";
                }
                else
                {
                    daySlotsAllocation [ consult.getTimeSlot().getTimeslotId() -1 ] =  "AVAILABLE";
                }
            }
        }
        return daySlotsAllocation;
    }

    /**
     * getter for duration
     * @return duration of the timeslot
     */
    public Duration getDuration()
    {
        return duration;
    }

    /**
     * setter for duration
     * @param duration duration of the timeslot
     */
    public void setDuration(Duration duration)
    {
        this.duration = duration;
    }

    /**
     * getter for startTime
     * @return start time of the timeslot
     */
    public LocalTime getStartTime()
    {
        return startTime;
    }

    /**
     * setter for startTime
     * @param startTime start time of the timeslot
     */
    public void setStartTime(LocalTime startTime)
    {
        this.startTime = startTime;
    }

    /**
     * getter for endTime
     * @return end time of the timeslot
     */
    public LocalTime getEndTime()
    {
        return endTime;
    }

    /**
     * setter for endTime
     * @param endTime end time of the timeslot
     */
    public void setEndTime(LocalTime endTime)
    {
        this.endTime = endTime;
    }

    /**
     * getter for timeslotId
     * @return the timeslot id e.g. 1,2, ... ,10
     */
    public int getTimeslotId()
    {
        return timeslotId;
    }

    /**
     * setter for timeslotId
     * @param timeslotId the timeslot id e.g. 1,2,3, ....,10
     */
    public void setTimeslotId(int timeslotId)
    {
        this.timeslotId = timeslotId;
    }
}
