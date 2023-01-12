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
    }
}

public class toStringEx {
    public static void main (String[] args) {
        Book book = new Book(100,"개미");

        System.out.println(book);
        System.out.println(book.toString());
    }
}