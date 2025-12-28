package com.smanagement.mapper;

import com.smanagement.dto.TeacherDto;
import com.smanagement.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//@Mapper(componentModel = "spring")
public interface TeacherMapper {
   // @Mapping(source = "school.id", target = "schoolId")
    TeacherDto toDto(Teacher teacher);

    //@Mapping(source = "schoolId", target = "school.id")
    Teacher toEntity(TeacherDto dto);
}

