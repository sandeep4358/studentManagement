package com.javafruit.StudentManagment.dto;


import lombok.*;

/**
 * Status value 00 means success and 01 failure.
 */
@Setter
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseObject {
    private int count;
    private Object data;
    private String status;

}
