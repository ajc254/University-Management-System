package university;


/**
 * @author 660047784, 660037119
 * @date 28/03/2017
 */
public class UniversityStaff implements Staff
{
    private String forename;
    private String surname;
    private String id;
    private ObjectArrayList teachingModules;
    
    /**
     * Constructor for UniversityStaff without ID
     * 
     * @param forename Forename of staff member
     * @param surname Surname of staff member
     */
    public UniversityStaff( String forename, String surname ) {
        this.forename = forename;
        this.surname = surname;
        teachingModules = new ObjectArrayList();
    }
    
    /**
     * Constructor for UniversityStaff with ID
     * 
     * @param forename Forename of staff member
     * @param surname Surname of staff member
     * @param id Staff member ID
     * 
     * @throws IDAlreadySetException if there is already a staff member with that id in the system
     * @throws InvalidIdException if the format of the id is invalid
     */
    public UniversityStaff( String forename, String surname, String id ) throws IDAlreadySetException, InvalidIDException {
        this.forename = forename;
        this.surname = surname;
        setID( id );
        teachingModules = new ObjectArrayList();
    }
    
    /**
     * Checks if the id follows the valid conventions
     * 
     * @throws InvalidIDException if id is invalid
     */
    public static void checkValidID( String id ) throws InvalidIDException {
        boolean valid = true;
        
        //length of id must be 5 characters
        if( id.length() != 5 ) valid = false;
        
        //if the id can pass as a hex value
        try {
            Integer.parseInt( id, 16 );
        }
        catch( NumberFormatException e ) {
            valid = false;
        }
        
        if( !valid ) {
            throw new InvalidIDException( "ID provided must be 5 hex characters." );
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
     * @inheritDoc
     */
    public void setID( String id ) throws IDAlreadySetException, InvalidIDException {
        //if the staff member doesn't have an id
        if( this.id == null ) {
            checkValidID(id);
            this.id = id; 
        }
        else {
            throw new IDAlreadySetException( "Staff member '" + forename + " " + surname + "' already has ID: " + id );
        }
    }
    
    /**
     * Links a module to the staff member.
     * @param module module to be linked
     */
    public void addTeachingModule( UniversityModule module ) {
        teachingModules.add( module );
    }
    
    /**
     * Remove link to a module.
     * @param module module to be removed.
     */
    public void removeTeachingModule( UniversityModule module ) {
        teachingModules.remove( module );
    }
    
    /**
     * Get all modules the staff is linked and teaching on.
     * 
     * @returns modules linked
     */
    public Module[] getTeachingModules() {
        Module[] modules = new Module[this.teachingModules.size()];
        
        //convert teachingModules into a module array
        for( int i = 0; i < this.teachingModules.size(); i++ ) {
            modules[i] = (Module)this.teachingModules.get(i);
        }
        return modules;
    }
}
