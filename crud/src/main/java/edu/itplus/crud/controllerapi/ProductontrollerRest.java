package edu.itplus.crud.controllerapi;

import edu.itplus.crud.domain.Product;
import edu.itplus.crud.model.ResponseObject;
import edu.itplus.crud.repository.ProductRepository;
import edu.itplus.crud.service.IStorageService;
import edu.itplus.crud.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductontrollerRest {
    private static Map<Integer, Product> Product = new HashMap<Integer, Product>();
    @Autowired
    ProductRepository repository;
    @Autowired
    StorageService storageService;

    @Autowired
    private IStorageService storageService1;

    @GetMapping("/get-all")
    public List<Product> getAllProduct(){
        return repository.findAll();
    }

    @GetMapping("/get-all/{categoryId}")
    public List<Product> getAllProductById(@PathVariable long categoryId){
        List<Product> productList = repository.findByCategoryId(categoryId);
        return productList;
    }

    @GetMapping("/files/{fileName:.+}")
    // /files/06a290064eb94a02a58bfeef36002483.png
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
        try {
            byte[] bytes = storageService1.readFileContent(fileName);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        }catch (Exception exception) {
            return ResponseEntity.noContent().build();
        }
    }
//    @GetMapping("/images/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
//        Resource file = storageService.loadAsResource(filename);
//
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insert(@RequestBody Product product){
        List<Product> ProductList = repository.findByName(product.getName().trim());
        if (ProductList.size()>0){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(
                    new ResponseObject("Thất bại", "Tên danh mục đã tồn tại", product)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Thành công", "Thêm mới danh mục thành công!", repository.save(product))
        );
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteById(@PathVariable Long id){
        repository.deleteById(id);
        return true;
    }

    @PutMapping("/update")
    public boolean updateById(@RequestBody Product Product){
        repository.save(Product);
        return true;
    }
}