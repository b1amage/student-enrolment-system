import model.Course;
import model.Student;
import system.StudentEnrolmentSystem;
import utility.date.DateConverter;
import utility.display.Table;


import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;


public class Main {

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        StudentEnrolmentSystem system = new StudentEnrolmentSystem();
        system.populateData();

        // Test get all
        Table.displayStudentTable(system.getAllStudent());
        Table.displayCourseTable(system.getAllCourses());
        Table.displayEnrolmentTable(system.getAllEnrolments());

//        System.out.println(system.getAllCourses());
//        System.out.println(system.getAllEnrolments());

        // Test get one
//        System.out.println(system.getCourseById("COSC3321"));
//        System.out.println(system.getStudentById("S103723"));
//        System.out.println(system.getOneEnrolment("S101312", "COSC4030", "2020C"));

        // Test add
//        system.addStudent(new Student("s3877698", "Nguyen Luu Quoc Bao", new Date("08/10/2002")));
//        system.addStudent(new Student("s3877698", "Nguyen Luu Quoc Bao", new Date("08/10/2002")));
//        System.out.println(system.getAllStudent());
//
//        system.addCourse(new Course("COSC2539", "Web programming", 12));
//        system.addCourse(new Course("COSC2539", "Web programming", 12));
//        System.out.println(system.getAllCourses());
//
//        system.addEnrolment("s3877698", "COSC2539", "2020A");
//        system.removeEnrolment("S101312", "COSC4030", "2020C");
//        System.out.println(system.getAllEnrolments());
    }




}
