package com.example.task1.model;

import java.time.LocalDate;

public record Attendance(Course course, Student student, LocalDate localDateTime, boolean isPresent) {}
