package com.smanagement.service.impl;

import com.smanagement.dto.SchoolDto;
import com.smanagement.entity.School;
import com.smanagement.mapper.SchoolMapper;
import com.smanagement.repository.SchoolRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SchoolServiceImplTest {

    @Mock
    private SchoolRepository schoolRepository;

    @Mock
    private SchoolMapper schoolMapper;

    @InjectMocks
    private SchoolServiceImpl schoolService;

    @Captor
    private ArgumentCaptor<School> schoolCaptor;

    @Test
    void shouldCreateSchool_whenDtoIsValid() {
        // Arrange
        SchoolDto dto = SchoolDto.builder()
                .schoolId("S1")
                .name("My School")
                .address("123 Lane")
                .email("a@b.com")
                .ownerName("Owner")
                .build();

        School entity = School.builder()
                .schoolId("S1")
                .name("My School")
                .address("123 Lane")
                .email("a@b.com")
                .ownerName("Owner")
                .build();

        School saved = School.builder()
                .id(10L)
                .schoolId("S1")
                .name("My School")
                .address("123 Lane")
                .email("a@b.com")
                .ownerName("Owner")
                .build();

        when(schoolMapper.toEntity(dto)).thenReturn(entity);
        when(schoolRepository.save(entity)).thenReturn(saved);
        when(schoolMapper.toDto(saved)).thenReturn(dto);

        // Act
        SchoolDto result = schoolService.create(dto);

        // Assert
        assertNotNull(result);
        assertEquals(dto, result);
        verify(schoolMapper).toEntity(dto);
        verify(schoolRepository).save(entity);
        verify(schoolMapper).toDto(saved);
    }

    @Test
    void shouldThrow_whenCreateWithNullInput_mappingThrows() {
        // Arrange
        when(schoolMapper.toEntity(null)).thenThrow(new NullPointerException("null dto"));

        // Act & Assert
        assertThrows(NullPointerException.class, () -> schoolService.create(null));
        verify(schoolMapper).toEntity(null);
        verifyNoInteractions(schoolRepository);
    }

    @Test
    void shouldReturnDto_whenGetByIdFound() {
        // Arrange
        String schoolId = "S1";
        School entity = School.builder().schoolId(schoolId).name("N").build();
        SchoolDto dto = SchoolDto.builder().schoolId(schoolId).name("N").build();

        when(schoolRepository.findBySchoolId(schoolId)).thenReturn(entity);
        when(schoolMapper.toDto(entity)).thenReturn(dto);

        // Act
        SchoolDto result = schoolService.getById(schoolId);

        // Assert
        assertNotNull(result);
        assertEquals(dto, result);
        verify(schoolRepository).findBySchoolId(schoolId);
        verify(schoolMapper).toDto(entity);
    }

    @Test
    void shouldReturnNull_whenGetByIdNotFound() {
        // Arrange
        when(schoolRepository.findBySchoolId("X")).thenReturn(null);
        when(schoolMapper.toDto(null)).thenReturn(null);

        // Act
        SchoolDto result = schoolService.getById("X");

        // Assert
        assertNull(result);
        verify(schoolRepository).findBySchoolId("X");
        verify(schoolMapper).toDto(null);
    }

    @Test
    void shouldReturnEmptyList_whenGetAllNoSchools() {
        // Arrange
        when(schoolRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<SchoolDto> result = schoolService.getAll();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(schoolRepository).findAll();
        verify(schoolMapper, never()).toDto(any());
    }

    @Test
    void shouldUpdate_whenSchoolExists() {
        // Arrange
        Long id = 1L;
        School existing = School.builder()
                .id(id)
                .name("Old")
                .address("OldAddr")
                .ownerName("OldOwner")
                .email("old@e.com")
                .build();

        SchoolDto updateDto = SchoolDto.builder()
                .name("New")
                .address("NewAddr")
                .ownerName("NewOwner")
                .email("new@e.com")
                .build();

        when(schoolRepository.findById(id)).thenReturn(Optional.of(existing));
        when(schoolRepository.save(any(School.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(schoolMapper.toDto(any(School.class))).thenReturn(updateDto);

        // Act
        SchoolDto result = schoolService.update(id, updateDto);

        // Assert
        assertNotNull(result);
        assertEquals(updateDto, result);
        verify(schoolRepository).findById(id);
        verify(schoolRepository).save(schoolCaptor.capture());
        School saved = schoolCaptor.getValue();
        assertEquals("New", saved.getName());
        assertEquals("NewAddr", saved.getAddress());
        assertEquals("NewOwner", saved.getOwnerName());
        assertEquals("new@e.com", saved.getEmail());
    }

    @Test
    void shouldReturnNull_whenUpdateNotFound() {
        // Arrange
        Long id = 2L;
        when(schoolRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        SchoolDto result = schoolService.update(id, SchoolDto.builder().build());

        // Assert
        assertNull(result);
        verify(schoolRepository).findById(id);
        verify(schoolRepository, never()).save(any());
    }

    @Test
    void shouldDelete_invokeRepository() {
        // Arrange
        Long id = 5L;
        doNothing().when(schoolRepository).deleteById(id);

        // Act
        schoolService.delete(id);

        // Assert
        verify(schoolRepository).deleteById(id);
    }

    @Test
    void shouldPropagateException_whenRepositoryThrowsOnGetAll() {
        // Arrange
        when(schoolRepository.findAll()).thenThrow(new RuntimeException("db down"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> schoolService.getAll());
        verify(schoolRepository).findAll();
    }
}

