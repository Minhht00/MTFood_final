package edu.itplus.crud.controllerapi;

import edu.itplus.crud.domain.Category;
import edu.itplus.crud.model.ResponseObject;
import edu.itplus.crud.repository.CategoryRepository;
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
@RequestMapping("/category")
public class CategoryControllerRest {
    private static Map<Integer, Category> category = new HashMap<Integer,Category>();
    @Autowired
    CategoryRepository repository;
    @Autowired
    StorageService storageService;
    @Autowired
    private IStorageService storageService1;

    @GetMapping("/get-all")
    public List<Category> getAllCate(){
        return repository.findAll();
    }

//    @GetMapping("/images/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
//        Resource file = storageService.loadAsResource(filename);
//
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//    }
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


    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insert(@RequestBody Category category){
        List<Category> categoryList = repository.findByName(category.getName().trim());
        if (categoryList.size()>0){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(
                    new ResponseObject("Thất bại", "Tên danh mục đã tồn tại", category)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Thành công", "Thêm mới danh mục thành công!", repository.save(category))
        );
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteById(@PathVariable Long id){
        repository.deleteById(id);
        return true;
    }

    @PutMapping("/update")
    public boolean updateById(@RequestBody Category category){
        repository.save(category);
        return true;
    }
}