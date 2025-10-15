package com.example.task1.service;

import com.example.task1.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl implements CourseService {

    private final List<Course> courses = new ArrayList<>();
    private final List<Student> students = new ArrayList<>();
    private final List<Attendance> attendanceList = new ArrayList<>();
    private final List<Grade> grades = new ArrayList<>();


    @Override
    public Course createCourse(int id, String title, String description, Teacher teacher) {
        Course course = new Course(id, title, teacher, new ArrayList<>());
        courses.add(course);
        return course;
    }

    @Override
    public void addStudentToCourse(int courseId, Student student) {
        Course course = getCourseById(courseId);
        if (course != null) {
            course.addStudent(student);
        } else {
            System.out.println("Course not found!");
        }
    }

    @Override
    public void markAttendance(int courseId, int studentId, LocalDate date, boolean isPresent) {
        Course course = getCourseById(courseId);
        if (course == null) {
            System.out.println("Course with ID " + courseId + " not found!");
            return;
        }

        Student student = getStudentById(studentId);
        for (Student s : course.getStudents()) {
            if (s.getId() == studentId) {
                student = s;
                break;
            }
        }

        if (student == null) {
            System.out.println("Student with ID " + studentId + " is not enrolled in this course!");
            return;
        }

        Attendance attendance = new Attendance(course, student, date, isPresent);
        attendanceList.add(attendance);

        System.out.println("✅ Attendance marked for student " + student.getFirstName() + " " + student.getLastName()
                + " in course '" + course.getTitle() + "' on " + date
                + " — " + (isPresent ? "Present" : "Absent"));
    }


    @Override
    public void assignGrade(int courseId, int studentId, int points) {
        Course course = getCourseById(courseId);
        if (course == null) {
            System.out.println("Course with ID " + courseId + " not found!");
        }

        Student student = getStudentById(studentId);
        for (Student s : course.getStudents()) {
            if (s.getId() == studentId) {
                student = s;
                break;
            }
        }

        if (student == null) {
            System.out.println("Student with ID " + studentId + " is not enrolled in this course!");
        }

        grades.add(new Grade(course, student, points));
        System.out.println("Grade assigned to " + student.getFirstName() + " " + student.getLastName()
                + " in course '" + course.getTitle() + "': " + points + " points");
    }

    @Override
    public Course getCourseById(int id) {
        for(Course course: courses){
            if(course.getId() == id){
                return course;
            }
        }
        return null;
    }

    @Override
    public Student getStudentById(int id){
        for (Student student: students){
            if(student.getId() == id){
                return student;
            }
        }
        return null;
    }

    @Override
    public List<Course> getAllCourses() {
        return courses;
    }

    @Override
    public void printCourseReport(int courseId) {
        Course course = getCourseById(courseId);
        if (course == null) {
            System.out.println("Course with ID " + courseId + " not found!");
            return;
        }

        System.out.println("\n===== COURSE REPORT =====");
        System.out.println("Course: " + course.getTitle());
        System.out.println("Teacher: " + course.getTeacher().getFirstName() + " " + course.getTeacher().getLastName());
        System.out.println("--------------------------");

        if (course.getStudents().isEmpty()) {
            System.out.println("No students enrolled yet.");
            return;
        }

        for (Student student : course.getStudents()) {
            System.out.println("👤 " + student.getFirstName() + " " + student.getLastName());

            long attended = attendanceList.stream()
                    .filter(a -> a.course().equals(course) && a.student().equals(student) && a.isPresent())
                    .count();

            long total = attendanceList.stream()
                    .filter(a -> a.course().equals(course) && a.student().equals(student))
                    .count();

            System.out.println("   Attendance: " + attended + "/" + total);

            grades.stream()
                    .filter(g -> g.getCourse().equals(course) && g.getStudent().equals(student))
                    .findFirst()
                    .ifPresentOrElse(
                            g -> System.out.println("Grade: " + g.getPoints()),
                            () -> System.out.println("Grade: Not assigned")
                    );
        }

        System.out.println("==========================\n");
    }

}
