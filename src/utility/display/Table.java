package utility.display;

import model.Course;
import model.Enrolment;
import model.Student;
import utility.date.DateConverter;

import java.util.List;

public class Table {
    private static String formatField(String field, int width) {

        int padSize = width - field.length();
        int padStart = field.length() + padSize / 2;

        field = String.format("%" + padStart + "s", field);
        field = String.format("%-" + width  + "s", field);

        return field;
    }

    public static void displayStudentTable(List<Student> students) {
        final int idWidth = 10;
        final int nameWidth = 20;
        final int birthdayWidth = 15;
        final int fieldCount = 3;

        final String rowSeparator = "-".repeat(idWidth + nameWidth + birthdayWidth + fieldCount + 1);

        String idField = formatField("ID", idWidth);
        String nameField = formatField("Name", nameWidth);
        String birthdayField = formatField("Birthday", birthdayWidth);

        // Print headline
        System.out.println(rowSeparator);
        System.out.printf("|%" + idWidth + "s|%-" + nameWidth + "s|%-" + birthdayWidth + "s|\n", idField, nameField, birthdayField);
        System.out.println(rowSeparator);

        // Print body
        for (Student st : students) {
            idField = formatField(st.getId(), idWidth);
            nameField = formatField(st.getName(), nameWidth);
            birthdayField = formatField(DateConverter.convertDateToString(st.getBirthday()), birthdayWidth);
            System.out.printf("|%" + idWidth + "s|%-" + nameWidth + "s|%-" + birthdayWidth + "s|\n", idField, nameField, birthdayField);
        }

        // Print footer
        System.out.println(rowSeparator);
    }

    public static void displayCourseTable(List<Course> courses) {
        final int idWidth = 10;
        final int nameWidth = 40;
        final int creditWidth = 10;
        final int fieldCount = 3;

        final String rowSeparator = "-".repeat(idWidth + nameWidth + creditWidth + fieldCount + 1);

        String idField = formatField("ID", idWidth);
        String nameField = formatField("Name", nameWidth);
        String creditField = formatField("credit", creditWidth);

        // Print header
        System.out.println(rowSeparator);
        System.out.printf("|%" + idWidth + "s|%-" + nameWidth + "s|%-" + creditWidth + "s|\n", idField, nameField, creditField);
        System.out.println(rowSeparator);

        // Print body
        for (Course c : courses) {
            idField = formatField(c.getId(), idWidth);
            nameField = formatField(c.getName(), nameWidth);
            creditField = formatField(String.valueOf(c.getNumberOfCredit()), creditWidth);
            System.out.printf("|%" + idWidth + "s|%-" + nameWidth + "s|%-" + creditWidth + "s|\n", idField, nameField, creditField);
        }

        // Print footer
        System.out.println(rowSeparator);
    }

    public static void displayEnrolmentTable(List<Enrolment> enrolments) {
        final int sIdWidth = 10;
        final int cIDWidth = 10;
        final int semesterWidth = 10;
        final int fieldCount = 3;

        final String rowSeparator = "-".repeat(sIdWidth + cIDWidth + semesterWidth + fieldCount + 1);

        String sIdField = formatField("SID", sIdWidth );
        String cIdField = formatField("CID", cIDWidth );
        String semesterField = formatField("Semester", semesterWidth);

        // Print header
        System.out.println(rowSeparator);
        System.out.printf("|%" + sIdWidth + "s|%-" + cIDWidth  + "s|%-" + semesterWidth + "s|\n", sIdField, cIdField, semesterField);
        System.out.println(rowSeparator);

        // Print body
        for (Enrolment e : enrolments) {
            sIdField = formatField(e.getStudent().getId(), sIdWidth);
            cIdField = formatField(e.getCourse().getId(), cIDWidth);
            semesterField = formatField(e.getSemester(), semesterWidth);

            System.out.printf("|%" + sIdWidth + "s|%-" + cIDWidth  + "s|%-" + semesterWidth + "s|\n", sIdField, cIdField, semesterField);
        }

        // Print footer
        System.out.println(rowSeparator);
    }
}
