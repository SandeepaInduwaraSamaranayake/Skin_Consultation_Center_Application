package console_application;

/**
 COPYRIGHT (C) Sandeepa Induwara Samaranayake - 20210302 - W1867067- sandeepa.20210302@iit.ac.lk. All Rights Reserved.
 Object-Oriented Programming Coursework L5 sem 1
 @author Sandeepa Induwara Samaranayake
 @version 1 Console application
 */

/**
 * this class will handle the invalid input exceptions.
 */
public class InvalidInputErrorException extends Exception
{
    private final int FLAG;
    private String errorDetails;

    /**
     * constructor for InvalidInputErrorException class which accepts one parameter FLAG.
     * @param FLAG the error flag to easily identify the error. (all the error flags and what indicates from that flags are listed in the FLAGS_INFO.txt file).
     */
    public InvalidInputErrorException(int FLAG)
    {
        this.FLAG = FLAG;
    }

    /**
     * constructor for InvalidInputErrorException which accepts two parameters FLAG and errorDetails.
     * @param FLAG the error flag to easily identify the error. (all the error flags and what indicates from that flags are listed in the FLAGS_INFO.txt file).
     * @param errorDetails error details to show to the user.
     */
    public InvalidInputErrorException(int FLAG, String errorDetails)
    {
        this.FLAG = FLAG;
        this.errorDetails = errorDetails;
    }

    /**
     * overriding toString method of Throwable class to show error messages to user.
     * @return input error message as a string
     */
    @Override
    public String toString()
    {
        return String.format("%s ERROR : %s. ERROR FLAG = %d", errorDetails, super.toString(),FLAG );
    }
}
