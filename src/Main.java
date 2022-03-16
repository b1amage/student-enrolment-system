import menu.Menu;
import menu.StudentMenu;
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
//        StudentEnrolmentSystem system = new StudentEnrolmentSystem();
//        system.populateData();
//
//        Table.displayStudentTable(system.getAllStudent());
//        Table.displayCourseTable(system.getAllCourses());
//        Table.displayEnrolmentTable(system.getAllEnrolments());

        Menu sMenu = new StudentMenu();
        sMenu.showMenu();

    }




}
