package com.group.libraryapp.repository.user;

import com.group.libraryapp.domain.book.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryy extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

}
