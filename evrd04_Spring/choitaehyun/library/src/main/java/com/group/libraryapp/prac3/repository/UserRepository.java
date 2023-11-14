package com.group.libraryapp.prac3.repository;

import com.group.libraryapp.prac3.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
