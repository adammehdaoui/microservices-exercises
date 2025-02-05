package com.stamina.school.controller;

import com.stamina.school.entity.School;
import com.stamina.school.service.SchoolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schools")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    public List<School> getSchools() {
        return schoolService.findAllSchools();
    }

    @GetMapping("/{id}")
    public School getSchoolById(@PathVariable Long id) {
        return schoolService.getSchoolById(id);
    }

}
