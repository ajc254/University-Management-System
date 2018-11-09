package university;

import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;


/**
 * UniversityAllocationManager
 * <p>
 * Allocation Manager to hold staff, students and modules in a university and their relationships.
 * 
 * @author 660037119, 660047784
 * @date 28/03/2017
 */

public class UniversityAllocationManager implements AllocationManager, Serializable
{
private ObjectArrayList students;
private ObjectArrayList staff;
private ObjectArrayList modules;
    
    /**
        * Constructor for the university allocation manager.
     */
    public UniversityAllocationManager() {
        students = new ObjectArrayList();
        staff = new ObjectArrayList();
        modules = new ObjectArrayList();
    }
    
    /**
        * Binary search current students stored via ID.
        * 
        * @param ID student ID to search
        * 
        * @returns UniversityStudent stored on system if existent, else null.
     */
    private UniversityStudent binarySearchStudentID( String ID ){
        int lowerBound = 0;
        int upperBound = students.size() - 1;
        String currentIndexValue;
       
        while (upperBound >= lowerBound){
            int currentIndex = (lowerBound + upperBound) / 2;
            currentIndexValue = (String)( (Student) students.get(currentIndex) ).getID();
            
            if( currentIndexValue.compareTo(ID) == 0 ){
                return (UniversityStudent)students.get(currentIndex);
            }
            if( currentIndexValue.compareTo(ID) < 0){
                lowerBound = currentIndex + 1;
            }
            if( currentIndexValue.compareTo(ID) > 0){
                upperBound = currentIndex - 1;
            }
        }
        return null;
    }
    
    /**
        * Binary search current staff stored via ID.
        * 
        * @param ID staff ID to search
        * 
        * @returns UniversityStaff stored on system if existent, else null.
     */
    private UniversityStaff binarySearchStaffID( String ID ){
        int lowerBound = 0;
        int upperBound = staff.size() - 1;
        String currentIndexValue;
       
        while (upperBound >= lowerBound){
            int currentIndex = (lowerBound + upperBound) /2;
            currentIndexValue = (String)( (Staff) staff.get(currentIndex) ).getID();
            
            if( currentIndexValue.compareTo(ID) == 0 ){
                return (UniversityStaff)staff.get(currentIndex);
            }
            if( currentIndexValue.compareTo(ID) < 0){
                lowerBound = currentIndex + 1;
            }
            if( currentIndexValue.compareTo(ID) > 0){
                upperBound = currentIndex - 1;
            }
        }
        return null;
    }
    
    /**
        * Binary search current modules stored via code.
        * 
        * @param code module code to search
        * 
        * @returns UniversityModule stored on system if existent, else null.
     */
    private UniversityModule binarySearchModuleCode( String code ){
        int lowerBound = 0;
        int upperBound = modules.size() - 1;
        String currentIndexValue;
       
        while (upperBound >= lowerBound){
            int currentIndex = (lowerBound + upperBound) /2;
            currentIndexValue = (String)( (Module) modules.get(currentIndex) ).getCode();
            
            if( currentIndexValue.compareTo(code) == 0 ){
                return (UniversityModule)modules.get(currentIndex);
            }
            if( currentIndexValue.compareTo(code) < 0){
                lowerBound = currentIndex + 1;
            }
            if( currentIndexValue.compareTo(code) > 0){
                upperBound = currentIndex - 1;
            }
        }
        return null;
    }
    
    /**
        * Binary insert a student into the manager in order of ID string lexographical ordering.
        * 
        * @param student student to insert
     */
    private void binaryInsertStudent( UniversityStudent student ) {
     
       if (students.size() == 0) {
           students.add(student);
           return;
       }
       int lowerBound = 0;
       int upperBound = students.size() - 1;
       int currentIndex = 0;
       String currentIndexValue;
        
       while (true) {
            currentIndex = (upperBound + lowerBound) / 2;
            currentIndexValue = (String)( (Student)students.get(currentIndex) ).getID();
            if ( currentIndexValue.compareTo( student.getID() ) == 0 ) {
                break;
            } else if ( currentIndexValue.compareTo( student.getID() ) < 0 ) {
                lowerBound = currentIndex + 1; 
                if ( lowerBound > upperBound ) {
                    currentIndex += 1;
                    break;
                }
            } else {
                upperBound = currentIndex - 1; 
                if ( lowerBound > upperBound )
                    break;
            }
       }
   
       ObjectArrayList newArray = new ObjectArrayList();
       for( int i=0; i<currentIndex; i++ ) {
           newArray.add( students.get(i) );
       }
       newArray.add( student );
       for( int i=currentIndex; i<students.size(); i++ ) {
           newArray.add( students.get(i) );
       }
   
       students = newArray;
    }
    
