package com.example.lesson3.part1;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private final String isbn;
    private final String name;
    private final String author;
    private final int year;
    private boolean status;

    private static final List<Book> books = new ArrayList<>();

    public Book(String isbn, String name, String author, int year) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.year = year;
        this.status = true;
    }

    public String getBookInfo() {
        return isbn + " - " + name + " by " + author + " (" + year + ") "
                + (status ? "[Available]" : "[Reserved]");
    }

    public static List<Book> getBooks() {
        return books;
    }

    public static void addNewBook(Book book) {
        books.add(book);
    }

    public void reserveBook() {
        if (status) {
            status = false;
            System.out.println(name + " reserved successfully.");
        } else {
            System.out.println(name + " is already reserved.");
        }
    }
}

