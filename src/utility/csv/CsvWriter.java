package utility.csv;

import model.Course;
import model.Student;
import utility.date.DateConverter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {
    private static final String separator = ",";

    public static void writeStudentToFile(String filePath, List<Student> list) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
        String row;
        bw.write("id" + separator + "name" + separator + "birthday\n");

        for (Student s : list) {
            row = s.getId() + separator + s.getName() + separator + DateConverter.convertDateToString(s.getBirthday()) + "\n";
            bw.write(row);
        }

        bw.close();
    }

    public static void writeCourseToFile(String filePath, List<Course> list) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
        String row;
        bw.write("id" + separator + "name" + separator + "credit\n");

        for (Course c : list) {
            row = c.getId() + separator + c.getName() + separator + c.getNumberOfCredit() + "\n";
            bw.write(row);
        }

        bw.close();
    }
}
