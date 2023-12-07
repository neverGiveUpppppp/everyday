package com.group.libraryapp.prac3;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepo extends JpaRepository<Book,Long> {

    Optional<Book> findByBookname(String bookName);

}
