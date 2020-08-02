package com.touteii.shopping.item.controller;

import com.touteii.shopping.item.pojo.Category;
import com.touteii.shopping.item.pojo.SpecGroup;
import com.touteii.shopping.item.pojo.SpecParam;
import com.touteii.shopping.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("spec")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupsByCid(@PathVariable("cid")Long cid){
        List<SpecGroup> groups= this.specificationService.queryGroupsByCid(cid);
        if(CollectionUtils.isEmpty(groups)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(groups);
    }

    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> queryParams(@RequestParam("gid") Long gid){
        List<SpecParam> params= this.specificationService.queryParams(gid);
        if(CollectionUtils.isEmpty(params)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(params);
    }

    @PostMapping("param")
    public ResponseEntity<Void> postSpecParam(@RequestBody SpecParam specParam){

        this.specificationService.postSpecParam(specParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value="param/{id}")
    public ResponseEntity<Void> deleteParams(@PathVariable("id") String id){

        this.specificationService.deleteSpecParam(Long.parseLong(id));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value="param")
    public ResponseEntity<Void> putCategory(@RequestBody SpecParam specParam){

        this.specificationService.putSpecParam(specParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
