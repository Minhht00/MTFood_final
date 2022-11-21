package edu.itplus.crud.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminLoginDto {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    private Boolean rememberMe = false;
}
