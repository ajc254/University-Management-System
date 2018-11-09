package university;

/**
 * In classes implementing the Module interface it should be possible 
 * to set the code only once (either at initialisation, or, if not set
 * at initialisation via the setCode method). 
 * <p>
 * It should not be possible to have a Module instance with less 
 * than 0 credits, or more than 120 credits.
 * <p>
 * It should not be possible to have a Module instance whose stage is 
 * not either 1, 2, 3 or 4
 * 
 * @author Jonathan Fieldsend 
 * @version 1.2
 */


public interface Module
{
    /**
     * Method returns the 5-digit code allocated to this module. If 
     * no code has been set, will return null
     * 
     * @return code for this module
     */
    String getCode();
    
    /**
     * Method sets the 5-digit code allocated to this module. 
     * 
     * @throws IDAlreadySetException if the module argument already has its 
     *         code set
     * @throws InvalidIDException if the code is not 5-digits
     */
    void setCode(String code) throws InvalidIDException, IDAlreadySetException;
    
    /**
     * Method returns the name of this module
     * 
     * @return name of this module
     */
    String getName();
    
    /**
     * Returns the stage of this module
     * 
     * @return stage number (1, 2, 3 or 4)
     */
    byte getStage();
    
    /**
     * Returns an array of staff teaching on this module
     * 
     * @return array of teaching staff on this module
     */
    Staff[] getTeachingStaff();
    
    /**
     * Returns the capcity of this module
     * 
     * @return capacity of this module
     */
    int getCapacity();
    
    /**
     * Marks this module as discontinued
     */
    void discontinue();
    
    /**
     * Returns whether this module is discontinued
     * 
     * @return <tt>true</tt> if discontinued
     */
    boolean isDiscontinued();
    
    /**
     * Adds staff in argument to list of staff involved on this modue
     * 
     * @throws DuplicateStaffException if staff contains one or more 
     *         members which are already listed as teaching on this module 
     * @throws IDNotSetException if one or more Staff IDs have not 
     *         been set in the members of the staff argument
     * @throws ModuleDiscontinuedException if the module has been discontinued
     */
    void addStaff(Staff[] staff) throws DuplicateStaffException, IDNotSetException,
    ModuleDiscontinuedException;
    
    /**
     * Adds staff in argument to list of staff involved on this modue
     * 
     * @throws DuplicateStaffException if the staff argument is already 
     *         listed as teaching on this module 
     * @throws IDNotSetException if staff ID has not been set
     * @throws ModuleDiscontinuedException if the module has been discontinued 
     */
    void addStaff(Staff staff) throws DuplicateStaffException, IDNotSetException,
    ModuleDiscontinuedException;
    
    /**
     * Removes the staff argument from the list of staff maintained by this module
     * 
     * @throws StaffNotInvolvedException if the argument is not currently a member 
     *         of the list of teaching staff on this module
     */
    void removeStaff(Staff staff) throws StaffNotInvolvedException;
    
    /**
     * Removes the staff argument from the list of staff maintained by this module
     * 
     * @return the credit value of this module. Will always be in the (inclusive) 
     *         range 0 to 120
     */
    byte getCredits();
    
}
