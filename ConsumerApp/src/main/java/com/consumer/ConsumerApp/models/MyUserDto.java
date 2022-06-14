package com.consumer.ConsumerApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyUserDto {
    private String id;
    private String username;
    private String name;
    private String surname;
}
