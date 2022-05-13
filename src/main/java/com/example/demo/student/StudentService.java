package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Students> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Students student) {
        Optional<Students> studentsOptional =
                studentRepository.findStudentsByEmail(student.getEmail());
        if (studentsOptional.isPresent()) throw new IllegalStateException("email already taken");
        System.out.println(student);
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
        } else {
            throw new IllegalStateException(
                    "Student with id: " + studentId + " does not exists [delete]");
        }
    }
    @Transactional
    public void updateService(Long studentId, String name, String email) {
        Students student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id: " + studentId + " does not exists [update]"
                ));

        // check if is different and exists
        if(name != null && name.length() >0 && !Objects.equals(name,student.getName())){
            student.setName(name);
        }

        if(email != null && email.length() >0 && !Objects.equals(email,student.getEmail())){
            Optional<Students> studentsOptional =
                    studentRepository.findStudentsByEmail(email);
            if(studentsOptional.isPresent()){
                throw new IllegalStateException("email already taken");
            }else{
                student.setEmail(email);
            }

        }

    }
}
