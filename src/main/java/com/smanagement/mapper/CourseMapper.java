package com.smanagement.mapper;

import com.smanagement.dto.CourseDto;
import com.smanagement.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//@Mapper(componentModel = "spring")
public interface CourseMapper {
    //@Mapping(source = "teacher.id", target = "teacherId")
    CourseDto toDto(Course course);

    //@Mapping(source = "teacherId", target = "teacher.id")
    Course toEntity(CourseDto dto);
}

