package menu;

import model.Course;
import service.CourseService;
import system.StudentEnrolmentManager;
import utility.display.Table;

import java.util.ArrayList;
import java.util.List;

public class CourseMenu extends Menu{
    private static final ArrayList<String> commands =
            new ArrayList() {{
                add("View all courses");
                add("View all courses in a semester");
                add("View all courses of a student in a semester");
                add("Back");
            }};

    private CourseService courseService;

    public CourseMenu(StudentEnrolmentManager manager) {
        this.courseService = new CourseService(manager);
    }

    @Override
    public void run() {

    }

    @Override
    public List<String> getCommands() {
        return commands;
    }

    public void viewAllCourses() {
        Table.displayCourseTable(courseService.getCourses());
    }

    public void viewAllCoursesInASemester() {

    }
}
