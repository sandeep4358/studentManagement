package com.smanagement.service.impl;

import com.smanagement.dto.SchoolDto;
import com.smanagement.entity.School;
import com.smanagement.mapper.SchoolMapper;
import com.smanagement.repository.SchoolRepository;
import com.smanagement.service.SchoolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    public SchoolServiceImpl(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    @Override
    @Transactional
    public SchoolDto create(SchoolDto dto) {
        School entity = schoolMapper.toEntity(dto);
        entity = schoolRepository.save(entity);
        return schoolMapper.toDto(entity);
    }

    @Override
    @Transactional
    public SchoolDto getById(String id) {
        return schoolMapper.toDto(schoolRepository.findBySchoolId(id));
    }

    @Override
    @Transactional
    public List<SchoolDto> getAll() {
        return schoolRepository.findAll().stream().map(schoolMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SchoolDto update(Long id, SchoolDto dto) {
        return schoolRepository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setAddress(dto.getAddress());
            existing.setOwnerName(dto.getOwnerName());
            existing.setEmail(dto.getEmail());
            return schoolMapper.toDto(schoolRepository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        schoolRepository.deleteById(id);
    }
}

