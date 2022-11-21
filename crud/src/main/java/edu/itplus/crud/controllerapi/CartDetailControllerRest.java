package edu.itplus.crud.controllerapi;

import edu.itplus.crud.domain.Order;
import edu.itplus.crud.domain.OrderDetail;
import edu.itplus.crud.model.ResponseObject;
import edu.itplus.crud.repository.OrderDetailRepository;
import edu.itplus.crud.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cart_detail")
public class CartDetailControllerRest {
    private static Map<Integer, OrderDetail> cart = new HashMap<Integer,OrderDetail>();
    @Autowired
    OrderDetailRepository repository;
    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insert(@RequestBody OrderDetail orderDetail){

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Thành công", "Thêm mới danh mục thành công!", repository.save(orderDetail))
        );
    }
}
