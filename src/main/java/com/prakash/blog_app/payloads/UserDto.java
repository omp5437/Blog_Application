package com.prakash.blog_app.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDto {
    private int id;
    @NotEmpty
    private String name;
    @Email
    private String email;
    
    @Size(min=3, max=10)
    private String password;
}
