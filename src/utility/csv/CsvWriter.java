package utility.csv;

import model.Course;
import model.Student;
import utility.date.DateConverter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Class name: CsvWriter
 * Role: Utility class
 * Functionalities: write list of object to a csv file
 * */
public class CsvWriter {
    private static final String separator = ",";

    /**
     * Functionality: write a list of student to csv file
     * @param filePath: path location to write file
     * @param list a list of student
     * @throws IOException: cannot create file
     */
    public static void writeStudentToFile(String filePath, List<Student> list) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));

        // Write header field
        bw.write("id" + separator + "name" + separator + "birthday\n");

        String row; // declare temporary row outside loop to avoid re-declare
        for (Student s : list) {
            // write row by row to csv file
            row = s.getId() + separator + s.getName() + separator + DateConverter.convertDateToString(s.getBirthday()) + "\n";
            bw.write(row);
        }

        bw.close();
    }

    /**
     * Functionality: write a list of course to csv file
     * @param filePath: path location to write file
     * @param list a list of course
     * @throws IOException: cannot create file
     */
    public static void writeCourseToFile(String filePath, List<Course> list) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));

        // Write header field
        bw.write("id" + separator + "name" + separator + "credit\n");

        String row; // declare temporary row outside loop to avoid re-declare

        for (Course c : list) {
            // write row by row to csv file
            row = c.getId() + separator + c.getName() + separator + c.getNumberOfCredit() + "\n";
            bw.write(row);
        }

        bw.close();
    }
}
