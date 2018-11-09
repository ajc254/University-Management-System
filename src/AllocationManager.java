package university;
import java.io.IOException;

/**
 * AllocationManager interface. 
 * <p>
 * Any situations causing a declared exception to be thrown from any of 
 * the methods listed should leave AllocationManager in the same state 
 * as prior to the method call.
 * <p>
 * Classes implementing the AllocationManager interface must provide a 
 * no-argument constructor, which returns an AllocationManager instance 
 * with no initial Students, Staff or Modules.
 * 
 * @author Jonathan Fieldsend
 * @version 1.4
 */
public interface AllocationManager
{
    /**
     * Method adds the student detailed by the arguments, and returns the 
     * unique 10-digit ID number, stored in a String, which has been 
     * allocated to the student.
     * 
     * @param forename forename of student to add
     * @param surname surname of student to add
     * @param stage stage of student to add
     * @return ID number assigned to the student
     * @throws InvalidStageException if an invalid stage is entered (less 
     *         than 1 or larger than 4)
     */
    String addStudent(String forename, String surname, byte stage) 
    throws InvalidStageException;
    
    /**
     * Method adds the student argument to the system, and at the same 
     * time should allocate it a unique 10-digit ID number, which should be 
     * set in the student.
     * 
     * @param student student to add to this AllocationManager
     * @throws IDAlreadySetException if the ID of the student argument 
     *         has already been set
     */
    void addStudent(Student student) throws IDAlreadySetException;
    
    /**
     * Method adds the module defined by the arguments to the system, and 
     * returns the unique 5-digit code allocated to it by the 
     * AllocationManager.
     * 
     * @param name module name
     * @param credits number of credits students enrolled on module will earn
     * @param stage stage module is at
     * @param capacity maximium number of students who may be enrolled on the 
     *        module
     * @param staff array of staff who teach on the module
     * @return the unique 5-digit code allocated to the module
     * @throws InvalidStageException if the stage value is less than 1 or 
     *         larger than 4
     * @throws InvalidCreditsException if the credits argument is less than 0 
     *         or greater than 120
     * @throws InvalidCapacityException if the capacity argument is less than 1
     * @throws DuplicateStaffException if the staff array contains duplicates
     * @throws StaffNotInSystemException if the staff array contains members 
     *         who have not previously been added to this AllocationManager
     */
    String addModule(String name, byte credits, byte stage, int capacity, Staff[] staff) 
    throws InvalidStageException, InvalidCreditsException, InvalidCapacityException, 
    DuplicateStaffException, StaffNotInSystemException;
    
    /**
     * Method adds the module argument to the system, and at the same time 
     * should allocate it a unique 5-digit code, which should be set in the 
     * module argument. It is only legal to pass in a module argument to this
     * method which does not have any staff currently assigned to it.
     *
     * @param module module to add to this AllocationManager
     * @throws IDAlreadySetException if the module argument already has its 
     *         code set
     */
    void addModule(Module module) throws IDAlreadySetException;
    
    /**
     * Method adds the staff member detailed by the arguments, and returns 
     * the unique 5-character Hexadecimal ID number, stored in a String, 
     * which has been allocated to the staff member.
     * 
     * @param forename forename of staff to add
     * @param surname surname of staff to add
     * @return ID number assigned to the staff member
     */
    String addStaff(String forename, String surname);
    
