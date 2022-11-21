package edu.itplus.crud.controllerapi;

import edu.itplus.crud.domain.Order;
import edu.itplus.crud.domain.Product;
import edu.itplus.crud.model.ResponseObject;
import edu.itplus.crud.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartControllerRest {
    private static Map<Integer, Order> cart = new HashMap<Integer,Order>();
    @Autowired
    OrderRepository repository;

    @GetMapping("/get-all")
    public List<Order> getAllProduct(){
        return repository.findAll();
    }
    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insert(@RequestBody Order order){

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Thành công", "Thêm mới danh mục thành công!", repository.save(order))
        );
    }
}
