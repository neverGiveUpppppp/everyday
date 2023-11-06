package com.group.libraryapp.prac2.Repository;

import com.group.libraryapp.prac2.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {


}
