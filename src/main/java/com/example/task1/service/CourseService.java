package com.example.task1.service;

import com.example.task1.model.Course;
import com.example.task1.model.Student;
import com.example.task1.model.Teacher;

import java.time.LocalDate;
import java.util.List;

public interface CourseService {

    Course createCourse(int id, String title, String description, Teacher teacher);
    void addStudentToCourse(int courseId, Student student);
    void markAttendance(int courseId, int studentId, LocalDate date, boolean present);
    void assignGrade(int courseId, int studentId, int points);

    Course getCourseById(int id);
    Student getStudentById(int id);
    List<Course> getAllCourses();
    void printCourseReport(int courseId);
}
