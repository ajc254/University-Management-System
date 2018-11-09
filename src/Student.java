package university;


/**
 * In classes implementing the Student interface it should be possible 
 * to set the ID only once (either at initialisation, or, if not set at 
 * initialisation via the setID method).
 * <p>
 * It should not be possible to have a Student instance whose stage is 
 * not either 1, 2, 3 or 4
 * 
 * @author Jonathan Fieldsend 
 * @version 1.0
 */
public interface Student
{
    /**
     * Method returns the stage of this student (1, 2, 3 or 4)
     * 
     * @return stage of this student
     */
    byte getStage();
    
    /**
     * Method returns the forename of this student
     * 
     * @return forename of this student
     */
    String getForename();
    
    /**
     * Method returns the surname of this student
     * 
     * @return surname of this student
     */
    String getSurname();
    
    
    /**
     * Method returns the ID this student. This is a String containing 
     * 10 digits, which should be unique to this student.
     * 
     * @return the ID of this student
     */
    String getID();
    
    /**
     * Method sets the ID of this student. This is a String containing 
     * 10 digits, which should be unique to this student. If an ID has 
     * already be set for this student, then an {@link IDAlreadySetException} 
     * will be thrown. If the argument is not a 10-digit String, then an 
     * {@link InvalidIDException} will be thrown
     * 
     * @throws IDAlreadySetException
     * @throws InvalidIDException
     */
    void setID(String id) throws IDAlreadySetException, InvalidIDException;
    
    /**
     * Method prints some details of this student
     */
    default void printInfo() {
        System.out.println(getForename() + " " + getSurname() + 
             ", stage: " + getStage() + ", ID: " + getID());
    }
}
