package service;

import model.Course;
import model.Enrolment;
import model.Student;
import system.StudentEnrolmentManager;
import utility.date.DateConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CsvService {
    private StudentEnrolmentManager manager;

    public CsvService(StudentEnrolmentManager manager) {
        this.manager = manager;
    }

    private Student convertCsvRowToStudent(String line) throws ParseException {
        String[] fields = line.split(",");
        trimStringArray(fields);

        String sId = fields[0];
        String name = fields[1];
        Date birthday = DateConverter.convertStringToDate(fields[2]);

        return new Student(sId, name, birthday);
    }

    private Course convertCsvRowToCourse(String line) {
        String[] fields = line.split(",");
        trimStringArray(fields);

        String cID = fields[3];
        String name = fields[4];
        int numberOfCredit = Integer.parseInt(fields[5]);

        return new Course(cID, name, numberOfCredit);
    }

    private Enrolment convertCsvRowToEnrolment(String line) {
        String[] fields = line.split(",");
        trimStringArray(fields);

        String sId = fields[0];
        String cId = fields[3];
        String semester = fields[6];

        return new Enrolment(manager.getStudentById(sId), manager.getCourseById(cId), semester);
    }

    public List<Student> getStudentsFromCsv(String fileName) throws FileNotFoundException, ParseException {
        Scanner fileScanner = new Scanner(new File(fileName));
        String row;
        List<Student> students = new ArrayList<>();
        Student student;

        while (fileScanner.hasNextLine()) {
            row = fileScanner.nextLine();

            if (!row.isEmpty()) {
                student = convertCsvRowToStudent(row);

                //Check if exits
                if (!isExist(student, students)) {
                    students.add(student);
                }

            }
        }

//        System.out.println(students);
        return students;
    }

    public List<Course> getCoursesFromCsv(String fileName) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File(fileName));
        String row;
        List<Course> courses = new ArrayList<>();
        Course course;

        while (fileScanner.hasNextLine()) {
            row = fileScanner.nextLine();
            if (!row.isEmpty()) {
                course = convertCsvRowToCourse(row);

                // Check if exits
                if (!isExist(course, courses)) {
                    courses.add(course);
                }

            }
        }

//        System.out.println(courses);
        return courses;
    }

    public List<Enrolment> getEnrolmentsFromCsv(String fileName) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File(fileName));
        String row;
        List<Enrolment> enrolments = new ArrayList<>();
        Enrolment enrolment;

        while (fileScanner.hasNextLine()) {
            row = fileScanner.nextLine();
            if (!row.isEmpty()) {
                enrolment = convertCsvRowToEnrolment(row);

//                 Check if exits
                if (!isExist(enrolment, enrolments)) {
                    enrolments.add(enrolment);
                }
            }
        }

//        System.out.println(enrolments);
        return enrolments;
    }

    public static void trimStringArray(String[] fields) {
        for (String field : fields) {
            field = field.trim();
        }
    }

    private boolean isExist(Student student, List<Student> students) {
        for (Student s : students) {
            if (s.getId().equals(student.getId())) {
                return true;
            }
        }

        return false;
    }

    private boolean isExist(Course course, List<Course> courses) {
        for (Course c : courses) {
            if (c.getId().equals(course.getId())) {
                return true;
            }
        }

        return false;
    }

    private boolean isExist(Enrolment enrolment, List<Enrolment> enrolments) {
        boolean sIdExits;
        boolean cIdExits;
        boolean semesterExits;

        for (Enrolment e : enrolments) {
            sIdExits = e.getStudent().getId().equals(enrolment.getStudent().getId());
            cIdExits = e.getCourse().getId().equals(enrolment.getCourse().getId());
            semesterExits = e.getSemester().equals(enrolment.getSemester());

            if (sIdExits  && cIdExits && semesterExits) {
                return true;
            }
        }

        return false;
    }
}
