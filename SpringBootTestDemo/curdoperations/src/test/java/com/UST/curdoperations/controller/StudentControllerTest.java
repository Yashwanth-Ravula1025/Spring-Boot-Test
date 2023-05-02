package com.UST.curdoperations.controller;

import com.UST.curdoperations.entity.StudentDetails;
import com.UST.curdoperations.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService mockStudentService;

    @Test
    void testAddstudents() throws Exception {
        // Setup
        // Configure StudentService.createStudent(...).
        final StudentDetails studentDetails = new StudentDetails(0, "name", "address", "email", 0L);
        when(mockStudentService.createStudent(new StudentDetails(0, "name", "address", "email", 0L)))
                .thenReturn(studentDetails);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/details/addstudents")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    void testGetAllStudents() throws Exception {
        // Setup
        // Configure StudentService.getStudents(...).
        final List<StudentDetails> studentDetails = List.of(new StudentDetails(0, "name", "address", "email", 0L));
        when(mockStudentService.getStudents()).thenReturn(studentDetails);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/details/students")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    void testGetAllStudents_StudentServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockStudentService.getStudents()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/details/students")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    void testGetStudentById() throws Exception {
        // Setup
        // Configure StudentService.getStudentById(...).
        final StudentDetails studentDetails = new StudentDetails(0, "name", "address", "email", 0L);
        when(mockStudentService.getStudentById(0)).thenReturn(studentDetails);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/details/students/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    void testUpdateStudent() throws Exception {
        // Setup
        // Configure StudentService.updateStudent(...).
        final StudentDetails studentDetails = new StudentDetails(0, "name", "address", "email", 0L);
        when(mockStudentService.updateStudent(new StudentDetails(0, "name", "address", "email", 0L)))
                .thenReturn(studentDetails);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/details/update/{id}")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    void testDeleteStudent() throws Exception {
        // Setup
        when(mockStudentService.deleteStudentById(0)).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/details/deletestudent/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }
}
