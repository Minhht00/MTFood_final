package edu.itplus.crud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
        private Long orderDetailId;
        private Long orderId;
        private Long productId;
        private String productName;
        private int quantity;
        private double unitPrice;
        private String image;
}