    /**
        * Binary insert a module into the manager in order of code string lexographical ordering.
        * 
        * @param module module to insert
     */
    private void binaryInsertModule( UniversityModule module ) {
     
       if ( modules.size() == 0 ) {
           modules.add(module);
           return;
       }
       int lowerBound = 0;
       int upperBound = modules.size() - 1;
       int currentIndex = 0;
       String currentIndexValue;
        
       while (true) {
            currentIndex = (upperBound + lowerBound) / 2;
            currentIndexValue = (String)( (Module)modules.get(currentIndex) ).getCode();
            if ( currentIndexValue.compareTo( module.getCode() ) == 0 ) {
                break;
            } else if ( currentIndexValue.compareTo( module.getCode() ) < 0 ) {
                lowerBound = currentIndex + 1; 
                if ( lowerBound > upperBound ) {
                    currentIndex += 1;
                    break;
                }
            } else {
                upperBound = currentIndex - 1; 
                if ( lowerBound > upperBound )
                    break;
            }
       }
   
       ObjectArrayList newArray = new ObjectArrayList();
       for( int i=0; i<currentIndex; i++ ) {
           newArray.add( modules.get(i) );
       }
       newArray.add( module );
       for( int i=currentIndex; i<modules.size(); i++ ) {
           newArray.add( modules.get(i) );
       }
   
       modules = newArray;
    }
    
    /**
        * Binary insert a staff member into the manager in order of ID string lexographical ordering.
        * 
        * @param staff staff to insert
     */
    private void binaryInsertStaff( UniversityStaff staff ) {
     
       if (this.staff.size() == 0) {
           this.staff.add(staff);
           return;
       }
       int lowerBound = 0;
       int upperBound = this.staff.size() - 1;
       int currentIndex;
       String currentIndexValue;
        
       while (true) {
            currentIndex = (upperBound + lowerBound) / 2;
            currentIndexValue = (String)( (Staff)this.staff.get(currentIndex) ).getID();
            if ( currentIndexValue.compareTo( staff.getID() ) == 0 ) {
                break;
            } else if ( currentIndexValue.compareTo( staff.getID() ) < 0 ) {
                lowerBound = currentIndex + 1; 
                if ( lowerBound > upperBound ) {
                    currentIndex += 1;
                    break;
                }
            } else {
                upperBound = currentIndex - 1; 
                if ( lowerBound > upperBound )
                    break;
            }
       }
   
       ObjectArrayList newArray = new ObjectArrayList();
       for( int i=0; i<currentIndex; i++ ) {
           newArray.add( this.staff.get(i) );
       }
       newArray.add( staff );
       for( int i=currentIndex; i<this.staff.size(); i++ ) {
           newArray.add( this.staff.get(i) );
       }
   
       this.staff = newArray;
    }
    
    
    
    
    /**
        * @inheritDoc
     */
    public String addStudent(String forename, String surname, byte stage) throws InvalidStageException {
        
        UniversityStudent student = new UniversityStudent( forename, surname, stage );
        String studentID = generateStudentID();
        
        try {
            student.setID( studentID );
        }
        catch( InvalidIDException e ) {
            e.printStackTrace();
        }
        catch( IDAlreadySetException e ) {
            e.printStackTrace();
        }

        binaryInsertStudent( student );
        return student.getID();
    }
    
    
    /**
        * @inheritDoc
     */
    public void addStudent(Student student) throws IDAlreadySetException { 
        UniversityStudent uniStudent = null;
        try {
            uniStudent = new UniversityStudent( student.getForename(), student.getSurname(), student.getStage() );  
        }
        catch( InvalidStageException e ) {
            e.printStackTrace();
        }
        
        if( student.getID() == null ) {
            try {
                String studentID = generateStudentID();
                uniStudent.setID( studentID );
                student.setID( studentID );
            }
            catch( InvalidIDException e ) {
                e.printStackTrace();
            }
        }
        else {
            throw new IDAlreadySetException( "Student already has ID: " + student.getID() + " and so cannot be added to the system." );
        }
        
        binaryInsertStudent( uniStudent );
    }
    
    
    /**
        * Generate a random student ID
        * 
        * @returns random student ID
     */
    private String generateStudentID() {
        String num1 = Integer.toString((int)(Math.random() * 99999 + 0));
        String num2 = Integer.toString((int)(Math.random() * 99999 + 1));
        String ID = num1 + num2;
        
        int zeros = 10 - ID.length();
        while( zeros > 0 ) {
            ID = "0" + ID;
            zeros--;
        }
        
        if( binarySearchStudentID(ID) != null ) {
            //try again if the random ID generated was already an existent in manager
            return generateStudentID();
        }
        
        assert ID != null;
        return ID;
    }
    
