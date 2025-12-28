package com.smanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolDtoWrapper {
    @JsonProperty("school")
    private SchoolDto school;
}
