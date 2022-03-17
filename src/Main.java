import menu.*;
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

//        StudentMenu sMenu = new StudentMenu(system);
//        sMenu.viewAllStudent();
//        sMenu.viewAllStudentInACourseInASemester();

//        CourseMenu cMenu = new CourseMenu(system);
//        cMenu.viewAllCourses();
//        cMenu.viewAllCoursesInASemester();
//        cMenu.viewAllCourseOfAStudentInASemester();

        EnrolmentMenu eMenu = new EnrolmentMenu(system);
        eMenu.viewAllEnrolments();
//        eMenu.addEnrolment();
//        eMenu.viewAllEnrolments();

        eMenu.dropEnrolment();
        eMenu.viewAllEnrolments();

    }




}
