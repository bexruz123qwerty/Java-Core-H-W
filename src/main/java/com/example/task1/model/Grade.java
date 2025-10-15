package com.example.task1.model;

import java.util.Objects;

public class Grade {
    private Course course;
    private Student student;
    private int points;

    public Grade(Course course, Student student, int points) {
        this.course = course;
        this.student = student;
        this.points = points;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return points == grade.points && Objects.equals(course, grade.course) && Objects.equals(student, grade.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, student, points);
    }

    @Override
    public String toString() {
        return "Grade{" +
                "course=" + course +
                ", student=" + student +
                ", points=" + points +
                '}';
    }
}
