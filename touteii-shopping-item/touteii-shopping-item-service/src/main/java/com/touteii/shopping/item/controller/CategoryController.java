package com.touteii.shopping.item.controller;




import com.touteii.shopping.item.pojo.Category;
import com.touteii.shopping.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {



    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoriesByPid(@RequestParam(value = "pid", defaultValue = "0") Long pid) {


            if (pid == null || pid < 0) {
                //400
                //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                //return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                return ResponseEntity.badRequest().build();
            }
            List<Category> categories = this.categoryService.queryCategoriesByPid(pid);
            if (CollectionUtils.isEmpty(categories)) {
                //404
                return ResponseEntity.notFound().build();

            }
            //200
            return ResponseEntity.ok(categories);

    }

    @PostMapping("add")
    public ResponseEntity<Void> insertCategory(@RequestBody Category category){
        this.categoryService.insertCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value="delete")
    public ResponseEntity<Void> deleteCategory(@RequestBody Category category){

        this.categoryService.deleteCategory(category.getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value="put")
    public ResponseEntity<Void> putCategory(@RequestBody Category category){

        this.categoryService.putCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
