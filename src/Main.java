import system.StudentEnrolmentSystem;


import java.io.FileNotFoundException;
import java.text.ParseException;


public class Main {

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        StudentEnrolmentSystem system = new StudentEnrolmentSystem();
        system.populateData();

        System.out.println(system.getAllStudent());
        System.out.println(system.getAllCourses());
        System.out.println(system.getAllEnrolments());
    }
}
