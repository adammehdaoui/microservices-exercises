package com.stamina.student.service;

import com.stamina.student.dto.SchoolDTO;
import com.stamina.student.dto.StudentDTO;
import com.stamina.student.entity.Student;
import com.stamina.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final RestTemplate restTemplate;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        this.restTemplate = new RestTemplate();

    }

    public SchoolDTO getSchoolById(Long id) {
        return restTemplate.getForObject("http://localhost:8080/schools/" + id, SchoolDTO.class);
    }

    public List<StudentDTO> findAll() {
        List<Student> students = studentRepository.findAll();

        return students
                .stream()
                .map(student -> {
                    SchoolDTO school = getSchoolById(student.getSchoolId());
                    return new StudentDTO(student.getId(), student.getName(), student.getGenre(), school);
                })
                .toList();
    }

    public List<Student> findBySchoolId(String schoolId) {
        return studentRepository.findBySchoolId(schoolId);
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

}
