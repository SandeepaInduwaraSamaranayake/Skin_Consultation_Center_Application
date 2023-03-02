package console_application;

import java.sql.SQLException;

/**
 COPYRIGHT (C) Sandeepa Induwara Samaranayake - 20210302 - W1867067- sandeepa.20210302@iit.ac.lk. All Rights Reserved.
 Object-Oriented Programming Coursework L5 sem 1
 @author Sandeepa Induwara Samaranayake
 @version 1 Console application
 */

/**
 * this class will handle the database error exceptions
 */
public class DatabaseConnectionErrorException extends SQLException
{
    private final int FLAG;
    private SQLException errorDetails;

    /**
     * constructor for DatabaseConnectionErrorException class.
     * @param FLAG error flag to easily identify the error. (all the error flags and what indicates from that flags are listed in the FLAGS_INFO.txt file)
     */
    public DatabaseConnectionErrorException(int FLAG)
    {
        this.FLAG = FLAG;
    }

    /**
     * constructor for DatabaseConnectionErrorException class which accepts two parameters FLAG and errorDetails.
     * @param FLAG  error flag to easily identify the error. (all the error flags and what indicates from that flags are listed in the FLAGS_INFO.txt file)
     * @param errorDetails error details to show to the user.
     */
    public DatabaseConnectionErrorException(int FLAG, SQLException errorDetails)
    {
        this.FLAG = FLAG;
        this.errorDetails = errorDetails;
    }

    /**
     * overriding toString method of Throwable class to show error messages to user.
     * @return database error message as a string
     */
    @Override
    public String toString()
    {
        System.out.println("error :" + errorDetails.getStackTrace() );
        return String.format("DATABASE CONNECTION ERROR : %s SQL ERROR CODE = %s ERROR FLAG = %d",
                errorDetails.getMessage(), errorDetails.getErrorCode(),FLAG );
    }
}
