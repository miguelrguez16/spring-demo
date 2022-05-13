package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentservice;

    @Autowired
    public StudentController( StudentService studentservice){
        this.studentservice = studentservice;
            // mejor que utilizar = new StudentService()
            // se debe auto inyectar
    }

    @GetMapping //Method GET
    public List<Students> getStudents() {
        return studentservice.getStudents();
    }
    @PostMapping
    public void registerNewStudent(@RequestBody Students student){
        studentservice.addNewStudent(student);
    }

}