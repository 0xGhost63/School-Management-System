import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

//Parent Person class
abstract class Person
{
    private String name;
    private int age;
    private String ID;
    private String program;

    public Person(String ID,String name,int age,String program) 
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
            if ((data instanceof String && s.getName().equals(data)) || (data instanceof String && s.getID().equals(data)))
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
    
 static void removePerson(ArrayList<Person> persons, ArrayList<Student> students, ArrayList<Lecturer> lecturers, Scanner sc)
{
    System.out.print("Enter the ID of the Student/Lecturer to remove: ");
    String id = sc.nextLine().trim();

    boolean removed = false;

    Iterator<Person> it = persons.iterator();
    while (it.hasNext())
    {
        Person p = it.next();
        if (p.getID().equalsIgnoreCase(id))
        {
            it.remove();
            if (p instanceof Student) students.remove(p);
            if (p instanceof Lecturer) lecturers.remove(p);
            System.out.println("Removed successfully:");
            p.showData();
            removed = true;
            break;
        }
    }

    if (!removed)
        System.out.println("No Student/Lecturer found with ID: " + id);
}

// Edit Student Fee / Lecturer Salary
static void editPerson(ArrayList<Student> students, ArrayList<Lecturer> lecturers, Scanner sc)
{
    System.out.println("Edit: 1-Student Fee  2-Lecturer Salary");
    String choice = sc.nextLine().trim();

    if (choice.equals("1"))
    {
        System.out.print("Enter Student ID to edit fee (or 'ALL' for all students): ");
        String id = sc.nextLine().trim();
        System.out.print("Enter new fee: ");
        double fee = Double.parseDouble(sc.nextLine());

        if (id.equalsIgnoreCase("ALL"))
        {
            Student.EditFeeForAll(students, fee);
        }
        else
        {
            boolean found = false;
            for (Student s : students)
            {
                if (s.getID().equalsIgnoreCase(id))
                {
                    s.changeFee(fee);
                    System.out.println("Updated fee for:");
                    s.showData();
                    found = true;
                    break;
                }
            }
            if (!found) System.out.println("Student not found with ID: " + id);
        }
    }
    else if (choice.equals("2"))
    {
        System.out.print("Enter Lecturer ID to edit salary (or 'ALL' for all lecturers): ");
        String id = sc.nextLine().trim();
        System.out.print("Enter new salary: ");
        double salary = Double.parseDouble(sc.nextLine());

        if (id.equalsIgnoreCase("ALL"))
        {
            Lecturer.EditSalaryForAll(lecturers, salary);
        }
        else
        {
            boolean found = false;
            for (Lecturer l : lecturers)
            {
                if (l.getID().equalsIgnoreCase(id))
                {
                    l.changeSalary(salary);
                    System.out.println("Updated salary for:");
                    l.showData();
                    found = true;
                    break;
                }
            }
            if (!found) System.out.println("Lecturer not found with ID: " + id);
        }
    }
    else
    {
        System.out.println("Invalid choice!");
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
    public String getID() {
        return ID;
    }
    public void setID(String iD) {
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

    Student(String ID,String name,int age,String program,ArrayList <String> courses,double fee)
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
    public Lecturer(String ID,String name,int age,String program,double salary,HashMap<String,String> classesTaught) 
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

 public static void writeData(ArrayList<Person> persons) throws IOException
    {
        Path studentPath = Paths.get("students.csv");
        Path lecturerPath = Paths.get("lecturers.csv");
    
        List<String> studentRows = new ArrayList<>();
        List<String> lecturerRows = new ArrayList<>();
    
        // Headers
        studentRows.add("ID,NAME,AGE,PROGRAM,FEE,COURSES");
        lecturerRows.add("ID,NAME,AGE,PROGRAM,SALARY,CLASSES");
    
        for (Person p : persons)
        {
            if (p instanceof Student)
            {
                Student s = (Student) p;
            
                String courses = String.join("|", s.getCourses());
            
                studentRows.add(
                    s.getID() + "," +
                    s.getName() + "," +
                    s.getAge() + "," +
                    s.getProgram() + "," +
                    s.getFee() + "," +
                    courses
                );
            }
            else if (p instanceof Lecturer)
            {
                Lecturer l = (Lecturer) p;
            
                StringBuilder classes = new StringBuilder();
                for (Map.Entry<String, String> entry : l.getClassesTaught().entrySet())
                {
                    classes.append(entry.getKey())
                           .append(":")
                           .append(entry.getValue())
                           .append("|");
                }
            
                lecturerRows.add(
                    l.getID() + "," +
                    l.getName() + "," +
                    l.getAge() + "," +
                    l.getProgram() + "," +
                    l.getSalary() + "," +
                    classes
                );
            }
        }
    
        Files.write(
            studentPath,
            studentRows,
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING
        );
    
        Files.write(
            lecturerPath,
            lecturerRows,
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING
        );
    }

    // --------- New Method to Check Duplicate IDs ---------
    public static boolean isIDTaken(String id, ArrayList<Person> persons)
    {
        for (Person p : persons)
        {
            if (p.getID().equalsIgnoreCase(id))
                return true;
        }
        return false;
    }

 public static void readData(ArrayList<Student> students,ArrayList<Lecturer> lecturers,ArrayList<Person> persons) throws IOException
    {
        // ---------- READ STUDENTS ----------
        Path studentPath = Paths.get("students.csv");

        if (Files.exists(studentPath))
        {
            List<String> lines = Files.readAllLines(studentPath);

            for (int i = 1; i < lines.size(); i++) // skip header
            {
                String[] data = lines.get(i).split(",");

                String id = data[0];
                String name = data[1];
                int age = Integer.parseInt(data[2]);
                String program = data[3];
                double fee = Double.parseDouble(data[4]);

                ArrayList<String> courses = new ArrayList<>();
                if (data.length > 5 && !data[5].isEmpty())
                {
                    courses.addAll(Arrays.asList(data[5].split("\\|")));
                }

                Person p = new Student(id, name, age, program, courses, fee);

                if (p instanceof Student)
                {
                    students.add((Student) p);
                    persons.add(p);
                }
            }
        }

        // ---------- READ LECTURERS ----------
        Path lecturerPath = Paths.get("lecturers.csv");

        if (Files.exists(lecturerPath))
        {
            List<String> lines = Files.readAllLines(lecturerPath);

            for (int i = 1; i < lines.size(); i++) // skip header
            {
                String[] data = lines.get(i).split(",");

                String id = data[0];
                String name = data[1];
                int age = Integer.parseInt(data[2]);
                String program = data[3];
                double salary = Double.parseDouble(data[4]);

                HashMap<String, String> classes = new HashMap<>();
                if (data.length > 5 && !data[5].isEmpty())
                {
                    String[] entries = data[5].split("\\|");
                    for (String entry : entries)
                    {
                        String[] pair = entry.split(":");
                        classes.put(pair[0], pair[1]);
                    }
                }

                Person p = new Lecturer(id, name, age, program, salary, classes);

                if (p instanceof Lecturer)
                {
                    lecturers.add((Lecturer) p);
                    persons.add(p);
                }
            }
        }
    }

 public static void printWithTimestamp(String msg)
    {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm MM/dd/yyyy");
        String timestamp = now.format(formatter);

        System.out.println("[" + timestamp + "] " + msg);

        try {
            Thread.sleep(500); // small pause for effect
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // ------------------- Main Method -------------------
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        ArrayList<Person> persons = new ArrayList<>();

        // Load existing data
        try
        {
            printWithTimestamp("Loading data...");
            readData(students, lecturers, persons);
            printWithTimestamp("Data loaded successfully!");
        }
        catch (IOException e)
        {
            printWithTimestamp("Error reading data files. Starting with empty data.");
        }

        // Login
        printWithTimestamp("Signing in...");
        System.out.println("Are you Admin (A) or User (U)?");
        String userType = sc.nextLine().trim().toUpperCase();

        boolean isAdmin = false;
        if (userType.equals("A"))
        {
            System.out.print("Enter Admin password: ");
            int pass = 0;
            try
            {
                pass = Integer.parseInt(sc.nextLine());
            }
            catch (NumberFormatException e)
            {
                printWithTimestamp("Invalid input! Login failed. You are now in view-only mode.");
            }

            if (pass == 6363)
            {
                isAdmin = true;
                printWithTimestamp("Admin login successful!");
            }
            else
            {
                printWithTimestamp("Wrong password! You are now in view-only mode.");
            }
        }
        else
        {
            printWithTimestamp("User login - view only mode.");
        }

        boolean exit = false;

        // ------------------- Main Menu Loop -------------------
        while (!exit)
        {
            System.out.println("\n================ MENU ================");
            System.out.println("1. Show all Persons");
            if (isAdmin)
            {
                System.out.println("2. Add Student/Lecturer");
                System.out.println("3. Remove Student/Lecturer");
                System.out.println("4. Edit Student Fee / Lecturer Salary");
            }
            System.out.println("0. Exit");
            System.out.println("=====================================");
            System.out.print("Enter choice: ");

            String choice = sc.nextLine().trim();

            switch (choice)
            {
                case "1":
                    Person.ShowDataForAll(persons);
                    break;

                case "2":
                    if (!isAdmin)
                    {
                        printWithTimestamp("Access denied. Only Admin can add.");
                        break;
                    }
                    addPersonMenu(sc, persons, students, lecturers);
                    break;

                case "3":
                    if (!isAdmin)
                    {
                        printWithTimestamp("Access denied. Only Admin can remove.");
                        break;
                    }
                    Person.removePerson(persons, students, lecturers, sc);
                    break;
                
                case "4":
                    if (!isAdmin)
                    {
                        printWithTimestamp("Access denied. Only Admin can edit.");
                        break;
                    }
                    Person.editPerson(students, lecturers, sc);
                    break;
                

                case "0":
                        printWithTimestamp("Signing out...");
                        try
                        {
                            printWithTimestamp("Backing up data...");
                            writeData(persons);
                            printWithTimestamp("Data saved successfully!");
                        }
                        catch (IOException e)
                        {
                            printWithTimestamp("Error saving data!");
                        }
                        exit = true;
                        break;

                default:
                    printWithTimestamp("Invalid choice. Try again.");
            }
        }

        sc.close();
    }

    // ------------------- Add Person -------------------
    private static void addPersonMenu(Scanner sc, ArrayList<Person> persons,
                                      ArrayList<Student> students, ArrayList<Lecturer> lecturers)
    {
        System.out.println("Add a Student or Lecturer? (S/L): ");
        String type = sc.nextLine().trim().toUpperCase();

        try
        {
            String id;
            while(true)
            {
                System.out.print("ID (" + (type.equals("S") ? "STU-xxxx" : "LECT-xxx") + "): ");
                id = sc.nextLine().trim().toUpperCase();
                if(!isIDTaken(id, persons)) break;
                printWithTimestamp("This ID already exists. Enter a different one.");
            }

            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Age: ");
            int age = Integer.parseInt(sc.nextLine());
            System.out.print("Program: ");
            String program = sc.nextLine();

            if (type.equals("S"))
            {
                System.out.print("Fee: ");
                double fee = Double.parseDouble(sc.nextLine());
                System.out.print("Courses (comma-separated): ");
                String[] courseArr = sc.nextLine().split(",");
                ArrayList<String> courses = new ArrayList<>();
                for (String c : courseArr) courses.add(c.trim());

                printWithTimestamp("Adding new student...");
                Student s = new Student(id, name, age, program, courses, fee);
                students.add(s);
                persons.add(s);
                printWithTimestamp("Student added successfully!");
            }
            else if (type.equals("L"))
            {
                System.out.print("Salary: ");
                double salary = Double.parseDouble(sc.nextLine());
                System.out.print("Number of classes taught: ");
                int n = Integer.parseInt(sc.nextLine());
                HashMap<String, String> classes = new HashMap<>();
                for (int i = 0; i < n; i++)
                {
                    System.out.print("Class name: ");
                    String cname = sc.nextLine();
                    System.out.print("Time: ");
                    String time = sc.nextLine();
                    classes.put(cname, time);
                }

                printWithTimestamp("Adding new lecturer...");
                Lecturer l = new Lecturer(id, name, age, program, salary, classes);
                lecturers.add(l);
                persons.add(l);
                printWithTimestamp("Lecturer added successfully!");
            }
            else
            {
                printWithTimestamp("Invalid type!");
            }
        }
        catch (NumberFormatException e)
        {
            printWithTimestamp("Invalid number entered. Aborting add operation.");
        }
    }
}
