package model;

import java.util.Date;

public class Student {
    private String id;
    private String name;
    private Date birthday;

    public Student(String id, String name, Date birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
