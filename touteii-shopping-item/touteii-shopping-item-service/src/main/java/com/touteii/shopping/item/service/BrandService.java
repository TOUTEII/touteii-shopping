package com.touteii.shopping.item.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.touteii.shopping.item.mapper.BrandMapper;
import com.touteii.shopping.item.pojo.Brand;
import com.touteii.shopping.pojo.PageResult;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;


    public PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        //chushihua examle target
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        //key use name to like seach or seach by first Number
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("name", "%" + key + "%").orEqualTo("letter", key);
        }
        //add fen page
        PageHelper.startPage(page, rows);
        //add sort condition
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));

        }

        List<Brand> brands = this.brandMapper.selectByExample(example);

        PageInfo<Brand> pageInfo = new PageInfo<>(brands);

        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }


    //add Brand
    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        //Brand First
        //boolean flag=this.brandMapper.insertSelective(brand) == 1;
        //Add mid table second
        //if(flag){
        this.brandMapper.insertSelective(brand);
        cids.forEach(cid -> {
            this.brandMapper.insertCateGoryAndBrand(cid, brand.getId());
        });
        //}

    }
}
