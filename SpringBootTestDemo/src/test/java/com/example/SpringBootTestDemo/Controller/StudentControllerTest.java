package com.example.SpringBootTestDemo.Controller;

import com.example.SpringBootTestDemo.controller.StudentController;
import com.example.SpringBootTestDemo.entity.Student;
import com.example.SpringBootTestDemo.service.StudentService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private StudentService studentService;
    private Student student;

    @BeforeEach
    void setup() {
        student = new Student(1,"yashwanth","Hyderabad","245098");
    }
    @Test
    void testSaveStudent() throws Exception {
        Student student = new Student(1,"Yashwanth","Hyderabad","245098");
        Mockito.when(studentService.addStudent(student)).thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders.post("/student/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\r\n" +
                        " \"id\" : 1, \r\n" +
                        " \"name\" : \"Yashwanth\", \r\n" +
                        " \"address\" :\"Hyderabad\", \r\n" +
                        " \"rollNo\" : \"245098\" \r\n" + "}"))
                .andExpect(status().isOk());
    }
    @Test
    public void testGetAllStudent() throws Exception {
        Student student1 = new Student(1, "John Doe", "123 Main St", "123456");
        Student student2 = new Student(2, "Jane Doe", "456 Oak St", "789012");

        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);

        Mockito.when(studentService.getAllStudent()).thenReturn(studentList);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(student1.getId())))
                .andExpect(jsonPath("$[0].name", is(student1.getName())))
                .andExpect(jsonPath("$[0].address", is(student1.getAddress())))
                .andExpect(jsonPath("$[0].rollNo", is(student1.getRollNo())))
                .andExpect(jsonPath("$[1].id", is(student2.getId())))
                .andExpect(jsonPath("$[1].name", is(student2.getName())))
                .andExpect(jsonPath("$[1].address", is(student2.getAddress())))
                .andExpect(jsonPath("$[1].rollNo", is(student2.getRollNo())));

        Mockito.verify(studentService, times(1)).getAllStudent();
    }
    @Test
    public void testGetStudentByName() throws Exception {
        Student student1 = new Student(1, "John Doe", "123 Main St", "123456");
        Student student2 = new Student(2, "Jane Doe", "456 Oak St", "789012");

        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);

        Mockito.when(studentService.getStudentByName("Doe")).thenReturn(studentList);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/name/Doe")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(student1.getId())))
                .andExpect(jsonPath("$[0].name", is(student1.getName())))
                .andExpect(jsonPath("$[0].address", is(student1.getAddress())))
                .andExpect(jsonPath("$[0].rollNo", is(student1.getRollNo())))
                .andExpect(jsonPath("$[1].id", is(student2.getId())))
                .andExpect(jsonPath("$[1].name", is(student2.getName())))
                .andExpect(jsonPath("$[1].address", is(student2.getAddress())))
                .andExpect(jsonPath("$[1].rollNo", is(student2.getRollNo())));

        Mockito.verify(studentService, times(1)).getStudentByName("Doe");
    }
    @Test
    public void testGetStudentById() throws Exception {
        Student student = new Student();
        student.setId(1);
        student.setName("John Doe");
        student.setAddress("123 Main St");
        student.setRollNo("123456");

        Mockito.when(studentService.getStudentById(student.getId())).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/{student-id}", student.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(student.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(student.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address", Matchers.is(student.getAddress())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rollNo", Matchers.is(student.getRollNo())));

        Mockito.verify(studentService, times(1)).getStudentById(student.getId());
    }
    @Test
    public void testDeleteStudentById() throws Exception {
        // Create a Student object with an ID of 1
        Student student = new Student();
        student.setId(1);

        // Mock the StudentService's deleteStudentById method to return the Student object
        Mockito.when(studentService.deleteStudentById(1)).thenReturn(student);

        // Perform the DELETE request
        mockMvc.perform(MockMvcRequestBuilders.delete("/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));

        // Verify that the StudentService's deleteStudentById method was called
        Mockito.verify(studentService, times(1)).deleteStudentById(1);
    }
}



