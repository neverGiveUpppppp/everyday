package com.group.libraryapp.prac2.Repository;


import com.group.libraryapp.prac2.domain.user.Userr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserrRepository extends JpaRepository<Userr, Long> {


    Optional<Userr> findByName(String name);
}
