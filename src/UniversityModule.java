package university;

/**
 * 
 * 
 * @author 660047784, 660037119
 * @date 28/03/2016
 */
public class UniversityModule implements Module
{
    private String name;
    private String code;
    private byte credits;
    private byte stage;
    private ObjectArrayList teachingStaff;
    private ObjectArrayList students;
    private int capacity;
    private int enrolled;
    private boolean discontinued;
    
    /**
     * Constructor for UniversityModule without module code
     * 
     * @param name Name of the module
     * @param credits The number of credits that the module gives
     * @param stage What stage the module can be taken at
     * @param capacity The maximum number of students that can take the module
     */
    public UniversityModule( String name, byte credits, byte stage, int capacity ) throws InvalidStageException, InvalidCreditsException {
        this.name = name;
        setCredits( credits );
        setStage( stage );
        teachingStaff = new ObjectArrayList();
        students = new ObjectArrayList();
        this.capacity = capacity;
        discontinued = false;
    }
    
    /**
     * Constructor for UniversityModule with module code
     * 
     * @param name Name of the module
     * @param credits The number of credits that the module gives
     * @param stage What stage the module can be taken at
     * @param capacity The maximum number of students that can take the module
     * @param code The module code
     */
    public UniversityModule( String name, byte credits, byte stage, int capacity, String code ) throws InvalidStageException, InvalidCreditsException, InvalidIDException, 
        IDAlreadySetException {
        this.name = name;
        setCredits( credits );
        setStage( stage );
        teachingStaff = new ObjectArrayList();
        students = new ObjectArrayList();
        this.capacity = capacity;
        discontinued = false;
        setCode( code );
    }
    
    /**
     * Checks that the format of the module code is correct
     * 
     * @param code The module code to check
     * @return true if the module code is valid, else false
     */
    public static boolean checkValidCode( String code ) throws InvalidIDException {
        boolean valid = true;
        
        //if the code isn't 5 characters
        if( code.length() != 5 ) valid = false;
        
        //if the code passes as an integer
        try {
            Integer.parseInt( code );
        }
        catch( NumberFormatException e ) {
            valid = false;
        }
        if( !valid ) {
            throw new InvalidIDException("Module code provided must be 5 digits.");
        }
        return valid;
    }
    
    /**
     * @inheritDoc
     */
    public String getCode(){
        return code;
    }
    
    /**
     * @inheritDoc
     */
    public void setCode(String code) throws InvalidIDException, IDAlreadySetException {
        //if the module doesn't have a module code
        if( this.code == null) {
            checkValidCode(code);
            this.code = code;
        }
        else { throw new IDAlreadySetException ("This module already has a code (" + this.code + " )" );
        }
    }
    
    /**
     * @inheritDoc
     */
    public String getName(){
        return name;
    }
    
    /**
     * @inheritDoc
     */
    public byte getStage(){
        return stage;
    }
    
    /**
     * Set the stage of the module.
     * 
     * @param stage stage to be set.
     * @throws InvalidStageException if the stage is not 1-4 inclusive.
     */
    private void setStage( byte stage ) throws InvalidStageException {
        //check the stage is between 1 and 4
        if( stage < 1 || stage > 4 ) {
            throw new InvalidStageException( "Stage for module '" + name + "' must be between 1 and 4 inclusive." );
        }
        else {
            this.stage = stage;
        }
    }
    
    /**
     * Set the credits of the module.
     * 
     * @param credits credits to be set.
     * @throws InvalidCreditsException if the credits are not between 0-120 inclusive.
     */
    private void setCredits( byte credits ) throws InvalidCreditsException {
        //check that the number of credits is between 0 and 120
        if( credits < 0 || credits > 120 ) {
            throw new InvalidCreditsException( "Credits for module '" + name + "' must be between 0 and 120 inclusive." );
        }
        else {
            this.credits = credits;
        }
    }
    
    /**
     * Returns the credits valued by the module
     * 
     * @returns the credits of the module
     */
    public byte getCredits() {
        return credits;
    }
   
