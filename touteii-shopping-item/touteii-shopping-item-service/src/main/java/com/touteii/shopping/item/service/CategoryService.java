package com.touteii.shopping.item.service;

import com.touteii.shopping.item.mapper.CategoryMapper;
import com.touteii.shopping.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> queryCategoriesByPid(Long pid) {
        Category record=new Category();
        record.setParentId(pid);
        return this.categoryMapper.select(record);
    }

    public void insertCategory(Category category) {
        this.categoryMapper.insert(category);
    }

    public void deleteCategory(Long id){
        this.categoryMapper.deleteByPrimaryKey(id);
    }

    public void putCategory(Category category) {

        this.categoryMapper.updateByPrimaryKeySelective(category);
    }
}
