package com.touteii.shopping.item.mapper;

import com.touteii.shopping.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface BrandMapper extends Mapper <Brand>{

    @Insert("insert into tb_category_brand (category_id,brand_id) values (#{cid},#{bid})")
    void insertCateGoryAndBrand(@Param("cid")Long cid,@Param("bid") Long id);
}
