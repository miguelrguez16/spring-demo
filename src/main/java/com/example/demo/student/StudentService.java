package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Students> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Students student) {
        Optional<Students> studentsOptional =
                studentRepository.findStudentsByEmail(student.getEmail());
        if(studentsOptional.isPresent()) throw new IllegalStateException("email Taken");
        System.out.println(student);
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        if(studentRepository.existsById(studentId)){
            studentRepository.deleteById(studentId);
        }else{
            throw new IllegalStateException("Student with id: " + studentId + " does not exists");
        }
    }
}
