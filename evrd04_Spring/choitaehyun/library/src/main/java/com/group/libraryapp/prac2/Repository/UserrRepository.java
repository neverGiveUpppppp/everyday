package com.group.libraryapp.prac2.Repository;


import com.group.libraryapp.prac2.domain.Userr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserrRepository extends JpaRepository<Userr, Long> {


}
