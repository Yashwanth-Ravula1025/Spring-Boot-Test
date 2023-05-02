package com.UST.curdoperations.service;

import com.UST.curdoperations.entity.StudentDetails;
import com.UST.curdoperations.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo repo;



    public StudentDetails createStudent(StudentDetails students) {
        return repo.save(students);
    }

    public List<StudentDetails> getStudents() {
        return repo.findAll();
    }

    public StudentDetails getStudentById(int id) {
        return repo.findById(id).orElse(null);
    }

    public StudentDetails updateStudent(StudentDetails students) {
        return students;
    }

    public String deleteStudentById(int id) {
        repo.deleteById(id);
        return "it was deleted";
    }
}
