package com.example.task1;

import com.example.task1.model.*;
import com.example.task1.service.CourseService;
import com.example.task1.service.CourseServiceImpl;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        CourseService courseService = new CourseServiceImpl();

        // Create teacher
        Teacher teacher = new Teacher(1, "Alice", "Johnson", "Java Programming");

        // Create students
        Student student1 = new Student(1, "John", "Doe");
        Student student2 = new Student(2, "Jane", "Smith");

        // Create a course
        Course course = courseService.createCourse(1, "Java Fundamentals",
                "Introduction to Java programming concepts", teacher);

        // Enroll students
        courseService.addStudentToCourse(course.getId(), student1);
        courseService.addStudentToCourse(course.getId(), student2);

        // Mark attendance
        courseService.markAttendance(course.getId(), student1.getId(), LocalDate.of(2025, 10, 8), true);
        courseService.markAttendance(course.getId(), student2.getId(), LocalDate.of(2025, 10, 8), false);
        courseService.markAttendance(course.getId(), student1.getId(), LocalDate.of(2025, 10, 9), true);

        // Assign grades
        courseService.assignGrade(course.getId(), student1.getId(), 90);
        courseService.assignGrade(course.getId(), student2.getId(), 75);

        // Print course report
        courseService.printCourseReport(course.getId());
    }
}

