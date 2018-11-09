package university;


/**
 * In classes implementing the Staff interface it should be possible 
 * to set the ID only once (either at initialisation, or, if not set 
 * at initialisation via the setID method).
 * 
 * @author Jonathan Fieldsend 
 * @version 1.1
 */

public interface Staff
{
    /**
     * Method returns the forename of this member of staff
     * 
     * @return forename of this member of staff
     */
    String getForename();
    
    /**
     * Method returns the surname of this member of staff
     * 
     * @return surname of this member of staff
     */
    String getSurname();
    
    /**
     * Method returns the ID this member of staff. This is a String containing 
     * a 5-character hexadecimal number, which should be unique to this 
     * member of staff.
     * 
     * @return the ID of this member of staff
     */
    String getID();
    
    /**
     * Method sets the ID of this member of staff. This is a String 
     * containing a 5-character hexadecimal number, which should be unique 
     * to this member of staff. If an ID has already be set for this 
     * member of staff, then an {@link IDAlreadySetException} will be 
     * thrown. If the argument is not a 5-character hexadecimal number, then 
     * an {@link InvalidIDException} will be thrown
     * 
     * @throws IDAlreadySetException
     * @throws InvalidIDException
     */
    void setID(String id) throws IDAlreadySetException, InvalidIDException;
    
    /**
     * Method prints some details of this member of staff
     */
    default void printInfo() {
        System.out.println(getForename() + " " + getSurname() + ", ID: " + getID());
    }
}
