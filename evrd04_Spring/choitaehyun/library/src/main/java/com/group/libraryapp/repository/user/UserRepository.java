package com.group.libraryapp.repository.user;

import com.group.libraryapp.domain.book.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
