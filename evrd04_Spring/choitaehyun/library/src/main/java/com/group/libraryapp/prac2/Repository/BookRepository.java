package com.group.libraryapp.prac2.Repository;

import com.group.libraryapp.prac2.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {


    Optional<Book> findByName(String bookName);
}
