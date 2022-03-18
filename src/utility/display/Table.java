package utility.display;

import menu.Menu;

import model.Course;
import model.Enrolment;
import model.Student;

import utility.date.DateConverter;

import java.util.List;

/**
 * Class name: Table
 * Role: Utility class
 * Functionalities: display List<Object> as table
 * */
public class Table {
    private static final String separator = "-";

    // Set constructor to private to avoid user initialize Table instance
    private Table() {
    }

    /**
     * Functionality: centralizer the fields of table with appropriate spacing
     * @param field: string field to be formatted
     * @param width: min-width of the field
     * @return a formatted field
     */
    private static String formatField(String field, int width) {

        int padSize = width - field.length();
        int padStart = field.length() + padSize / 2;

        field = String.format("%" + padStart + "s", field);
        field = String.format("%-" + width  + "s", field);

        return field;
    }

    /**
     * Functionality: display a list of student as a table
     * @param students: list of student
     */
    public static void displayStudentTable(List<Student> students) {
        final int idWidth = 10;
        final int nameWidth = 20;
        final int birthdayWidth = 15;
        final int fieldCount = 3;

        final String rowSeparator = separator.repeat(idWidth + nameWidth + birthdayWidth + fieldCount + 1);

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

    /**
     * Functionality: display a list of course as a table
     * @param courses: list of course
     */
    public static void displayCourseTable(List<Course> courses) {
        final int idWidth = 10;
        final int nameWidth = 40;
        final int creditWidth = 10;
        final int fieldCount = 3;

        final String rowSeparator = separator.repeat(idWidth + nameWidth + creditWidth + fieldCount + 1);

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

    /**
     * Functionality: display a list of enrolment as a table
     * @param enrolments: list of enrolment
     */
    public static void displayEnrolmentTable(List<Enrolment> enrolments) {
        final int sIdWidth = 10;
        final int cIDWidth = 10;
        final int semesterWidth = 10;
        final int fieldCount = 3;

        final String rowSeparator = separator.repeat(sIdWidth + cIDWidth + semesterWidth + fieldCount + 1);

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

    /**
     * Functionality: display a menu under a list of commands
     * @param menu: a menu to be display
     */
    public static void displayMenuTable(Menu menu) {
        final int toggleWidth = 10;
        final int commandWidth = 50;
        final int fieldCount = 2;

        String toggleField = formatField("Toggle", toggleWidth);
        String commandField = formatField("Command", commandWidth);

        final String rowSeparator = separator.repeat(toggleWidth + commandWidth + fieldCount + 1);
        List<String> commands = menu.getCommands();

        // Print header
        System.out.println(rowSeparator);
        System.out.printf("|%" + toggleWidth + "s|%-" + commandWidth + "s|\n", toggleField, commandField);
        System.out.println(rowSeparator);

        // Print body
        for (int i = 0; i < commands.size(); i++) {
            toggleField = formatField(String.valueOf(i + 1), toggleWidth);
            commandField = formatField(commands.get(i), commandWidth);
            System.out.printf("|%" + toggleWidth + "s|%-" + commandWidth + "s|\n", toggleField, commandField);
        }

        // Print footer
        System.out.println(rowSeparator);
    }
}
