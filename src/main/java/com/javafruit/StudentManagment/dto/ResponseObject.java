package com.javafruit.StudentManagment.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@Getter
@Builder
public class ResponseObject {
    private Object data;
}