    /**
     * Method adds the staff argument to the system, and at the same time 
     * should allocate it a unique 5-character hexadecimal ID number, which 
     * should be set in the staff member.
     * <p>
     * If the argument already has an ID set a IDAlreadySetException will be 
     * thrown, and the staff argument will not be added to this 
     * AllocationManager.
     * 
     * @param staff member of staff to add to this AllocationManager
     * @throws IDAlreadySetException if the ID of the staff argument has 
     *         already been set
     */
    void addStaff(Staff staff) throws IDAlreadySetException;
    
    
    /**
     * Method discontinues the module matching the moduleCode argument in 
     * this AllocationManager. On completion, all students registered on 
     * this module should be released, with their state reflecting this, 
     * and also the staff deallocated from the module.
     * 
     * @param moduleCode code of module to be discontinued
     * @throws InvalidIDException if the code is not 5-digits
     * @throws IDNotRecognisedException if the legal code does not match 
     *         any module in the system
     */
    void discontinue(String moduleCode) throws InvalidIDException, 
    IDNotRecognisedException;
    
    
    /**
     * Method enrols the student matching the studentID on the module 
     * matching the moduleCode.
     * 
     * @param studentID ID of student to be enrolled
     * @param moduleCode module code of module the student is to be 
     *          enrolled on
     * @throws InvalidIDException if the student ID is not 10-digits 
     *          and/or the module code is not 5-digits
     * @throws IDNotRecognisedException if the student ID does not match 
     *          a student stored in the system and/or the module code does 
     *          not match a module stored in the system
     * @throws ModuleAtCapacityException if the module is full and unable 
     *          to accommodate the student
     * @throws InsufficientAvailableCreditsException if the students does 
     *          not have sufficient free credits to legally enrol on the 
     *          module (a fully enrolled student has 120 credits)
     * @throws ModuleDiscontinuedException if the module is discontinued 
     *          and therefore unable to have students enrol
     * @throws ModuleStageTooHighException if the module is at a higher stage 
     *          than the student can legally enrol on (i.e. above the
     *          student's stage)
     * @throws EnrollingWouldPreventHonoursException if the module is at a 
     *          lower stage that the student's current stage, and enrolling
     *          on it would make it impossible for the student to have 90 
     *          credits at their current stage. Also, if the student is 
     *          already enrolled on the corresponding module.
     */
    void enrol(String studentID, String moduleCode) throws InvalidIDException, 
    IDNotRecognisedException, ModuleAtCapacityException, 
    InsufficientAvailableCreditsException, ModuleDiscontinuedException,
    ModuleStageTooHighException, EnrollingWouldPreventHonoursException;
    
    /**
     * Method should load and replace this AllocationManager's contents 
     * with the serialised contents stored in the file given in the 
     * argument.
     * 
     * @param filename name (including location) of the file to be saved
     * @throws IOException  if there is a problem experienced when trying 
     *          to load the contents of the file
     * @throws CLassNotFoundException   if required class files cannot be 
     *          found when loading
     * 
     */
    void loadAllocationManager(String filename) throws IOException, 
    ClassNotFoundException;
    
    /**
     * Method returns the number of staff stored by this 
     * AllocationManager
     * 
     * @return number of stored staff
     */
    int getNumberOfStaff();
    
    /**
     * Method returns the number of students stored by this 
     * AllocationManager
     * 
     * @return number of stored students
     */
    int getNumberOfStudents();
    
    /**
     * Method returns the number of modules stored by this 
     * AllocationManager
     * 
     * @return number of stored modules
     */
    int getNumberOfModules();
    
    /**
     * Method returns an array containing all staff stored by this 
     * AllocationManager
     * 
     * @return an array containing all staff stored by the system
     */
    Staff[] getStaff();
    
    /**
     * Method returns an array containing the staff the module matching 
     * the argument <tt>moduleCode</tt> have currently teaching on it
     * 
     * @param moduleCode a module code matching a module stored in the 
     *          system
     * @return an array containing all staff teaching on the module
     * @throws InvalidIDException if the moduleCode is not a valid 
     *          5-digit number
     * @throws IDNotRecognisedException if the module code does not 
     *          match any stored in the system
     */
    Staff[] getStaff(String moduleCode) throws InvalidIDException, 
    IDNotRecognisedException;
    
    
    /**
     * Method returns an array containing all students stored by this 
     * AllocationManager
     * 
     * @return an array containing all students stored by the system
     */
    Student[] getStudents();
    
    /**
     * Method returns an array containing the students which are enrolled 
     * on the module matching the argument code
     * 
     * @param moduleCode a module code matching a module stored in the 
     *          system
     * @return an array containing all students enrolled on the module
     * @throws InvalidIDException if the moduleCode is not 5-digits
     * @throws IDNotRecognisedException if the module code does not 
     *          match any stored in the system
     */
    Student[] getStudents(String moduleCode) throws InvalidIDException, 
    IDNotRecognisedException;
    
