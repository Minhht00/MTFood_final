package edu.itplus.crud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto{
    private Long id;

    private String email;

    private String password;

    private String name;

    private String address;

    private Boolean isEdit = false;
}
