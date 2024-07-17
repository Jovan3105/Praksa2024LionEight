package com.example.demo.dtos;


import lombok.*;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data



public class UserDto {
    private Long id;
    private String name;
    private String lastName;
    private String username;
    private String password;
}
