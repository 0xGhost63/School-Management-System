import java.util.*;

//Parent Person class
abstract class Person
{
    private String name;
    private int age;
    private int ID;
    private String program;

    public Person(int ID,String name,int age,String program) 
    {
        this.ID=ID;
        this.name=name;
        this.age=age;
        this.program=program;
    }

    abstract void showData();

    //Method to search the Student or Lecuter
    static <T> void  SearchStudentorLecturer(ArrayList<Person> persons,T data) 
    {
        boolean isFound=false;
        for (Person s : persons)
        {
            if ((data instanceof String && s.getName().equals(data)) || (data instanceof Integer && s.getID() == (Integer) data))
            {
                s.showData();
                isFound = true;
            }
        }

        if(!isFound)
        {
            System.out.printf("No Person found by the data of the \"%s\" !",data);
        }
        
    }
    
    //Method to remove the Student
    static <T>void RemoveStudent(ArrayList<Person>persons,T data)
    {
        System.out.println("The available Pupils/Tutors are : ");
        SearchStudentorLecturer(persons, data);
        System.out.println("Enter the ID of the Pupil/Tutor to re-confirm : ");
        int ID=0;
        boolean isFound=false;

        for (Person s : persons)
        {
            if (s.getID() == ID)
            {
                System.out.println("SuccesFully Removed the Pupil/Tutor : ");
                s.showData();
                isFound = true;
            }
        }
        if(!isFound)
        {
            System.out.printf("Invalid ID (%d) entered , no Pupils/Tutors exists with this ID !",ID);
        }
    }

    //Method to add the Student/Lecuter
    static void addStudentorLecturer(ArrayList<Person>persons,Person person)
    {
        if (person instanceof Person)
        {
            persons.add(person);
            System.out.println("Successfully added !");
        }
        else
        {
            System.out.println("Object doesn't hold the valid attributes of the C");
        }
    }

    //Show All Students/Lectrers
    static void ShowDataForAll(ArrayList<Person>persons)
    {
        System.out.println("All enrollments are : ");
        for (Person s : persons)
        {
            s.showData();
        }
    }

    //Getter Setters
        public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public String getProgram() {
        return program;
    }
    public void setProgram(String program) {
        this.program = program;
    }
}


//Student Class 
class Student extends Person
{

    private ArrayList <String> courses;
    private double fee;
    static int TotalStudents=0;

    Student(int ID,String name,int age,String program,ArrayList <String> courses,double fee)
    {
        super(ID, name, age, program);
        this.courses=courses;
        this.fee=fee;
        TotalStudents++;
    }

    //Method to add a course
    void enrollCourse(String course)
    {
        this.courses.add(course);
        System.out.println("Successfully Added the course ! The updated course list is : ");
        coursesList();
    }

    //Method to drop a course
    void dropCourse(String course)
    {
        if(this.courses.remove(course))
        {
            System.out.println("Succesfully Dropped the course , the updated course list is : ");
            coursesList();
        }
        else
        {
            System.out.printf("No course by the name of \"%s\" exists\n",course);
        }
        
    }

    //Method to print the data of the Student
    @Override
    void showData()
    {
        System.out.println("=========== STUDENT DATA ===========");
        System.out.printf("%-25s : %s%n", "Student ID", this.getID());
        System.out.printf("%-25s : %s%n", "Name", this.getName());
        System.out.printf("%-25s : %s%n", "Age", this.getAge());
        System.out.printf("%-25s : %s%n", "Fee", this.getFee());
        System.out.printf("%-25s : %s%n", "Program", this.getProgram());

        System.out.println("Enrolled Courses:");
        coursesList();
        System.out.println("===================================");
    }

    //Method to print the enrolled courses of a Student
    void coursesList()
    {
        for (int i = 0; i < courses.size(); i++)
        {
            System.out.printf("  %d - %s%n", i + 1, courses.get(i));
        }
    }

    //Edit the fee 
    void changeFee(double amount)
    {
        this.setFee(amount);
    }

    //edit the fee for all 
    static void EditFeeForAll(ArrayList<Student>students,double amount)
    {
        for (Student s : students)
        {
            s.changeFee(amount);
        }
        System.out.printf("Successfully updated the fee for all Students to %f",amount);
    }

    //Getters and Setters
    
    public ArrayList<String> getCourses() {
        return courses;
    }
    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }
    public double getFee() {
        return fee;
    }
    public void setFee(double fee) {
        this.fee = fee;
    }
    
}

class Lecturer extends Person
{
    private double salary;
    private HashMap<String,String> classesTaught;
    public Lecturer(int ID,String name,int age,String program,double salary,HashMap<String,String> classesTaught) 
    {
        super(ID, name, age, program);
        this.salary=salary;
        this.classesTaught=classesTaught;
    }


    //Method to print the data of the Student
    @Override
    void showData()
    {
        System.out.println("=========== Lecturer DATA ===========");
        System.out.printf("%-25s : %s%n", "Lecturer ID", this.getID());
        System.out.printf("%-25s : %s%n", "Name", this.getName());
        System.out.printf("%-25s : %s%n", "Age", this.getAge());
        System.out.printf("%-25s : %s%n", "Salary", this.getSalary());
        System.out.printf("%-25s : %s%n", "Program", this.getProgram());
        System.out.println("Classes Taught : ");
        showClassesTaught();
        System.out.println("===================================");
    }
    
    //Method to print the Classes Taught 
    void showClassesTaught()
    {
        int count = 1;
        for (Map.Entry<String, String> entry : classesTaught.entrySet())
        {
            String className = entry.getKey();
            String time = entry.getValue(); // depending on what value represents
            System.out.printf("  %d - %s : %s%n", count, className, time);
            count++;
        }
    }

    //Edit the fee 
    void changeSalary(double amount)
    {
        this.setSalary(amount);
    }

    //edit the fee for all 

    static void EditSalaryForAll(ArrayList<Lecturer>lecturers,double amount)
    {
        for (Lecturer l : lecturers)
        {
            l.changeSalary(amount);
        }
        System.out.printf("Successfully updated the Salary for all Lecturers to %f",amount);
    }
    
    //Getters Setters

    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public HashMap<String, String> getClassesTaught() {
        return classesTaught;
    }
    public void setClassesTaught(HashMap<String, String> classesTaught) {
        this.classesTaught = classesTaught;
    }
}

class Admin
{
    private String name;
    private final int PASS;

    static private Admin instance=null;

    private  Admin() 
    {
        this.name="0xGhost";
        this.PASS=6363;   
    }

    public static Admin getInstance()
    {
        if (instance == null) 
        {
            instance = new Admin();
        }
        return instance; 
    }
    
}
//Main Class
public class School_Management
{
    public static void main(String[] args) 
    {
        ArrayList<String> arr  = new ArrayList<>();
        arr.add("Bio");
        arr.add("Chm");
        arr.add("Mathematics");
        arr.add("Phy");
        Student s1 = new Student(0, "Hehehehe", 0, null, arr,2005);
        // s1.showStudent();
        // System.out.println("The length of the array is : "+arr.length);
        // s1.dropCourse("Biology");
        // s1.dropCourse("Bio");
        // s1.enrollCourse("Bio");
        System.out.println(s1.getName());
    }

}