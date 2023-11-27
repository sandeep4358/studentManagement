package com.javafruit.StudentManagment.dto;


import lombok.*;

import java.io.Serializable;

/**
 * Status value 00 means success and 01 failure.
 */
@Setter
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseObject implements Serializable {
    private int count;
    private Object data;
    private String status;

}
