package edu.itplus.crud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long orderId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;
    private Long customerId;
    private String customerName;
    private String address;
    private String customerEmail;
    private double amount;
    private short status;
}