    /**
        * @inheritDoc
     */
    public String addModule(String name, byte credits, byte stage, int capacity, Staff[] staff) 
    throws InvalidStageException, InvalidCreditsException, InvalidCapacityException, 
    DuplicateStaffException, StaffNotInSystemException { 
   
        if( capacity < 1 ) {
            throw new InvalidCapacityException( "Attempted to add module " + name + " with invalid capacity less than 1. " );
        }
        for( int i=0; i<staff.length; i++ ) {
            for( int j=i+1; j<staff.length; j++ ) {
                if( staff[i].equals(staff[j]) ) {
                    assert i != j;
                    throw new DuplicateStaffException( "Duplicate staff in array, therefore cannot add to module" );
                }
            }
        }


        UniversityModule module = null;
        String moduleCode = generateModuleCode();
         
        for( Staff staffToAdd : staff ) {
            if( binarySearchStaffID(staffToAdd.getID()) == null )  {
                throw new StaffNotInSystemException( "Attempted to add staff member '" + staffToAdd.getForename() + " " + staffToAdd.getSurname() + 
                                                     "' who does not exist in the system onto module: " + name );
            }
        }
        
        try {
            module = new UniversityModule( name, credits, stage, capacity, moduleCode );
        }
        catch( InvalidIDException e ) {
            e.printStackTrace();
        }
        catch( IDAlreadySetException e ) {
            e.printStackTrace();
        }
        
        
        for( Staff staffToAdd : staff ) {
            UniversityStaff staffInSystem = binarySearchStaffID( staffToAdd.getID() );
            try {
                module.addStaff( staffInSystem );
            }
            catch( IDNotSetException e ) {
                e.printStackTrace(); 
            }
            catch( ModuleDiscontinuedException e ) {
                e.printStackTrace();
            }
            staffInSystem.addTeachingModule( module );
        }
                
        binaryInsertModule( module );
        return module.getCode();
    }
    
    /**
        * @inheritDoc
     */
    public void addModule(Module module) throws IDAlreadySetException { 
        UniversityModule uniModule = null;
        try {
            uniModule = new UniversityModule( module.getName(), module.getCredits(), module.getStage(), module.getCapacity() );  
        }
        catch( InvalidStageException e ) {
            e.printStackTrace();
        }
        catch( InvalidCreditsException e ) {
            e.printStackTrace();
        }
        
        if( module.getCode() == null ) {
            try {
                String moduleCode = generateModuleCode();
                uniModule.setCode( moduleCode );
                module.setCode( moduleCode );
            }
            catch( InvalidIDException e ) {
                e.printStackTrace();
            }
        }
        else {
            throw new IDAlreadySetException( "Module already has code: " + module.getCode() + " and so cannot be added to the system." );
        }
        
        binaryInsertModule( uniModule );
    }
    
    /**
        * Generate a random module code
        * 
        * @returns random module code
     */
    public String generateModuleCode() {
        String code = Integer.toString((int)(Math.random() * 99999 + 1));
        
        int zeros = 5 - code.length();
        while( zeros > 0 ) {
            code = "0" + code;
            zeros--;
        }
        
        if( binarySearchModuleCode(code) != null ) {
            //try again
            return generateModuleCode();
        }
        
        assert code != null;
        return code;
    }
    
