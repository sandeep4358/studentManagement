package com.smanagement.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotificationEvent {
    private String message;
    private String topic;
    private String timestamp;
    private  String user;
}
