package university;

/**
 * UniversityAllocationManager
 * <p>
 * Allocation Manager to hold staff, students and modules in a university and their relationships.
 * 
 * @author 660037119, 660047784
 * @date 28/03/2017
 */

public class UniversityStudent implements Student
{
    private String forename;
    private String surname;
    private byte stage;
    private ObjectArrayList modules;
    private byte sameStageCredits;
    private byte lowerStageCredits;
    private byte totalCredits;
    private String id;
    
    /**
     * Constructor for University Student without ID.
     * 
     * @param forename forename of student
     * @param surname surname of student
     * @param stage stage of student
     * @throws InvalidStageException if the student has a stage out of range 1-4 inclusive
     */
    public UniversityStudent( String forename, String surname, byte stage ) throws InvalidStageException {
        this.forename = forename;
        this.surname = surname;
        setStage( stage );
        modules = new ObjectArrayList();
    }
    
    /**
     * Constructor for University Student without ID.
     * 
     * @param forename forename of student
     * @param surname surname of student
     * @param stage stage of student
     * @param id id of the student
     * @throws InvalidStageException if the student has a stage out of range 1-4 inclusive
     */
    public UniversityStudent( String forename, String surname, byte stage, String id ) throws InvalidStageException, InvalidIDException, IDAlreadySetException {
        this.forename = forename;
        this.surname = surname;
        setStage( stage );
        setID( id );
        modules = new ObjectArrayList();
    }
    
    /**
     * Check an ID is of correct student form.
     * 
     * @param id id to be checked
     * @throws InvalidIDException if the ID is of incorrect form
     */
    public static void checkValidID(String id) throws InvalidIDException{
        boolean valid = true;
        
        if( id.length() != 10 ) valid = false;
        
        try {
            Long.parseLong( id );
        }
        catch( NumberFormatException e ) {
            valid = false;
        }
        
        if( !valid ) {
            throw new InvalidIDException("Student ID provided must be 10 digits.");
        }
    }
    
    /**
        * @inheritDoc
     */
    public byte getStage() {
        return stage;
    }
    
    /**
     * Set the stage of a student.
     * 
     * @param stage stage of student to be set
     * @throws InvalidStageException if the stage is out of range 1-4 inclusive.
     */
    private void setStage( byte stage ) throws InvalidStageException {
        if( stage < 1 || stage > 4 ) {
            throw new InvalidStageException( "Stage for student '" + forename + surname + "' must be between 1 and 4 inclusive." );
        }
        else {
            this.stage = stage;
        }
    }
    
    /**
        * @inheritDoc
     */
    public String getForename() {
        return forename;
    }
    
    /**
        * @inheritDoc
     */
    public String getSurname() {
        return surname;
    }
    
    /**
        * @inheritDoc
     */
    public String getID() {
        return id;
    }
    
    /**
        * Return the credits the student has from modules enrolled at the same stage as them
        * 
        * @returns credits at the same stage
     */
    public byte getSameStageCredits() {
        return sameStageCredits;
    }
    
    /**
        * Return the credits the student has from modules enrolled at a lower stage than them
        * 
        * @returns credits at a lower stage
     */
    public byte getLowerStageCredits() {
        return lowerStageCredits;
    }
    
    /**
        * Return the credits the student has from modules enrolled at a higher stage
        * 
        * @returns credits at a higher stage
     */
    public byte getTotalCredits() {
        return totalCredits;
    }
    
    /**
        * @inheritDoc
     */
    public void setID( String id ) throws IDAlreadySetException, InvalidIDException {
        if( this.id == null) { 
            checkValidID(id);
            this.id = id;
        }
        else { 
            throw new IDAlreadySetException ("Student '" + forename + " " + surname + "' already has ID: " + this.id);
        }
    }
    
    /**
        * Return the modules this student is currently enrolled on.
        * 
        * @returns modules enrolled
     */
    public Module[] getEnrolledModules() {
        Module[] modules = new Module[this.modules.size()];
        
        for( int i = 0; i < this.modules.size(); i++ ) {
            modules[i] = (Module)this.modules.get(i);
        }
        return modules;
    }
    
    /**
        * Assigns and links the student to a module.
        * 
        * @param module module to be assigned
     */
    public void assignModule( UniversityModule module ) {
        modules.add( module );
        
        if( module.getStage() == stage ) {
            sameStageCredits += module.getCredits();
        }
        else {
            lowerStageCredits += module.getCredits();
        }
        totalCredits += module.getCredits();
    }
    
        /**
        * Removes and unlinks the student from a module.
        * 
        * @param module module to be assigned
     */
    public void removeModule( UniversityModule module ) {
        modules.remove( module );
        
        if( module.getStage() == stage ) {
            sameStageCredits -= module.getCredits();
        }
        else {
            lowerStageCredits -= module.getCredits();
        }
        totalCredits -= module.getCredits();
    }
}