    /**
        * @inheritDoc
     */
    public String addStaff(String forename, String surname) { 
        UniversityStaff staff = new UniversityStaff( forename, surname );
        
        String staffID = generateStaffID();
        try {
            staff.setID( staffID );
        }
        catch( InvalidIDException e ) {
            e.printStackTrace();
        }
        catch( IDAlreadySetException e ) {
            e.printStackTrace();
        }

        binaryInsertStaff( staff );
        return staff.getID();
    }
    
    /**
        * @inheritDoc
     */
    public void addStaff(Staff staff) throws IDAlreadySetException { 
            
        UniversityStaff uniStaff = new UniversityStaff( staff.getForename(), staff.getSurname() );
        
        if( staff.getID() == null ) {
            try {
                String staffID = generateStaffID();
                uniStaff.setID( staffID );
                staff.setID( staffID );
            }
            catch( InvalidIDException e ) {
                e.printStackTrace();
            }
        }
        else {
            throw new IDAlreadySetException( "Staff member already has ID: " + staff.getID() + " and so cannot be added to the system." );
        }
        
        binaryInsertStaff( uniStaff );
    }
    
    /**
        * Generate a random staff ID
        * 
        * @returns random staff ID
     */
    public String generateStaffID(){
        String ID = Integer.toHexString((int)(Math.random() * 1048575 + 0));
        
        int zeros = 5 - ID.length();
        while( zeros > 0 ) {
            ID = "0" + ID;
            zeros--;
        }
        
        if( binarySearchStaffID(ID) != null ){
            //try again
            return generateStaffID();
        }
        
        assert ID != null;
        return ID;
    }
    
    /**
        * @inheritDoc
     */
    public void discontinue(String moduleCode) throws InvalidIDException, 
    IDNotRecognisedException {
        UniversityModule.checkValidCode(moduleCode);
        UniversityModule module = binarySearchModuleCode(moduleCode);
        
        if( module == null ) {
            throw new IDNotRecognisedException( "Module code: " + moduleCode + " not found in the system." );
        }
        Student[] students = module.getStudents();
        
        for( int i=0; i<students.length; i++ ) {
            UniversityStudent student = ( (UniversityStudent) students[i] );
            student.removeModule( module );
        }   
        
        Staff[] staff = module.getTeachingStaff();
        for( Staff staffMember : staff ) {
            ( (UniversityStaff) staffMember ).removeTeachingModule( module );
        }
        module.discontinue();
    }
    
    /**
        * @inheritDoc
     */
    public void enrol(String studentID, String moduleCode) throws InvalidIDException, 
    IDNotRecognisedException, ModuleAtCapacityException, 
    InsufficientAvailableCreditsException, ModuleDiscontinuedException,
    ModuleStageTooHighException, EnrollingWouldPreventHonoursException { 
        
        UniversityStudent.checkValidID( studentID );
        UniversityModule.checkValidCode( moduleCode );

        UniversityStudent student = binarySearchStudentID(studentID);
        UniversityModule module = binarySearchModuleCode(moduleCode);
        
        if( student == null || module == null ) {
            throw new IDNotRecognisedException( "The student ID : " + studentID + " or the module code: " + moduleCode + 
            " does not reference a student or module on the system.");
        }
        
        if( module.isAtCapacity() ) {
            throw new ModuleAtCapacityException( "The module with code: " + moduleCode + " is at capacity and cannot enrol students" );
        }
        
        if( student.getTotalCredits() + module.getCredits() > 120 ) {
            throw new InsufficientAvailableCreditsException( "The student with ID: " + studentID + " does not have enough credits left to enrol onto module with code : " 
            + moduleCode );
        }
        
        if( module.isDiscontinued() ) {
            throw new ModuleDiscontinuedException( "The module with code: " + moduleCode + " is discontinued and so cannot enrol students" );
        }
        
        if( module.getStage() > student.getStage() ) {
            throw new ModuleStageTooHighException( "The module with code: " + moduleCode + " is of a higher stage than student with ID: " + studentID );
        }
    
        if( module.getStage() != student.getStage() ) {
            if( module.getCredits() + student.getLowerStageCredits() > 30 ) {
                throw new EnrollingWouldPreventHonoursException( "Enrolling student with ID: " + studentID + " to module with code: " + moduleCode
                + "would give the student more than 30 credits at a lower stage, preventing honours.");
            }
        }
        
        student.assignModule( module );
        module.addStudent( student );
    }
    
