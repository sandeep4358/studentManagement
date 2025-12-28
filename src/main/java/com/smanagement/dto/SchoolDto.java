package com.smanagement.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SchoolDto {
    private String schoolId;
    @JsonProperty("school_name")
    @NotBlank(message = "School name is required")
    private String name;
    private String address;
    private String description;
    private String schoolPhone;
    private String email;
    private String status;
    @JsonProperty("owner_name")

    private String ownerName;
    private String password;
}

