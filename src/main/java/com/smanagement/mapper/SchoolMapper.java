package com.smanagement.mapper;

import com.smanagement.dto.SchoolDto;
import com.smanagement.entity.School;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SchoolMapper {
    SchoolDto toDto(School school);
    School toEntity(SchoolDto dto);
}