    /**
     * @inheritDoc
     */
    public Staff[] getTeachingStaff(){
        Staff[] teachingStaff = new Staff[this.teachingStaff.size()];
        
        //convert staff stored by module to a staff array
        for( int i = 0; i < this.teachingStaff.size(); i++ ) {
            teachingStaff[i] = (Staff)this.teachingStaff.get(i);
        }
        return teachingStaff;
    }
    
    /**
     * @inheritDoc
     */
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * Checks if the module has reached capacity
     * 
     * @return true if module is at maximum capacity, false otherwise
     */
    public boolean isAtCapacity() {
        if( enrolled == capacity ){
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Discontinue the module, removing all links.
     */
    public void discontinue(){
        teachingStaff = new ObjectArrayList();
        students = new ObjectArrayList();
        enrolled = 0;
        discontinued = true;
    }
    
    /**
     * @inheritDoc
     */
    public boolean isDiscontinued(){
        return discontinued;
    }
    
    /**
     * @inheritDoc
     */
    public void addStaff(Staff[] staff) throws DuplicateStaffException, IDNotSetException, ModuleDiscontinuedException{
        
        if( discontinued ) {
            throw new ModuleDiscontinuedException( "Module " + name + " is discontinued, and so cannot accept more staff." );
        }
        for( Staff staffToAdd : staff ){
            //if the staff member doens't have an id, throw IDNotSetException
            if( staffToAdd.getID() == null ) {
                throw new IDNotSetException( "Attempted to add a staff member '" + staffToAdd.getForename() + " " + staffToAdd.getSurname() + 
                "' whom has no ID set into module: " + name );
            }
            for( int i=0; i < teachingStaff.size(); i++ ) {
                //if there is a staff member with the same id in the module
                if ( staffToAdd.equals(teachingStaff.get(i)) ) {
                    throw new DuplicateStaffException( "Attempted to add a duplicate staff member with ID: " + staffToAdd.getID() + ", in module " + name );
                }
            }
            teachingStaff.add( staffToAdd );
        }
    }
    
    /**
     * @inheritDoc
     */
    public void addStaff(Staff staff) throws DuplicateStaffException, IDNotSetException, ModuleDiscontinuedException{
        if( discontinued ) {
            throw new ModuleDiscontinuedException( "Module '" + name + "' is discontinued, and so cannot accept more staff." );
        }
        //if the staff member doens't have an id, throw IDNotSetException
        if( staff.getID() == null ) {
                throw new IDNotSetException( "Attempted to add a staff member '" + staff.getForename() + " " + staff.getSurname() + 
                "' whom has no ID set into module: " + name );
        }
        for( int i=0; i < teachingStaff.size(); i++ ) {
            //if there is a staff member with the same id in the module
            if( staff.equals(teachingStaff.get(i)) ) {
                throw new DuplicateStaffException( "Attempted to add a duplicate staff member with ID: " + staff.getID() + ", in module " + name );
            }
        }
        teachingStaff.add( staff );
    }
    
    /**
     * @inheritDoc
     */
    public void removeStaff(Staff staff) throws StaffNotInvolvedException{
        boolean staffFound = teachingStaff.remove(staff);
        
        if( !staffFound ) {
            throw new StaffNotInvolvedException( "Staff member with ID: " + staff.getID() + " is not found in module: " + name + ", therefore cannot be removed" );
        }
    }
    
    /**
     * Allocates a student to the module array
     * 
     * @param student Student object to add to module
     */
    public void addStudent( UniversityStudent student ) {
        students.add( student );
        enrolled++;
    }
    
    /**
     * Removes a student from the module
     * 
     * @param student Student to remove from module
     */
    public void removeStudent( UniversityStudent student ) {
        students.remove( student );
        enrolled--;
    }
    
    /**
     * Gets all the students enrolled onto the module
     * 
     * @return all students enrolled onto the module as a student array
     */
    public Student[] getStudents() {
        Student[] students = new Student[this.students.size()];
        
        //convert students stored by module to a Student array
        for( int i = 0; i < this.students.size(); i++ ) {
            students[i] = (Student)this.students.get(i);
        }
        return students;
    }
}

