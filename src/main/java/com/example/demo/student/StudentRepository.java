package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//capa de accesoo

@Repository
public interface StudentRepository extends JpaRepository<Students, Long>{


}