    /**
        * @inheritDoc
     */
    public void loadAllocationManager(String filename) throws IOException, 
    ClassNotFoundException { 
        UniversityAllocationManager manager = null ;
        FileInputStream fileInput = null ;
        ObjectInputStream objectInput = null ;
        fileInput = new FileInputStream ( filename );
        objectInput = new ObjectInputStream ( fileInput );
        manager = (UniversityAllocationManager)objectInput.readObject();
        objectInput.close ();
    }

    /**
        * @inheritDoc
     */
    public int getNumberOfStaff() { 
        return staff.size();
    }
    
    /**
        * @inheritDoc
     */
    public int getNumberOfStudents() { 
        return students.size();
    }
    
    /**
        * @inheritDoc
     */
    public int getNumberOfModules() { 
        return modules.size();
    }
    
    /**
        * @inheritDoc
     */
    public Staff[] getStaff()  {
        Staff[] staff = new Staff[this.staff.size()];
        
        for( int i=0; i<this.staff.size(); i++ ) {
            staff[i] = (Staff)this.staff.get(i);
        }
        return staff;
    }
    
    /**
        * @inheritDoc
     */
    public Staff[] getStaff(String moduleCode) throws InvalidIDException, 
    IDNotRecognisedException  { 
        
        UniversityModule.checkValidCode( moduleCode );
        UniversityModule module = binarySearchModuleCode(moduleCode);
        
        if( module == null ) {
            throw new IDNotRecognisedException( "The module code " + moduleCode + " does not exist on the system.");
        }
        return module.getTeachingStaff();
    }
    
    /**
        * @inheritDoc
     */
    public Student[] getStudents() { 
        Student[] students = new Student[this.students.size()];
        
        for( int i=0; i<this.students.size(); i++ ) {
            students[i] = (Student)this.students.get(i);
        }
        return students;
    }
    
    /**
        * @inheritDoc
     */
    public Student[] getStudents(String moduleCode) throws InvalidIDException, 
    IDNotRecognisedException { 
        
        UniversityModule.checkValidCode( moduleCode );
        UniversityModule module = binarySearchModuleCode(moduleCode);
        
        if( module == null ) {
            throw new IDNotRecognisedException( "The module code " + moduleCode + " does not exist on the system.");
        }
        return module.getStudents();
    }
    
    /**
        * @inheritDoc
     */
    public Module[] getModules() { 
        Module[] modules = new Module[this.modules.size()];
        
        for( int i=0; i<this.modules.size(); i++ ) {
            modules[i] = (Module)this.modules.get(i);
        }
        return modules;
    }
    
    /**
        * @inheritDoc
     */
    public Module[] getRunningModules() { 
    
        ObjectArrayList modules = new ObjectArrayList();
        
        for( int i=0; i<this.modules.size(); i++ ) {
            Module module = (Module)this.modules.get(i);
            
            if( !module.isDiscontinued() ) {
                modules.add( module );
            }
        }
        Module[] runningModules = new Module[modules.size()];
        
        for( int i=0; i<modules.size(); i++ ) {
            runningModules[i] = (Module)modules.get(i);
        }
        return runningModules;
    }
    
    /**
        * @inheritDoc
     */
    public Module[] getAvailableModules()  { 
        ObjectArrayList modules = new ObjectArrayList();
        
        for( int i=0; i<this.modules.size(); i++ ) {
            UniversityModule module = (UniversityModule)this.modules.get(i);
            
            if( !module.isDiscontinued() && !module.isAtCapacity() ) {
                modules.add( module );
            }
        }
        Module[] avaliableModules = new Module[modules.size()];
        
        for( int i=0; i<modules.size(); i++ ) {
            avaliableModules[i] = (Module)modules.get(i);
        }
        return avaliableModules;
    }
    
    /**
        * @inheritDoc
     */
    public Module[] getModules(String studentID) throws InvalidIDException, 
    IDNotRecognisedException  { 
        
        UniversityStudent.checkValidID( studentID );
        UniversityStudent student = binarySearchStudentID(studentID);
        
        if( student == null ) {
            throw new IDNotRecognisedException( "The student ID : " + studentID + " does not exist on the system.");
        }
        
        return student.getEnrolledModules();
    }
   
