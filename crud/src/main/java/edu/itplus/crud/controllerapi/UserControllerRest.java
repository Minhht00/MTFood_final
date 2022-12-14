package edu.itplus.crud.controllerapi;

import edu.itplus.crud.domain.Account;
import edu.itplus.crud.domain.Customer;
import edu.itplus.crud.model.ResponseObject;
import edu.itplus.crud.repository.CustomerRepository;
import edu.itplus.crud.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserControllerRest {
    private static Map<Integer, Customer> users = new HashMap<Integer,Customer>();
    @Autowired
    CustomerService customerService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    CustomerRepository repository;

    @GetMapping("/get-all")
    public List<Customer> getAll(){
        return repository.findAll();
    }

    @GetMapping("/get-user/{id}")
    public List<Customer> getUserById(@PathVariable long id){
        List<Customer> userList = repository.findById(id);
        return userList;
    }

    @GetMapping("/login/{email}/{password}")
    public ResponseEntity<ResponseObject> login(@PathVariable String email, @PathVariable String password){
        List<Customer> emailList = repository.findByEmail(email);


        List<Customer> passwordList = repository.findByPassword(password);
        if (emailList.size()>0 && passwordList.size()>0){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("true"));
        } else {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(
                    new ResponseObject("false"));
        }
//        Customer customer = customerService.login(email,password);
//        if (customer.getEmail().isEmpty()){
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("true"));
//        } else {
//            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(
//                    new ResponseObject("false"));
//        }
    }

    @GetMapping("/checkLogin/{email}/{password}")
    public List<Customer> checkLogin(@PathVariable String email, @PathVariable String password){
        List<Customer> emailList = repository.findByEmail(email);
//        List<Customer> passwordList = repository.findByPassword(password);
        if (emailList.size()>0 && bCryptPasswordEncoder.matches(password,emailList.get(emailList.size()-1).getPassword())){
            System.out.println(emailList.stream().toList());
            return emailList;
        } else {
            return null;
        }

//        Customer customer = customerService.login(email,password);
//        if (customer.getEmail().isEmpty()){
//            return customer;
//        } else {
//            return null;
//        }
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insert(@RequestBody Customer customer){
        List<Customer> userList = repository.findByEmail(customer.getEmail().trim());
        if (userList.size()>0){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(
                    new ResponseObject("Th???t b???i", "T??n danh m???c ???? t???n t???i", customer)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(

                new ResponseObject("Th??nh c??ng", "Th??m m???i danh m???c th??nh c??ng!", customerService.save(customer))
        );
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteById(@PathVariable Long id){
        repository.deleteById(id);
        return true;
    }

    @PutMapping("/update")
    public boolean updateById(@RequestBody Customer customer){
        customerService.save(customer);
        return true;
    }
}
