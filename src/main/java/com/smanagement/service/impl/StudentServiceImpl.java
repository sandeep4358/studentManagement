package com.smanagement.service.impl;

import com.smanagement.dto.StudentDto;
import com.smanagement.entity.School;
import com.smanagement.entity.Student;
import com.smanagement.mapper.StudentMapper;
import com.smanagement.repository.SchoolRepository;
import com.smanagement.repository.StudentRepository;
import com.smanagement.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class  StudentServiceImpl{//} implements StudentService {

    /*private final StudentRepository studentRepository;
    private final SchoolRepository schoolRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, SchoolRepository schoolRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.schoolRepository = schoolRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentDto create(StudentDto dto) {
        Student student = studentMapper.toEntity(dto);
        if (dto.getSchoolId() != null) {
            School school = schoolRepository.findById(dto.getSchoolId()).orElse(null);
            student.setSchool(school);
        }
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    @Override
    public StudentDto getById(Long id) {
        return studentRepository.findById(id).map(studentMapper::toDto).orElse(null);
    }

    @Override
    public List<StudentDto> getAll() {
        return studentRepository.findAll().stream().map(studentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public StudentDto update(Long id, StudentDto dto) {
        return studentRepository.findById(id).map(existing -> {
            existing.setFirstName(dto.getFirstName());
            existing.setLastName(dto.getLastName());
            if (dto.getSchoolId() != null) {
                School s = schoolRepository.findById(dto.getSchoolId()).orElse(null);
                existing.setSchool(s);
            }
            return studentMapper.toDto(studentRepository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }*/
}