   /**
        * @inheritDoc
     */
    public int getNumberOfFullyAllocatedStudents()  { 
        int numberFullStudents = 0;
        
        for( int i=0; i<students.size(); i++ ) {
            UniversityStudent student = (UniversityStudent)students.get(i);
            
            if( student.getTotalCredits() == 120 ) {
                numberFullStudents++;
            }
        }
        return numberFullStudents;
    }
    
    /**
        * @inheritDoc
     */
    public int getNumberOfModulesAtCapacity() { 
        int numberFullModules = 0;
        
        for( int i=0; i<modules.size(); i++ ) {
            UniversityModule module = (UniversityModule)modules.get(i);
            
            if( module.isAtCapacity() ) {
                numberFullModules++;
            }
        }
        return numberFullModules;
    }
    
    
    /**
        * @inheritDoc
     */
    public Module[] remove(Staff staff) throws InvalidIDException, 
    IDNotRecognisedException, IDNotSetException { 
        
        if( staff.getID() == null ) {
            throw new IDNotSetException( "Staff member with name " + staff.getForename() + staff.getSurname() + " has no ID set and so cannot be removed." );
        }
        UniversityStaff.checkValidID( staff.getID() );
        
        UniversityStaff staffToRemove = binarySearchStaffID( staff.getID() );
        ObjectArrayList nowNoStaff = new ObjectArrayList();
      
        for( Module module : staffToRemove.getTeachingModules() ) {
            try {
                module.removeStaff( staffToRemove );
            }
            catch( StaffNotInvolvedException e ) {
                e.printStackTrace();
            }
            
            if( ((UniversityModule)module).getTeachingStaff().length == 0 ) {
                nowNoStaff.add( module );
            }
        }
        Module[] noStaff = new Module[nowNoStaff.size()];
            
        for( int i=0; i<nowNoStaff.size(); i++ ) {
            noStaff[i] = (Module)nowNoStaff.get(i);
        }
        this.staff.remove( staffToRemove );
        return noStaff;
    }
    
    /**
        * @inheritDoc
     */
    public void remove(Student student) throws InvalidIDException, 
    IDNotRecognisedException, IDNotSetException { 
        
        if( student.getID() == null ) {
            throw new IDNotSetException( "Student with name " + student.getForename() + student.getSurname() + " has no ID set, and so cannot be removed." );
        }
        
        UniversityStudent studentToRemove = binarySearchStudentID( student.getID() );
        if( studentToRemove == null ) {
            throw new IDNotRecognisedException( "The student ID : " + studentToRemove.getID() + " does not exist on the system." );
        }
        
        for( Module module : studentToRemove.getEnrolledModules() ) {
            unEnrol( studentToRemove.getID(), module.getCode() );
        }
        
        students.remove( studentToRemove );
    }
    
    /**
        * @inheritDoc
     */
    public void saveAllocationManager(String filename) throws IOException {
        FileOutputStream fileOutput = null;
        ObjectOutputStream objectOutput = null;
        fileOutput = new FileOutputStream(filename);
        objectOutput = new ObjectOutputStream(fileOutput);
        objectOutput.writeObject(this);
        objectOutput.close ();
    }
    
    /**
        * @inheritDoc
     */
    public boolean unEnrol(String studentID, String moduleCode) throws 
    InvalidIDException, IDNotRecognisedException { 
        
        UniversityStudent.checkValidID(studentID);
        UniversityModule.checkValidCode(moduleCode);
        
        UniversityStudent student = binarySearchStudentID(studentID);
        UniversityModule module = binarySearchModuleCode(moduleCode);
        
        if( student == null || module == null ) {
            throw new IDNotRecognisedException( "The student ID : " + studentID + " or the module code: " + moduleCode + 
            " does not reference a student or module on the system.");
        }
        
        for( Module enrolledModule : student.getEnrolledModules() ) {
            
            if( enrolledModule.getCode().equals(module.getCode()) ) {
                ((UniversityModule)enrolledModule).removeStudent( student );
                student.removeModule( (UniversityModule)enrolledModule );
                return true;
            }
        }
        return false;
    }
}
