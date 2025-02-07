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

    @GetMapping("/school/{schoolId}")
    public List<Student> getStudentsBySchoolId(@PathVariable Long schoolId) {
        return studentService.findBySchoolId(schoolId);
    }

    @PostMapping()
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @DeleteMapping("/{id}")
    public Student deleteStudent(@PathVariable String id) {
        return studentService.deleteStudent(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable String id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

}
