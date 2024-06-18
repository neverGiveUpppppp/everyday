package com.prac.Class;

public class Book {
    int bookNumber;
    String bookTitle;

    Book (int bookNumber, String bookTitle) {
        this.bookNumber = bookNumber;
        this.bookTitle = bookTitle;
    }

    @Override
    public String toString() {
        System.out.println(bookTitle+" "+bookNumber);
        return bookTitle+" "+bookNumber;
    }

}

