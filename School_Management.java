class Student
{
    int ID;
    String name;
    int age;
    String program;
    String [] courses;

    Student(int ID,String name,int age,String program,String [] course)
    {
        this.ID=ID;
        this.name=name;
        this.age=age;
        this.program=program;
        this.courses=courses;
    }

    void showStudent()
    {
        System.out.println("===========STUDENT DATA======");
        System.out.printf("%-50s : %s15","The name of the Student is ",this.name); 
    }
}
public class School_Management
{
    public static void main(String[] args) 
    {
        
    }
}