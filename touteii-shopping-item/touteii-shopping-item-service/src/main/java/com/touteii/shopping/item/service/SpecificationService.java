package com.touteii.shopping.item.service;

import com.touteii.shopping.item.mapper.SpecGroupMapper;
import com.touteii.shopping.item.mapper.SpecParamMapper;
import com.touteii.shopping.item.pojo.SpecGroup;
import com.touteii.shopping.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificationService {

    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;

    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroup record=new SpecGroup();
        record.setCid(cid);
        return this.specGroupMapper.select(record);
    }

    public List<SpecParam> queryParams(Long gid) {
        SpecParam record=new SpecParam();
        record.setGroupId(gid);
        return this.specParamMapper.select(record);
    }

    public void postSpecParam(SpecParam specParam) {
        this.specParamMapper.insert(specParam);
    }

    public void deleteSpecParam(Long id) {
        this.specParamMapper.deleteByPrimaryKey(id);
    }

    public void putSpecParam(SpecParam specParam) {
        this.specParamMapper.updateByPrimaryKeySelective(specParam);
    }
}
