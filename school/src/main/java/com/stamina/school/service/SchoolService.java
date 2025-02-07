package com.stamina.school.service;

import com.stamina.school.entity.School;
import com.stamina.school.repository.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public List<School> findAllSchools() {
        return schoolRepository.findAll();
    }

    public School getSchoolById(Long id) {
        return schoolRepository.findById(id).orElse(null);
    }

    public School addSchool(School school) {
        return schoolRepository.save(school);
    }

    public School deleteSchool(Long id) {
        School school = schoolRepository.findById(id).orElse(null);
        if (school != null) {
            schoolRepository.delete(school);
        }
        return school;
    }

    public School updateSchool(Long id, School school) {
        School existingSchool = schoolRepository.findById(id).orElse(null);
        if (existingSchool != null) {
            existingSchool.setAddress(school.getAddress());
            existingSchool.setDirectorName(school.getDirectorName());
            existingSchool.setName(school.getName());
            return schoolRepository.save(existingSchool);
        }
        return null;
    }

}