    /**
     * Method returns an array containing all modules stored by this 
     * AllocationManager, including discontinued modules
     * 
     * @return an array containing all modules stored by the system
     */
    Module[] getModules();
    
    /**
     * Method returns an array containing all modules stored by this 
     * AllocationManager, excluding discontinued modules
     * 
     * @return an array containing all modules stored by the system, 
     *          excluding discontinued modules
     */
    Module[] getRunningModules();
    
    /**
     * Method returns an array containing all modules stored by this 
     * AllocationManager, excluding discontinued modules and those at 
     * capacity
     * 
     * @return an array containing all modules stored by the system, 
     *          excluding discontinued modules and those at capacity
     */
    Module[] getAvailableModules();
    
    /**
     * Method returns an array containing the modules the student matching 
     * the argument student ID currently is registered on
     * 
     * @param studentID a 10-digit student code matching a student on 
     *          the system
     * @return an array of modules the student is enrolled on
     * @throws InvalidIDException if the studentID is not 10-digits
     * @throws IDNotRecognisedException if the studentCode is not in 
     *          the system
     */
    Module[] getModules(String studentID) throws InvalidIDException, 
    IDNotRecognisedException;
    
    /**
     * Method returns the number of students maintained by the system who 
     * have been allocated their complete complement of credits
     * 
     * @return number of students with complete module allocation given 
     *          their credit limit
     */
    int getNumberOfFullyAllocatedStudents();
    
    /**
     * Method returns the number of modules currently filled to capacity.
     * 
     * @return number of modules at capacity
     */
    int getNumberOfModulesAtCapacity();
    
    /**
     * Method removes the staff from this AllocationManager. The staff 
     * will be removed from any modules they teach on, and
     * on method return no trace of the staff member will remain in this 
     * AllocationManager.
     * <p>
     * Returns an array (potentially empty) of modules which now have no
     * teaching staff due to the removal of the staff argument from this
     * AllocationManager
     * 
     * @return array of modules which have no teaching staff due to 
     *          removal of the staff member due to the method call
     * @throws InvalidIDException if the staff's ID is not a 5-character hexadecimal
     * @throws IDNotRecognisedException if the staff is not in 
     *          the system
     * @throws IDNotSetException if the staff argument's ID has not 
     *          been set
     */
    Module[] remove(Staff staff) throws InvalidIDException, 
    IDNotRecognisedException, IDNotSetException;
    
    /**
     * Method removes the student from this AllocationManager. The student 
     * will be removed from any modules they have sucessfully enrolled on, and
     * on method return no trace of the student will remain in this 
     * AllocationManager.
     * 
     * @throws InvalidIDException if the student's ID is not 10-digits
     * @throws IDNotRecognisedException if the student's ID is not in 
     *          the system
     * @throws IDNotSetException if the student's ID has not 
     *          been set
     */
    void remove(Student student) throws InvalidIDException, 
    IDNotRecognisedException, IDNotSetException;
    
    /**
     * Method saves this AllocationManager's contents into a serialised 
     * file, with the filename given in the argument.
     * 
     * @param filename  name (including location) of the file to be saved
     * @throws IOException  if there is a problem experienced when trying 
     *          to save the contents to the file
     */
    void saveAllocationManager(String filename) throws IOException;
    
    /**
     * Removes the student matching the studentID argument from the module 
     * matching the moduleCode argument. On method returning, these items 
     * will not longer be linked, and their state will correctly reflect 
     * this. If the both the ID and code exist, but they are not linked on 
     * entry to the method, <tt>false</tt> will be returned, otherwise the 
     * method will complete returning <tt>true</tt>.
     * 
     * @param studentID ID of student to be enrolled
     * @param moduleCode module code of module the student is to be 
     *          enrolled on
     * @return true if the student is unenrolled, false if the student was 
     *          not enrolled on method entry
     * @throws InvalidIDException if the student ID is not 10-digits and/or 
     *          the module code is not 5-digits
     * @throws IDNotRecognisedException if the student ID does not match a 
     *          student stored in the system and/or the module code does not 
     *          match a module stored in the system
     */
    boolean unEnrol(String studentID, String moduleCode) throws 
    InvalidIDException, IDNotRecognisedException;
}
