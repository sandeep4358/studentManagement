package com.smanagement.service;

import com.smanagement.dto.SchoolDto;

import java.util.List;
//contain the school information
public interface SchoolService {
    SchoolDto create(SchoolDto dto);
    SchoolDto getById(String id);
    List<SchoolDto> getAll();
    SchoolDto update(Long id, SchoolDto dto);
    void delete(Long id);
}

