package com.stamina.student.controller;

import com.stamina.student.dto.StudentDTO;
import com.stamina.student.entity.Student;
import com.stamina.student.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> getStudents() {
        return studentService.findAll();
    }

    @GetMapping("/{schoolId}")
    public List<Student> getStudentsBySchoolId(@PathVariable String schoolId) {
        return studentService.findBySchoolId(schoolId);
    }

    @PostMapping()
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

}
