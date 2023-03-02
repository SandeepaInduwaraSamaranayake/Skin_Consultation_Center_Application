package console_application;

/**
 COPYRIGHT (C) Sandeepa Induwara Samaranayake - 20210302 - W1867067- sandeepa.20210302@iit.ac.lk. All Rights Reserved.
 Object-Oriented Programming Coursework L5 sem 1
 @author Sandeepa Induwara Samaranayake
 @version 1 Console application
 */

/**
 * this class will handle the validation exceptions.
 */
public class ValidateErrorException extends Exception
{
    int flag;
    String errorFieldName, errorDetails, example;

    /**
     * constructor of the ValidateErrorException class.
     * @param flag the error flag to easily identify the error. (all the error flags and what indicates from that flags are listed in the FLAGS_INFO.txt file).
     * @param errorFieldName error field name to indicate which field failed the validation.
     * @param errorDetails  error details to show to the user.
     * @param example sample answer to help user.
     * @throws ValidateErrorException if validation failed.
     */
    public ValidateErrorException(int flag, String errorFieldName, String errorDetails, String example) throws ValidateErrorException
    {
        this.flag = flag;
        this.errorFieldName = errorFieldName;
        this.errorDetails = errorDetails;
        this.example = example;
    }

    /**
     * overriding toString method of Throwable class to show error messages to user.
     * @return validation error message as a string
     */
    @Override
    public String toString()
    {
        return String.format( "ERROR : THE ENTERED %s CANNOT PROCEED. (MAKE SURE %s. E.g. %s ). ERROR FLAG = %d", errorFieldName, errorDetails, example, flag);
    }
}
