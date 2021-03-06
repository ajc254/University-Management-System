package university;


/**
 * This class defines exception behaviour required by interface(s) provided 
 * in the university package
 * 
 * @author Jonathan Fieldsend 
 * @version 1.0
 */
public class ModuleDiscontinuedException extends Exception
{
    /**
     * Constructor creates this instance with a message to the client 
     * regarding the cause of the exception
     * 
     * @param message string containing details of exception cause
     */
    public ModuleDiscontinuedException(String message) {
        super(message);
    }
}
