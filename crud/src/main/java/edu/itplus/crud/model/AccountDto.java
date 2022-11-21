package edu.itplus.crud.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    @NotEmpty
    @Length(min = 6)
    private String username;
    @NotEmpty
    @Length(min = 6)
    private String password;

    private Boolean isEdit = false;
}
