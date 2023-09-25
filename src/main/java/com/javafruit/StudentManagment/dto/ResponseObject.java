package com.javafruit.StudentManagment.dto;


import lombok.*;

@Setter
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseObject {
    private int count;
    private Object data;

}
