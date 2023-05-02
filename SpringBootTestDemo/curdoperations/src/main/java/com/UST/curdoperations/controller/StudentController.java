package com.UST.curdoperations.controller;

import com.UST.curdoperations.entity.StudentDetails;
import com.UST.curdoperations.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/details")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/addstudents")
    public StudentDetails addstudents(@RequestBody StudentDetails students) {
        return studentService.createStudent(students);
    }

    @GetMapping("/students")
    public List<StudentDetails> getAllStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/students/{id}")
    public StudentDetails getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);

    }

    @PutMapping("/update/{id}")
    public StudentDetails updateStudent(@RequestBody StudentDetails students) {
        return studentService.updateStudent(students);


    }

    @DeleteMapping("/deletestudent/{id}")
    public String deleteStudent(@PathVariable int id) {
        return studentService.deleteStudentById(id);

    }
}